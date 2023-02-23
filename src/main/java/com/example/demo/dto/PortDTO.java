package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PortDTO {
    private String id;
    private String name;
    private Float area;
    private Float longitude;
    private Float latitude;
    private String address;
}
