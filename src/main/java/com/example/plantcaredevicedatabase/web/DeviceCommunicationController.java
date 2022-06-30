package com.example.plantcaredevicedatabase.web;

import com.example.plantcaredevicedatabase.model.DataEntry;
import com.example.plantcaredevicedatabase.service.DataEntryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceCommunicationController {
    private final DataEntryService dataEntryService;

    public DeviceCommunicationController(DataEntryService dataEntryService) {
        this.dataEntryService = dataEntryService;
    }

    @GetMapping("/save")
    public Boolean saveEntry(
            @RequestParam Float temperature,
            @RequestParam Float humidity,
            @RequestParam Integer moisture){
        dataEntryService.save(temperature, humidity, moisture);

        return true;
    }

    @GetMapping("/get/all")
    public List<DataEntry> getAllDataEntries(){
        return dataEntryService.findAll();
    }

    @GetMapping("/get/today")
    public DataEntry getAllDataEntriesToday(){
        return dataEntryService.findAllToday();
    }

    @GetMapping("/get/month")
    public DataEntry getAllDataEntriesThisMonth(){
        return dataEntryService.findAllMonth();
    }

    @GetMapping("/deleteAll")
    public Boolean deleteAll(){
        return dataEntryService.deleteAll();
    }
}
