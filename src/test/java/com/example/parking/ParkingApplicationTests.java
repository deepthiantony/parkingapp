package com.example.parking;

import com.example.parking.service.ParkingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingApplicationTests {

	@Test
	void contextLoads() {
		ParkingService service=new ParkingService();
		Assertions.assertEquals(1,1);
		Assertions.assertNotNull(service);
	}

}
