package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WeaponryDTO {
    private String id;
    private String name;
    private String union;
    private String description;
}
