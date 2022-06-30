package com.example.plantcaredevicedatabase.repository;

import com.example.plantcaredevicedatabase.model.DataEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DataEntryDBRepository extends JpaRepository<DataEntry, Long> {
}
