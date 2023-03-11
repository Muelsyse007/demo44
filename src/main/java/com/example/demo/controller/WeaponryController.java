package com.example.demo.controller;

import com.example.demo.dto.WeaponryDTO;
import com.example.demo.service.WeaponryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weaponry")
@RequiredArgsConstructor
public class WeaponryController {
    private final WeaponryService weaponryService;

    @PostMapping("")
    public WeaponryDTO createWeaponry(@RequestBody WeaponryDTO weaponryDTO) {
        return weaponryService.createWeaponry(weaponryDTO);
    }

    @PostMapping("/DEPLOYED_IN/{weaponryName}/{baseType}/{baseName}/{roles}")
    public void addRelationship(@PathVariable String weaponryName, @PathVariable String baseType, @PathVariable String baseName, @PathVariable Integer roles) {
        weaponryService.addRelationship(weaponryName, baseType, baseName, roles);
    }
}
