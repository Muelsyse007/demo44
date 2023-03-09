package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WeaponryDTO {
    private long id;
    private String name;
    private String union;
    private String description;
}
