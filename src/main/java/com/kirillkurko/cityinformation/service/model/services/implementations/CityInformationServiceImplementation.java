package com.kirillkurko.cityinformation.service.model.services.implementations;

import com.kirillkurko.cityinformation.service.model.beans.City;
import com.kirillkurko.cityinformation.service.model.repository.CityRepository;
import com.kirillkurko.cityinformation.service.model.services.interfaces.CityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityInformationServiceImplementation implements CityInformationService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    public City getCity(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    @Transactional
    public void saveCity(City city) {
        cityRepository.save(city);
    }

    @Override
    @Transactional
    public void deleteCity(String name) {
        cityRepository.deleteByName(name);
    }
}
