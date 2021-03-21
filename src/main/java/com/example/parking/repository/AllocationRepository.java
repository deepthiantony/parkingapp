package com.example.parking.repository;


import com.example.parking.model.Allocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllocationRepository extends CrudRepository<Allocation, Long> {
    Optional<Allocation> findById(long allocationID);
}
