package com.example.parking;

import com.example.parking.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingApplication implements CommandLineRunner {
    @Autowired
    SlotService slotService;
    @Value("${no.of.free.slots:5}")
    private int noOfFreeSlots;

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < noOfFreeSlots; i++) {
            slotService.createSlot();
        }
    }
}
