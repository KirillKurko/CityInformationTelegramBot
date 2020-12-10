package com.kirillkurko.cityinformation.service.controller;

import com.kirillkurko.cityinformation.service.exceptions.CityNotFoundException;
import com.kirillkurko.cityinformation.service.model.beans.City;
import com.kirillkurko.cityinformation.service.model.services.interfaces.CityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cityInformation", produces = {"application/json;charset=UTF-8"})
public class CityInformationController {

    @Autowired
    private CityInformationService cityInformationService;

    @GetMapping(value = "/cities")
    public List<City> getCities() {
        return cityInformationService.getCities();
    }

    @GetMapping(value = "/cities/{name}")
    public City getCity(@PathVariable String name) {
        City city = cityInformationService.getCity(name);
        System.out.println("city: " + name);
        if (city == null) {
            throw new CityNotFoundException("City not found");
        }
        return cityInformationService.getCity(name);
    }

    @PostMapping("/cities")
    public City addCity(@RequestBody City city) {
        city.setId(0);
        cityInformationService.saveCity(city);
        return city;
    }

    @PutMapping("/cities")
    public City updateCity(@RequestBody City city) {
        cityInformationService.saveCity(city);
        return city;
    }

    @DeleteMapping("/cities/{name}")
    public String deleteCity(@PathVariable String name) {
        City city = cityInformationService.getCity(name);
        if (city == null) {
            throw new CityNotFoundException("City not found");
        }
        cityInformationService.deleteCity(name);
        return name;
    }
}
