package com.example.parking.repository;

import com.example.parking.constants.Constants;
import com.example.parking.model.Slot;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SlotRepository extends CrudRepository<Slot,Integer> {

    List<Slot> findAll();
    List<Slot> findByStatus(String status);


}
