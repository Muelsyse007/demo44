package com.example.demo.controller;

import com.example.demo.dto.AirportDTO;
import com.example.demo.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @GetMapping("")
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{name}")
    public AirportDTO getAirportByName(@PathVariable String name) {
        return airportService.getAirportByName(name);
    }

    @PostMapping("")
    public AirportDTO createAirport(@RequestBody AirportDTO airportDTO) {
        return airportService.createAirport(airportDTO);
    }

    @PutMapping("")
    public AirportDTO updateAirport(@RequestBody AirportDTO airportDTO) {
        return airportService.updateAirport(airportDTO);
    }

    @DeleteMapping("/{name}")
    public void deleteAirport(@PathVariable String name) {
        airportService.deleteAirportByName(name);
    }
}
