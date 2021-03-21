package com.example.parking.service;

import com.example.parking.model.Slot;

public interface SlotService {
    public Slot createSlot();
    public Slot getOneFreeSlot();

}
