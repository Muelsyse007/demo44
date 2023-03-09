package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.util.Pair;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class AirportDTO {
    private long id;
    private String name;
    private Float area;
    private String airfieldClass;
    private String airportType;
    private String longitude;
    private String latitude;
    private String IATA;
    private String ICAO;
    private String website;
    private String address;
}
