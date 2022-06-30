package com.example.plantcaredevicedatabase.service.impl;

import com.example.plantcaredevicedatabase.model.DataEntry;
import com.example.plantcaredevicedatabase.repository.DataEntryDBRepository;
import com.example.plantcaredevicedatabase.service.DataEntryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DataEntryServiceImpl implements DataEntryService {

    private final DataEntryDBRepository dataEntryRepository;

    public DataEntryServiceImpl(DataEntryDBRepository dataEntryRepository) {
        this.dataEntryRepository = dataEntryRepository;
    }

    @Override
    public List<DataEntry> findAll() {
        return dataEntryRepository.findAll();
    }

    @Override
    public DataEntry findAllToday() {
        List<DataEntry> data = dataEntryRepository.findAll()
                .stream()
                .filter(i -> i.logTime.getDayOfYear() == LocalDateTime.now().getDayOfYear() && i.logTime.getYear() == LocalDateTime.now().getYear())
                .toList();
        DataEntry dataEntry = new DataEntry();

        if(data.size() == 0){
            dataEntry.setTemperature((float)0.0);
            dataEntry.setHumidity((float)0.0);
            dataEntry.setMoisture(0);

            return dataEntry;
        }

        dataEntry.setTemperature((float) data.stream().mapToDouble(i -> i.temperature).average().orElseThrow(RuntimeException::new));
        dataEntry.setHumidity((float) data.stream().mapToDouble(i -> i.humidity).average().orElseThrow(RuntimeException::new));
        dataEntry.setMoisture( (int) data.stream().mapToInt(i -> i.moisture).average().orElseThrow(RuntimeException::new));
        return dataEntry;
    }

    @Override
    public DataEntry findAllMonth() {
        List<DataEntry> data = dataEntryRepository.findAll()
                .stream()
                .filter(i -> i.logTime.getMonth() == LocalDateTime.now().getMonth() && i.logTime.getYear() == LocalDateTime.now().getYear())
                .toList();
        DataEntry dataEntry = new DataEntry();
        dataEntry.setTemperature((float) data.stream().mapToDouble(i -> i.temperature).average().orElseThrow(RuntimeException::new));
        dataEntry.setHumidity((float) data.stream().mapToDouble(i -> i.humidity).average().orElseThrow(RuntimeException::new));
        dataEntry.setMoisture((int) data.stream().mapToDouble(i -> i.moisture).average().orElseThrow(RuntimeException::new));
        return dataEntry;
    }

    @Override
    public DataEntry save(Float temperature, Float humidity, Integer moisture) {

        DataEntry dataEntry = new DataEntry();
        dataEntry.setTemperature(temperature);
        dataEntry.setHumidity(humidity);
        dataEntry.setMoisture(moisture);

        return dataEntryRepository.save(dataEntry);
    }

    @Override
    public Boolean deleteAll() {
        dataEntryRepository.deleteAll();
        return true;
    }
}
