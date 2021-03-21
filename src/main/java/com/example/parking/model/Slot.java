package com.example.parking.model;

import com.example.parking.enums.SlotStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
@Entity
public class Slot {
    @Id
    @GeneratedValue
    @Column(name = "slot_id")
    private long slotID;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private SlotStatus slotStatus;
    @OneToMany(mappedBy = "slot", fetch = FetchType.LAZY)
    private List<Allocation> allocation;
}
