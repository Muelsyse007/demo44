package com.example.demo.relationship;

import com.example.demo.entity.Weaponry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
public class DeployedIn {
    @RelationshipId
    long id;
    @TargetNode
    private final Weaponry weaponry;
    private final Integer count;

    public DeployedIn(Weaponry weaponry, Integer count) {
        this.count = count;
        this.weaponry = weaponry;
    }

    public Integer getCounts(){return count;}
}
