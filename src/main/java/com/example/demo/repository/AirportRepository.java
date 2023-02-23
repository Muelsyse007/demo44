package com.example.demo.repository;

import com.example.demo.entity.Airport;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AirportRepository extends Neo4jRepository<Airport,String> {
    Airport findAirportByName(String name);
}
