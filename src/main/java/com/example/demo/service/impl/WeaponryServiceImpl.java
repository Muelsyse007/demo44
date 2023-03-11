package com.example.demo.service.impl;

import com.example.demo.dto.WeaponryDTO;
import com.example.demo.entity.Airport;
import com.example.demo.entity.MilitaryBase;
import com.example.demo.entity.Port;
import com.example.demo.entity.Weaponry;
import com.example.demo.relationship.DeployedIn;
import com.example.demo.repository.AirportRepository;
import com.example.demo.repository.MilitaryBaseRepository;
import com.example.demo.repository.PortRepository;
import com.example.demo.repository.WeaponryRepository;
import com.example.demo.service.WeaponryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeaponryServiceImpl implements WeaponryService {
    final private Logger logger = LoggerFactory.getLogger(WeaponryServiceImpl.class);
    private final WeaponryRepository weaponryRepository;
    private final PortRepository portRepository;
    private final MilitaryBaseRepository militaryBaseRepository;
    private final AirportRepository airportRepository;

    private WeaponryDTO cast(Weaponry weaponry) {
        WeaponryDTO weaponryDTO = new WeaponryDTO();
        weaponryDTO.setDescription(weaponry.getDescription())
                .setName(weaponry.getName())
                .setUnion(weaponry.getUnion());
        return weaponryDTO;
    }

    @Override
    public WeaponryDTO createWeaponry(WeaponryDTO weaponryDTO) {
        Weaponry newWeaponry = new Weaponry();
        newWeaponry.initWeaponry(weaponryDTO);
        newWeaponry.setIdentity(weaponryDTO.getId());
        newWeaponry.setId(weaponryDTO.getId());
        weaponryRepository.save(newWeaponry);
        return weaponryDTO;
    }

    @Override
    public String addRelationship(String weaponryName, String baseType, String baseName, Integer numCount) {
        Weaponry weaponry = weaponryRepository.findWeaponryByName(weaponryName);
        DeployedIn deployedIn = new DeployedIn(weaponry,numCount);
        deployedIn.getCount();
        List<DeployedIn> list = new ArrayList<>();
        switch (baseType) {
            case "Airport" -> {
                Airport airport = airportRepository.findAirportByName(baseName);
                list = airport.getWeaponrySet();
                if (list.contains(deployedIn)) {
                    logger.info("no");
                    break;
                }
                list.add(deployedIn);
                airport.setWeaponrySet(list);
                try {
                    airportRepository.save(airport);
                } catch (Exception e) {
                    logger.info("error");
                }
            }
            case "Port" -> {
                Port port = portRepository.findPortByName(baseName);
                list = port.getWeaponrySet();
                if (list.contains(deployedIn)) {
                    logger.info("no");
                    break;
                }
                list.add(deployedIn);
//                port.setWeaponrySet(list);
                portRepository.save(port);
            }
            case "MilitaryBase" -> {
                MilitaryBase militaryBase = militaryBaseRepository.findMilitaryBaseByName(baseName);
                list = militaryBase.getWeaponrySet();
                if (list.contains(deployedIn)) {
                    logger.info("no");
                    break;
                }
                list.add(deployedIn);
//                militaryBase.setWeaponrySet(list);
                militaryBaseRepository.save(militaryBase);
            }
        }
        return "OK";
    }
}
