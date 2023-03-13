package com.example.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.service.CypherService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cypher")
@RequiredArgsConstructor
public class CypherController {
    private final CypherService cypherService;

    @GetMapping("/")
    public Result testCypher(){
        return cypherService.aTestCypher();
    }
    @GetMapping("/1")
    public List<String> getAirports() {
        return cypherService.getAirport();
    }

//    @GetMapping("/{id}/{type}/{numLimit}")
//    public JSONObject getRelationships(@PathVariable Long id, @PathVariable String type, @PathVariable Integer numLimit) {
//        return cypherService.getRelations(id, type, numLimit);
//    }
    @GetMapping("/relation")
    public JSONObject getRelationships(@RequestParam(value = "id") Long id,
                                       @RequestParam(value = "type") String type,
                                       @RequestParam(value = "numLimit", required = false) Integer numLimit) {
        return cypherService.getRelations(id, type, numLimit);
    }
}
