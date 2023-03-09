package com.example.demo.repository;

import com.example.demo.entity.Airport;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface AirportRepository extends Neo4jRepository<Airport, Long> {
    Airport findAirportByName(String name);
    List<Airport> findAllByName(String name);
}
