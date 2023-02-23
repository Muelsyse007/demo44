package com.example.demo.service.impl;

import com.example.demo.dto.WeaponryDTO;
import com.example.demo.entity.Airport;
import com.example.demo.entity.MilitaryBase;
import com.example.demo.entity.Port;
import com.example.demo.entity.Weaponry;
import com.example.demo.repository.AirportRepository;
import com.example.demo.repository.MilitaryBaseRepository;
import com.example.demo.repository.PortRepository;
import com.example.demo.repository.WeaponryRepository;
import com.example.demo.service.WeaponryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;

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
                .setUnion(weaponry.getUnion())
                .setId(weaponry.getIdentity());
        return weaponryDTO;
    }

    @Override
    public WeaponryDTO createWeaponry(WeaponryDTO weaponryDTO) {
        Weaponry newWeaponry = new Weaponry();
        newWeaponry.initWeaponry(weaponryDTO);
        weaponryRepository.save(newWeaponry);
        return weaponryDTO;
    }

    @Override
    public WeaponryDTO addRelationship(String weaponryName, String baseType, String baseName) {
        Weaponry weaponry = weaponryRepository.findWeaponryByName(weaponryName);
        HashSet<Weaponry> set = new HashSet<>();
        switch (baseType) {
            case "Airport" -> {
                Airport airport = airportRepository.findAirportByName(baseName);
                set = airport.getWeaponries();
                if (set.contains(weaponry)) {
                    logger.info("no");
                    break;
                }
                set.add(weaponry);
                airport.setWeaponries(set);
                airportRepository.save(airport);
            }
            case "Port" -> {
                Port port = portRepository.findPortByName(baseName);
                set = port.getWeaponries();
                if (set.contains(weaponry)) {
                    logger.info("no");
                    break;
                }
                set.add(weaponry);
                port.setWeaponries(set);
                portRepository.save(port);
            }
            case "MilitaryBase" -> {
                MilitaryBase militaryBase = militaryBaseRepository.findMilitaryBaseByName(baseName);
                set = militaryBase.getWeaponries();
                if (set.contains(weaponry)) {
                    logger.info("no");
                    break;
                }
                set.add(weaponry);
                militaryBase.setWeaponries(set);
                militaryBaseRepository.save(militaryBase);
            }
        }
        return cast(weaponry);
    }
}
