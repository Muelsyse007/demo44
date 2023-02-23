package com.example.demo.service;

import com.example.demo.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    List<AirportDTO> getAllAirports();

    AirportDTO getAirportByName(String name);

    AirportDTO createAirport(AirportDTO airportDTO);

    void deleteAirportByName(String name);

    AirportDTO updateAirport(AirportDTO airportDTO);
}
