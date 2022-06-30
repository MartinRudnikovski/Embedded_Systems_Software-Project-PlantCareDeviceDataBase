package com.example.plantcaredevicedatabase.service;

import com.example.plantcaredevicedatabase.model.DataEntry;

import java.util.List;

public interface DataEntryService {
    List<DataEntry> findAll();
    DataEntry findAllToday();
    DataEntry findAllMonth();
    DataEntry save(Float temperature, Float humidity, Integer moisture);

    Boolean deleteAll();
}
