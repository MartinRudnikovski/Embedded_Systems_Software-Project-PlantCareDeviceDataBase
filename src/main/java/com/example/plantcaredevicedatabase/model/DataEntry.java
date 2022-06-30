package com.example.plantcaredevicedatabase.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class DataEntry {
    @Id
    @GeneratedValue
    public Long id;

    public Float temperature;
    public Float humidity;
    public Integer moisture;

    public LocalDateTime logTime;

    public DataEntry(){
        logTime = LocalDateTime.now();
    }
}
