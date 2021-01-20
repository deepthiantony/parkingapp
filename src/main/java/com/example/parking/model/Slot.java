package com.example.parking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Slot {
    @Id
    @GeneratedValue
    @Column(name = "slot_id")
    Integer slotID;
    @Column(name="status")
    String status;
//    @OneToMany(mappedBy = "slot",cascade = CascadeType.ALL)
//    public List<Allocation> allocation;

    public Slot() {
    }

    public Integer getSlotID() {
        return slotID;
    }

    public void setSlotID(Integer slotID) {
        this.slotID = slotID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
