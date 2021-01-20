package com.example.parking.service;

import com.example.parking.constants.Constants;
import com.example.parking.exception.AllocationException;
import com.example.parking.model.Slot;
import com.example.parking.repository.AllocationRepository;
import com.example.parking.repository.SlotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParkingServiceTest {

    @Mock
    SlotRepository slotRepository;

    ParkingService parkingService = new ParkingService();
    @Mock
    private AllocationRepository allocationRepository;

    @BeforeEach
    public void setup (){
        MockitoAnnotations.initMocks(this);
        parkingService.setSlotRepository(slotRepository);
        parkingService.setAllocationRepository(allocationRepository);
    }

    @Test
    void testAllocateService() {
        List<Slot> list = new ArrayList<>();
        Mockito.when(slotRepository.findByStatus(Mockito.anyString())).thenReturn(list);
        assertThrows(AllocationException.class,
                () -> parkingService.createAllocation(null, null));
    }

    @Test
    public void testNoOf() {
//        Assertions.assertEquals(0,ParkingService.getFreeSLot().size());
//        Assertions.assertNotNull(ParkingService.createSlot());
//        Assertions.assertNotNull(ParkingService.getFreeSLot());
//        Assertions.assertEquals(Constants.FREE,ParkingService.createSlot().getStatus());
    }

    @Test
    public void testNoOfHoursExceeded() throws AllocationException {

        Assertions.assertThrows(AllocationException.class,
                ()->parkingService.createAllocation(456,8));
    }

}