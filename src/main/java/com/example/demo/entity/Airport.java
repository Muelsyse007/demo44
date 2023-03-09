package com.example.demo.entity;

import com.example.demo.dto.AirportDTO;
import com.example.demo.relationship.DeployedIn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Airport")
@Getter
@Setter
public class Airport{
    @Id
    long identity;
    @Property
    private long id;
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
    @Version
    private long version;
    @Relationship(type = "DEPLOYED_IN", direction = Relationship.Direction.INCOMING)
    private ArrayList<DeployedIn> weaponrySet = new ArrayList<>();

    public Airport withId(Long identity) {
        if (Objects.equals(this.identity, identity)) {
            return this;
        } else {
            Airport newObject = new Airport();
            newObject.identity = identity;
            newObject.name = this.name;
            return newObject;
        }
    }
    public void initAirport(AirportDTO airportDTO) {
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
