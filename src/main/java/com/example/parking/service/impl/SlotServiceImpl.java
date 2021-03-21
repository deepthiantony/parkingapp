package com.example.parking.service.impl;

import com.example.parking.enums.SlotStatus;
import com.example.parking.exception.ParkingApplicationException;
import com.example.parking.model.Slot;
import com.example.parking.repository.SlotRepository;
import com.example.parking.service.SlotService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("slotService")
public class SlotServiceImpl implements SlotService {

    @Autowired @Getter @Setter
    private SlotRepository slotRepository;

    public Slot createSlot() {
        return slotRepository.save(new Slot(SlotStatus.FREE));
    }

    @Override
    public Slot getOneFreeSlot() {
        Slot slot = slotRepository.findFirstBySlotStatus(SlotStatus.FREE);
        if (slot == null) {
            throw new ParkingApplicationException("No free slot available");
        }
        return slot;
    }

}

