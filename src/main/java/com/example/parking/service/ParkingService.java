package com.example.parking.service;

import com.example.parking.constants.Constants;
import com.example.parking.exception.AllocationException;
import com.example.parking.exception.SlotException;
import com.example.parking.model.Allocation;
import com.example.parking.model.Slot;
import com.example.parking.repository.AllocationRepository;
import com.example.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    AllocationRepository allocationRepository;

    public Slot createSlot() {
        Slot slot = new Slot();
        slot.setStatus(Constants.FREE);
        return slotRepository.save(slot);
    }

    public Allocation createAllocation(Integer vehicleNumber, Integer hours)
            throws AllocationException {
        Optional<Slot> allocatedSlotOptional = slotRepository.findByStatus(Constants.FREE)
                .stream().findAny();
        Allocation allocation = new Allocation();
        if (allocatedSlotOptional.isPresent()) {
            allocation.setHours(hours);
            allocation.setVehicleNumber(vehicleNumber);
            allocation.setAllocationStatus(Constants.ALLOCATED);
            Slot slot = allocatedSlotOptional.get();
            slot.setStatus(Constants.ALLOCATED);
            allocation.setSlot(slot);
//            slotRepository.save(allocatedSlotOptional.get());
            return allocationRepository.save(allocation);
        } else {
            throw new AllocationException("No free slots available");
        }

    }

    public List<Slot> getFreeSlots() throws SlotException {

        List<Slot> freeslots = slotRepository.findByStatus(Constants.FREE);
        if (freeslots.isEmpty()) {
            throw new SlotException("No free slots");
        } else {
            return freeslots;
        }
    }


    public Allocation ammendSlot(Integer hours, Integer allocationID) throws AllocationException {
        Optional<Allocation> ammendallocation = allocationRepository.findById(allocationID);
        if (ammendallocation.isPresent()) {
            Allocation allocation = ammendallocation.get();
            allocation.setHours(allocation.getHours() + hours);
            return allocationRepository.save(allocation);
        } else {
            throw new AllocationException("Provde " + allocationID + " allocation not found");

        }

    }

    public SlotRepository getSlotRepository() {
        return slotRepository;
    }

    public void setSlotRepository(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public AllocationRepository getAllocationRepository() {
        return allocationRepository;
    }

    public void setAllocationRepository (AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    public Allocation unallocateSlot(Integer allocationId) throws AllocationException {
       Optional<Allocation> allocation= allocationRepository.findById(allocationId);
        if(allocation.isPresent()){
            Allocation unallocateAllocation=allocation.get();
            unallocateAllocation.setAllocationStatus(Constants.UNALLOCATE);
            return allocationRepository.save(unallocateAllocation);
        }
        else{
            throw new AllocationException("the particular allocation cannot be found");
        }
    }
}

