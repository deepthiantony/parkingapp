package com.example.parking.service;

import com.example.parking.enums.AllocationStatus;
import com.example.parking.enums.SlotStatus;
import com.example.parking.exception.ParkingApplicationException;
import com.example.parking.model.Allocation;
import com.example.parking.model.Slot;
import com.example.parking.repository.AllocationRepository;
import com.example.parking.service.impl.AllocationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AllocationServiceTest {
    public static final int ALLOCATION_ID = 100;
    public static final String VEHICLE_NUMBER = "123";
    public static final int HOUR = 60 * 60 * 1000;
    public static final int MINIMUM_HOURS = 4;
    public static final int RATE_PER_HOUR = 40;
    private AllocationServiceImpl allocationService = new AllocationServiceImpl();
    AllocationRepository allocationRepository = Mockito.mock(AllocationRepository.class);
    SlotService slotService = Mockito.mock(SlotService.class);

    @BeforeEach
    public void setup() {
        allocationService.setAllocationRepository(allocationRepository);
        allocationService.setSlotService(slotService);
        ReflectionTestUtils.setField(allocationService, "minimumHours", MINIMUM_HOURS);
        ReflectionTestUtils.setField(allocationService, "ratePerHour", RATE_PER_HOUR);
    }

    @Test
    public void testCreateAllocation() {
        Slot slot = new Slot(SlotStatus.FREE);
        Mockito.when(slotService.getOneFreeSlot()).thenReturn(slot);
        Allocation allocation = new Allocation(VEHICLE_NUMBER, AllocationStatus.ALLOCATED, new Date(), slot);
        allocation.setAllocationId(ALLOCATION_ID);
        Mockito.when(allocationRepository.save(Mockito.any(Allocation.class))).thenReturn(allocation);
        long allocationId = allocationService.createAllocation(VEHICLE_NUMBER);
        Assertions.assertEquals(ALLOCATION_ID, allocationId);
    }

    @Test
    public void testDeAllocateForInvalidTime() {
        Mockito.when(allocationRepository.findById(ALLOCATION_ID)).thenReturn(Optional.of(new Allocation()));
        Assertions.assertThrows(ParkingApplicationException.class, () ->
                allocationService.deallocate(ALLOCATION_ID, null));
    }

    @Test
    public void testDeAllocateForInvalidAllocation() {
        Assertions.assertThrows(ParkingApplicationException.class, () ->
                allocationService.deallocate(ALLOCATION_ID, new Date()));
    }

    @Test
    public void testDeAllocateForTimeLessThanMinimumHours() {
        Date allocationTime = new Date();
        Allocation allocation = new Allocation(VEHICLE_NUMBER, AllocationStatus.ALLOCATED, allocationTime, new Slot(SlotStatus.OCCUPIED));
        Mockito.when(allocationRepository.findById(ALLOCATION_ID)).thenReturn(Optional.of(allocation));

        Date deallocationTime = new Date(allocationTime.getTime() + (1 * HOUR));
        allocation = allocationService.deallocate(ALLOCATION_ID, deallocationTime);

        Assertions.assertEquals(AllocationStatus.DEALLOCATED, allocation.getAllocationStatus());
        Assertions.assertEquals(MINIMUM_HOURS, allocation.getHoursCharged());
        Assertions.assertEquals(MINIMUM_HOURS * RATE_PER_HOUR, allocation.getAmount());
        Assertions.assertEquals(SlotStatus.FREE, allocation.getSlot().getSlotStatus());
    }

    @Test
    public void testDeAllocateForTimeMoreThanMinimumHours() {
        Date allocationTime = new Date();
        Date deallocationTime = new Date(allocationTime.getTime() + (5 * HOUR));
        Allocation allocation = new Allocation(VEHICLE_NUMBER, AllocationStatus.ALLOCATED, allocationTime, new Slot(SlotStatus.OCCUPIED));
        Mockito.when(allocationRepository.findById(ALLOCATION_ID)).thenReturn(Optional.of(allocation));
        allocation = allocationService.deallocate(ALLOCATION_ID, deallocationTime);
        Assertions.assertEquals(5, allocation.getHoursCharged());
        Assertions.assertEquals(5 * RATE_PER_HOUR, allocation.getAmount());
    }


}
