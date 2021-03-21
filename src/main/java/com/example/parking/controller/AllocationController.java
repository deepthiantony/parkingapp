package com.example.parking.controller;

import com.example.parking.enums.AllocationStatus;
import com.example.parking.model.Allocation;
import com.example.parking.model.Slot;
import com.example.parking.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/v1")
public class AllocationController {
    @Autowired
    AllocationService allocationService;

    @PostMapping("/allocations")
    public ResponseEntity<Long> createAllocation(@RequestBody Allocation allocation) {
        long allocationId = allocationService.createAllocation(allocation.getVehicleNumber());
        return ResponseEntity.ok().body(allocationId);
    }

    @PutMapping("/allocations/{allocationId}")
    public ResponseEntity<Allocation> deallocate(@PathVariable("allocationId") long allocationId) {
        Allocation allocation = allocationService.deallocate(allocationId, new Date());
        return ResponseEntity.ok().body(allocation);
    }

}
