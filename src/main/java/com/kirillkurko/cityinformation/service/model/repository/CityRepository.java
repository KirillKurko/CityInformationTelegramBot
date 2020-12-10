package com.kirillkurko.cityinformation.service.model.repository;

import com.kirillkurko.cityinformation.service.model.beans.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByName(String name);

    void deleteByName(String name);

}
