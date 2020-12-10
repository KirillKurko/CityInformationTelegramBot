package com.kirillkurko.cityinformation.service.model.services.interfaces;

import com.kirillkurko.cityinformation.service.model.beans.City;

import java.util.List;


public interface CityInformationService {

    List<City> getCities();

    City getCity(String name);

    void saveCity(City city);

    void deleteCity(String name);
}
