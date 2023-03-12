package com.example.demo.service.impl;


import com.example.demo.service.CypherService;
import com.example.demo.utils.Runner;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CypherServiceImpl implements CypherService {
    private final Session session;
    private final Logger log = LoggerFactory.getLogger(CypherServiceImpl.class);
//    private Runner runner;

//    public Result searchPath(SearchPathDTO searchPathDTO) {
//        String cypher = "MATCH (n:" + searchPathDTO.getFromNode() + "{" + searchPathDTO.getProperity() + ":'"
//                + searchPathDTO.getValue() + "'}) "
//                + "CALL apoc.path.subgraphAll(n,{relationshipFilter: '<|>',minLevel: 0,maxLevel: -1}) "
//                + "YIELD nodes,relationships RETURN nodes, relationships";
//        Session session = sessionFactory.openSession();
//        return session.query(cypher, new HashMap<>(), false);
//    }
    @Override
    public Result aTestCypher() {
//        String cypher = "Match (n: Airport) return *";
        String cypher = "Match(a:Airport),(b:Weaponry) where a.name=\"5888\" and b.name=\"7\" create (a)-[r:test]->(b) return r";
        return session.run(cypher);
    }
}
