package com.example.parking.service;

import com.example.parking.enums.SlotStatus;
import com.example.parking.exception.ParkingApplicationException;
import com.example.parking.model.Slot;
import com.example.parking.repository.SlotRepository;
import com.example.parking.service.impl.SlotServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class SlotServiceTest {

    private SlotServiceImpl slotService = new SlotServiceImpl();

    SlotRepository slotRepository = Mockito.mock(SlotRepository.class);

    @BeforeEach
    public void setup() {
        Slot slot = new Slot();
        slot.setSlotStatus(SlotStatus.FREE);
        slotService.setSlotRepository(slotRepository);
        Mockito.when(slotRepository.save(any(Slot.class))).thenReturn(slot);
    }

    @Test
    public void testCreateSlot() {
        Assertions.assertTrue(slotService.createSlot() instanceof Slot);
    }


    @Test
    public void testGetOneFreeSlot() {
        Slot slot = new Slot(SlotStatus.FREE);
        Mockito.when(slotRepository.findFirstBySlotStatus(SlotStatus.FREE)).thenReturn(slot);
        final Slot oneFreeSlot = slotService.getOneFreeSlot();
        Assertions.assertTrue(oneFreeSlot instanceof Slot);
        Assertions.assertEquals(SlotStatus.FREE, oneFreeSlot.getSlotStatus());
    }

    @Test
    public void testNoFreeSlot() {
        Mockito.when(slotRepository.findFirstBySlotStatus(SlotStatus.FREE)).thenReturn(null);
        Assertions.assertThrows(ParkingApplicationException.class,()->slotService.getOneFreeSlot());
    }


}