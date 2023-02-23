package com.example.demo.service.impl;

import com.example.demo.dto.AirportDTO;
import com.example.demo.entity.Airport;
import com.example.demo.repository.AirportRepository;
import com.example.demo.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    final private Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);
    private final AirportRepository airportRepository;

    private AirportDTO cast(Airport airport) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(airport.getIdentity())
                .setAirportType(airport.getAirportType())
                .setAddress(airport.getAddress())
                .setICAO(airport.getICAO())
                .setIATA(airport.getIATA())
                .setArea(airport.getArea())
                .setAirfieldClass(airport.getAirfieldClass())
                .setLatitude(airport.getLatitude())
                .setLongitude(airport.getLongitude())
                .setName(airport.getName())
                .setWebsite(airport.getWebsite());
        //todo 在此完善关系显示
        return airportDTO;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        try {
            return airportRepository.findAll().stream().map(this::cast).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AirportDTO getAirportByName(String name){
        Airport airport = airportRepository.findAirportByName(name);
        logger.info("yes");
        return cast(airport);
    }

    @Override
    public AirportDTO createAirport(AirportDTO airportDTO) {
        Airport newAirport = new Airport();
        newAirport.initAirport(airportDTO);
        airportRepository.save(newAirport);
        return airportDTO;
    }

    @Override
    public void deleteAirportByName(String name) {
        //todo 判断是否存在
        Airport existAirport = airportRepository.findAirportByName(name);
        airportRepository.delete(existAirport);
    }

    @Override
    public AirportDTO updateAirport(AirportDTO airportDTO) {
        Airport existAirport = airportRepository.findAirportByName(airportDTO.getName());
        existAirport.initAirport(airportDTO);
        airportRepository.save(existAirport);
        return airportDTO;
    }
}
