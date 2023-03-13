package com.example.demo.service;

import com.alibaba.fastjson2.JSONObject;
import org.neo4j.driver.Result;

import java.util.List;

public interface CypherService {
    //    public Result searchPath(SearchPathDTO searchPathDTO) {
    //        String cypher = "MATCH (n:" + searchPathDTO.getFromNode() + "{" + searchPathDTO.getProperity() + ":'"
    //                + searchPathDTO.getValue() + "'}) "
    //                + "CALL apoc.path.subgraphAll(n,{relationshipFilter: '<|>',minLevel: 0,maxLevel: -1}) "
    //                + "YIELD nodes,relationships RETURN nodes, relationships";
    //        Session session = sessionFactory.openSession();
    //        return session.query(cypher, new HashMap<>(), false);
    //    }
    Result aTestCypher();

    List<String> getAirport();

    JSONObject getRelations(Long id, String type, Integer numLimit);
}
