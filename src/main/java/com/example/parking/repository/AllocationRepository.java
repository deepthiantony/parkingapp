package com.example.parking.repository;

import com.example.parking.model.Allocation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AllocationRepository extends CrudRepository<Allocation,Integer> {
Optional<Allocation> findById(Integer allocationID);
}
