package com.example.demo.entity;

import com.example.demo.dto.AirportDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Airport")
@RequiredArgsConstructor
@Getter
@Setter
public class Airport {
    @Id
    private String identity;
    @Property
    private String name;
    @Property
    private Float area;
    @Property
    private String airfieldClass;
    @Property
    private String airportType;
    @Property
    private String longitude;
    @Property
    private String latitude;
    @Property
    private String IATA;
    @Property
    private String ICAO;
    @Property
    private String website;
    @Property
    private String address;
    @Relationship(type = "DEPLOYED_IN", direction = INCOMING)
    private HashSet<Weaponry> weaponries = new HashSet<>();


    public void initAirport(AirportDTO airportDTO) {
        this.identity = airportDTO.getId();
        this.airportType = airportDTO.getAirportType();
        this.name = airportDTO.getName();
        this.area = airportDTO.getArea();
        this.address = airportDTO.getAddress();
        this.IATA = airportDTO.getIATA();
        this.ICAO = airportDTO.getICAO();
        this.airfieldClass = airportDTO.getAirfieldClass();
        this.latitude = airportDTO.getLatitude();
        this.longitude = airportDTO.getLongitude();
        this.website = airportDTO.getWebsite();
    }
}
