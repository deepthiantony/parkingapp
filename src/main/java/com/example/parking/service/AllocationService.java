package com.example.parking.service;

import com.example.parking.model.Allocation;

import java.util.Date;

public interface AllocationService {
    long createAllocation(String vehicleNumber);
    Allocation deallocate(long allocationId, Date deAllactionTime);
}
