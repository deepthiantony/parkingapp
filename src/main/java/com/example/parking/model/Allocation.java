package com.example.parking.model;

import com.example.parking.enums.AllocationStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Allocation {
    @Id
    @GeneratedValue
    @Column(name = "allocation_id")
    private long allocationId;
    @Column(name = "hours_charged")
    private int hoursCharged;
    @NonNull
    @Column(name = "vehicle_no")
    private String vehicleNumber;
    @NonNull
    @Column(name = "allocation_status")
    @Enumerated(EnumType.STRING)
    private AllocationStatus allocationStatus;
    @NonNull
    @Column(name = "allocated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date allocatedTime;
    @Column(name = "deallocated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deAllocatedTime;
    @NonNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "slot_id")
    public Slot slot;
    @Column(name = "amount")
    private double amount;


}
