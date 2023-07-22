package com.vnd.mco2restructure.model.vendingmachine;

import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.slots.IdSlot;

import java.util.HashMap;
import java.util.List;

public class SpecialVendingMachine extends VendingMachine{
    private HashMap<NonCustomizableItem, List<NonCustomizableItem>> itemStorage;

    public SpecialVendingMachine() {
        this(8, 10);
    }

    public SpecialVendingMachine(int noOfSlots, int itemCapacity) {
        super(noOfSlots);
        itemStorage = new HashMap<>();
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new IdSlot<>(itemCapacity);
        }
    }

    public SpecialVendingMachine(int no, boolean isNoOfCapacity) {
        super(no, isNoOfCapacity);
        itemStorage = new HashMap<>();
        if (isNoOfCapacity) {
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new IdSlot<>(10);
            }
        } else {
            no = Math.max(10, no);
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new IdSlot<>(no);
            }
        }
    }

    @Override
    public Item[] dispenseItem(int slotNo, int amount) {
        return new Item[0];
    }
}
