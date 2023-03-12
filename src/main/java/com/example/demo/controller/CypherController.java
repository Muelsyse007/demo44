package com.example.demo.controller;

import com.example.demo.service.CypherService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cypher")
@RequiredArgsConstructor
public class CypherController {
    private final CypherService cypherService;

    @GetMapping("/")
    public Result testCypher(){
        return cypherService.aTestCypher();
    }
}
