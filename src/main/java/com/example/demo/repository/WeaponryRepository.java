package com.example.demo.repository;

import com.example.demo.entity.Weaponry;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface WeaponryRepository extends Neo4jRepository<Weaponry,String> {
    Weaponry findWeaponryByName(String name);
}
