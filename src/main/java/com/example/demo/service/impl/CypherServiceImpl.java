package com.example.demo.service.impl;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.service.CypherService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CypherServiceImpl implements CypherService {
//    private final Session session;
    private final Driver driver;
//    private final Session session = driver.session();
//    private Runner runner;
    private final JSONObject data = new JSONObject();
    final private Logger logger = LoggerFactory.getLogger(CypherServiceImpl.class);

    @Override
    public Result aTestCypher() {
//        String cypher = "Match (n: Airport) return *";
        String cypher = "Match(a:Airport),(b:Weaponry) where a.name=\"5888\" and b.name=\"7\" create (a)-[r:test]->(b) return r";
//        return session.run(cypher);
        return null;
    }
    @Override
    public List<String> getAirport() {
        try (var session = driver.session()) {
            return session.executeRead(tx -> {
                List<String> names = new ArrayList<>();
                var result = tx.run("MATCH (a:Airport) RETURN a.name ORDER BY a.name");
                while (result.hasNext()) {
                    names.add(result.next().get(0).asString());
                }
                return names;
            });
        }
    }

    @Override
    public JSONObject getRelations(Long id, String type, Integer numLimit) {
//        String cypher = "match (n:"+type+"{id:"+id+"})-[r]-() return type(r)";
        String cypher = "match (n:" + type + "{id:" + id + "})-[r]-() return type(r),startNode(r).name," +
        "LABELS(startNode(r))[0],endNode(r).name,LABELS(endNode(r))[0]";
        //如果有数据限制，则根据限制，如果没有则默认只返回25条关系
        if (numLimit == null || numLimit <= 0) {
            numLimit = 25;
        }
        cypher = cypher + " " + "LIMIT" + " " + numLimit;
        String finalCypher = cypher;
        logger.info(finalCypher);
//        String cypher1 = "match (n:Weaponry{id:0})-[r]-() return type(r),startNode(r).name," +
//                "LABELS(startNode(r))[0],endNode(r).name,LABELS(endNode(r))[0]";
        try (var session = driver.session()) {
            return session.executeRead(tx -> {
                JSONArray categories = new JSONArray();
                JSONArray nodes = new JSONArray();
                JSONArray links = new JSONArray();
                HashSet<String> categorySet = new HashSet<>();
                HashMap<JSONObject,String> nodeMap = new HashMap<>();
                HashMap<String, Integer> categoryMap = new HashMap<>();
                HashMap<String, JSONObject> nodeList = new HashMap<>();
                var result = tx.run(finalCypher);
                List<Record> list = result.list();
                //对所有出现的节点和label根据name进行去重，存入Set/Map容器内
                for (Record record:list) {
                    categorySet.add(record.get(2).asString());
                    categorySet.add(record.get(4).asString());

                    JSONObject startNode = new JSONObject();
                    JSONObject endNode = new JSONObject();

                    startNode.put("name",record.get(1).asString());
                    startNode.put("value",1);
                    endNode.put("name",record.get(3).asString());
                    endNode.put("value",1);
                    nodeMap.put(startNode,record.get(2).asString());
                    nodeMap.put(endNode,record.get(4).asString());
                }
                //遍历categorySet，将所有label存入对应的JSONArray和Map并编号，方便下一步查找
                int numFlag = 0;
                for (String category:categorySet) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name",category);
                    categories.add(jsonObject);
                    categoryMap.put(category, numFlag);
                    numFlag = numFlag + 1;
                }
                //遍历nodeMap，为每个node注入category的编号，将node存入Array，方便进行关系的导入
                numFlag = 0;
                for (JSONObject node:nodeMap.keySet()) {
                    node.put("category", categoryMap.get(nodeMap.get(node)));
                    node.put("id", numFlag);
                    nodes.add(node);
                    nodeList.put(node.get("name").toString(), node);
                    numFlag = numFlag + 1;
                }
                //再次遍历返回的result，完成关系Json的格式化
                for (Record record:list) {
                    JSONObject link = new JSONObject();
                    link.put("name",record.get(0).asString());
                    link.put("source", nodeList.get(record.get(1).asString()).get("id"));
                    link.put("target", nodeList.get(record.get(3).asString()).get("id"));
                    links.add(link);
                }
                //给JsonArray data添加元素，每个键对应上面的同名JsonArray
                data.put("categories", categories);
                data.put("nodes", nodes);
                data.put("links", links);
                return data;
            });
        }
    }
}
