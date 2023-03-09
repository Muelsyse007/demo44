package com.example.demo.entity;

import com.example.demo.dto.PortDTO;
import com.example.demo.relationship.DeployedIn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
@Node("Port")
@RequiredArgsConstructor
@Getter
@Setter
public class Port {
    @Id
    private String identity;
    @Property
    private String name;
    @Property
    private Float area;
    @Property
    private Float longitude;
    @Property
    private Float latitude;
    @Property
    private String address;
    @Relationship(type = "DEPLOYED_IN", direction = INCOMING)
    private ArrayList<DeployedIn> weaponrySet = new ArrayList<>();

    public Port(PortDTO portDTO){
        this.identity = portDTO.getId();
        this.name = portDTO.getName();
        this.area = portDTO.getArea();
        this.address = portDTO.getAddress();
        this.latitude = portDTO.getLatitude();
        this.longitude = portDTO.getLongitude();
    }
}
