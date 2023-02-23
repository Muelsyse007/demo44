package com.example.demo.entity;

import com.example.demo.dto.WeaponryDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Weaponry")
@RequiredArgsConstructor
@Getter
@Setter
public class Weaponry {
    @Id
    private String identity;
    @Property
    private String name;
    @Property
    private String union;
    @Property
    private String description;

    public void initWeaponry(WeaponryDTO weaponryDTO){
        this.identity = weaponryDTO.getId();
        this.name = weaponryDTO.getName();
        this.union = weaponryDTO.getUnion();
        this.description = weaponryDTO.getDescription();
    }
}
