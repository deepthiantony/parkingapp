package com.example.parking.model;

import javax.persistence.*;

@Entity
public class Allocation {
    @Id
    @GeneratedValue
    @Column(name="allocation_id")
    Integer allocationId;
    @Column(name="hours")
    Integer hours;
    @Column(name="vehicle_no")
    Integer vehicleNumber;
    @Column(name="allocation_status")
    String allocationStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "slot_id")
    public Slot slot;

    public Allocation() {
    }

    public Integer getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Integer allocationId) {
        this.allocationId = allocationId;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }


    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
