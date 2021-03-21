package com.example.parking.service.impl;

import com.example.parking.enums.AllocationStatus;
import com.example.parking.enums.SlotStatus;
import com.example.parking.exception.ParkingApplicationException;
import com.example.parking.model.Allocation;
import com.example.parking.model.Slot;
import com.example.parking.repository.AllocationRepository;
import com.example.parking.service.AllocationService;
import com.example.parking.service.SlotService;
import com.example.parking.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("allocationService")
public class AllocationServiceImpl implements AllocationService {
    @Autowired
    @Getter
    @Setter
    AllocationRepository allocationRepository;

    @Autowired
    @Getter
    @Setter
    SlotService slotService;
    @Value("${parking.minimum.hours:2}")
    private int minimumHours;
    @Value("${parking.rate.per.hour:20}")
    private int ratePerHour;

    @Override
    public long createAllocation(String vehicleNumber) {
        Slot slot = slotService.getOneFreeSlot();
        slot.setSlotStatus(SlotStatus.OCCUPIED);
        Allocation allocation = new Allocation(vehicleNumber, AllocationStatus.ALLOCATED, new Date(), slot);
        return allocationRepository.save(allocation).getAllocationId();
    }

    @Override
    public Allocation deallocate(long allocationId, Date deAllocationTime) {
        if(deAllocationTime == null){
            throw new ParkingApplicationException("Undefined deallocationTime");
        }
        Optional<Allocation> allocatedOptional = allocationRepository.findById(allocationId);
        if (!allocatedOptional.isPresent()) {
            throw new ParkingApplicationException("Allocation with the given id cannot be found");
        }
        Allocation allocation = allocatedOptional.get();
        allocation.setAllocationStatus(AllocationStatus.DEALLOCATED);
        allocation.getSlot().setSlotStatus(SlotStatus.FREE);
        allocation.setDeAllocatedTime(deAllocationTime);
        updateTotalHoursAndAmountOfAllocation(allocation);
        allocationRepository.save(allocation);
        return allocation;

    }

    private void updateTotalHoursAndAmountOfAllocation(Allocation allocation) {
        Date allocationStart = allocation.getAllocatedTime();
        Date allocationEnd = allocation.getDeAllocatedTime();
        int totalHours = 0;
        double totalAmount = 0.0;
        double timeDiffInHours = DateUtils.timeDiffInHours(allocationStart, allocationEnd);

        if (timeDiffInHours < minimumHours) {
            totalHours = minimumHours;
        } else {
            totalHours = (int) Math.ceil(timeDiffInHours);
        }
        totalAmount = totalHours * ratePerHour;
        allocation.setHoursCharged(totalHours);
        allocation.setAmount(totalAmount);
    }
}
