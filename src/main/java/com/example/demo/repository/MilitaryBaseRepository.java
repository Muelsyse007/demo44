package com.example.demo.repository;

import com.example.demo.entity.MilitaryBase;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MilitaryBaseRepository extends Neo4jRepository<MilitaryBase,String> {
    MilitaryBase findMilitaryBaseByName(String name);
}
