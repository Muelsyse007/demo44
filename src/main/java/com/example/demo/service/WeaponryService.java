package com.example.demo.service;

import com.example.demo.dto.WeaponryDTO;
import com.example.demo.entity.Weaponry;
import java.util.List;

public interface WeaponryService {
    WeaponryDTO createWeaponry(WeaponryDTO weaponryDTO);

    String addRelationship(String weaponryName, String baseType, String baseName, Integer roles);
}
