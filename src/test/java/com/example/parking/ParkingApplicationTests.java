package com.example.parking;

import com.example.parking.model.Allocation;
import com.example.parking.service.AllocationService;
import com.example.parking.service.SlotService;
import com.example.parking.service.impl.SlotServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingApplicationTests {

    @Autowired
    SlotService slotService;

    @Autowired
    AllocationService allocationService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(slotService);
		Assertions.assertNotNull(allocationService);
	}

}
