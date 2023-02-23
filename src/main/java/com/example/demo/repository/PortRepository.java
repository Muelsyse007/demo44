package com.example.demo.repository;

import com.example.demo.entity.Port;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PortRepository extends Neo4jRepository<Port,String> {
    Port findPortByName(String name);
}
