package com.example.parking.repository;

import com.example.parking.enums.SlotStatus;
import com.example.parking.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {
    Slot findFirstBySlotStatus(SlotStatus slotStatus);



}
