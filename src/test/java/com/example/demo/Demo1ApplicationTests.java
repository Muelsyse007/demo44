package com.example.demo;

import com.example.demo.entity.Airport;

import com.example.demo.entity.Weaponry;

import com.example.demo.relationship.DeployedIn;
import com.example.demo.repository.AirportRepository;
import com.example.demo.repository.WeaponryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.management.relation.Role;
import java.util.*;
import java.util.Collections;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads( @Autowired AirportRepository airportRepository,
        @Autowired WeaponryRepository weaponryRepository) {
        // 删除所有节点和关系（删除节点会响应删除关联关系），避免后续创建节点重复影响
//        movieRepository.deleteAll();
//        personRepository.deleteAll();

        // 创建节点
        Airport airport = new Airport();
        airport.setName("8");
        airport.setIdentity(1111111111);
        Weaponry weaponry = new Weaponry();
        weaponry.setName("7");
        weaponry.setIdentity(876543212);

        DeployedIn deployedIn=new DeployedIn(weaponry,1);
//        Weaponry director = new Weaponry(1973, "新海诚");
        // 添加关系
        airport.getWeaponrySet().add(deployedIn);
//        airport.getDirectors().add(director);
        // 存入图数据库持久化
        airportRepository.save(airport);

    }

}
