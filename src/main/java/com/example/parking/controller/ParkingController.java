package com.example.parking.controller;

import com.example.parking.exception.AllocationException;
import com.example.parking.exception.SlotException;
import com.example.parking.model.Allocation;
import com.example.parking.model.Slot;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ParkingController {
    @Autowired
    ParkingService parkingService;

    @PostMapping("/create-slot")
    public ResponseEntity<Slot> createSlot() {
        Slot slot = parkingService.createSlot();
        return ResponseEntity.ok().body(slot);
    }

    @PostMapping("/allocate-slot")
    public ResponseEntity<Allocation> allocateslot(@RequestBody Allocation allocation) throws AllocationException {
        Allocation updatedAllocation = parkingService.createAllocation(allocation.getVehicleNumber(), allocation.getHours());
        return ResponseEntity.ok().body(updatedAllocation);
    }

    @GetMapping("/free-slots")
    public ResponseEntity<List<Slot>> getFreeSlots() throws SlotException {
        List<Slot> freeslot = parkingService.getFreeSlots();
        return ResponseEntity.ok().body(freeslot);
    }

    @PostMapping("/ammend-allocation")
    public ResponseEntity<Allocation> getammendedSlot(@RequestBody Allocation allocation) throws AllocationException {
        Allocation extendedAllocation=parkingService.ammendSlot(allocation.getHours(),allocation.getAllocationId());
        return ResponseEntity.ok().body(extendedAllocation);
    }

    @PostMapping("/ammend-allocation/{allocId}")
    public ResponseEntity<Allocation> getammendedSlot(@PathVariable("allocId") Integer allocId,
                                                      @RequestParam(name = "hours",required = false,defaultValue = "2")
                                                              Integer hours)
            throws AllocationException {
        Allocation extendedAllocation=parkingService.ammendSlot(hours,allocId);
        return ResponseEntity.ok().body(extendedAllocation);
    }
    @PostMapping("/unallocate-slot/{allocID}")
    public ResponseEntity<Allocation> unallocateSlot(@PathVariable("allocID") Integer allocId) throws AllocationException {
        Allocation unallocatedAllocation= parkingService.unallocateSlot(allocId);
        return ResponseEntity.ok().body(unallocatedAllocation);
    }

}


