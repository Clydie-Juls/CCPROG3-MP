package com.vnd.mco2restructure.model.vendingmachine;


import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.slots.Slot;
import com.vnd.mco2restructure.model.slots.StorageSlot;

public class RegularVendingMachine extends VendingMachine {

    public RegularVendingMachine() {
        this(8, 10);
    }

    public RegularVendingMachine(int noOfSlots, int itemCapacity) {
        super(noOfSlots);
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new StorageSlot(itemCapacity);
        }
    }

    public RegularVendingMachine(int no, boolean isNoOfCapacity) {
        super(no, isNoOfCapacity);
        if (isNoOfCapacity) {
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new StorageSlot(10);
            }
        } else {
            no = Math.max(10, no);
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new StorageSlot(no);
            }
        }
    }

    @Override
    public Item dispenseItem(int slotNo) {
        StorageSlot selectedSlot = (StorageSlot)slots[slotNo];
        return selectedSlot.giveItem();
    }

    @Override
    public boolean hasStock(Slot<? extends Item> slot) {
        if (slot.getItem() == null) {
            return false;
        }

        return ((StorageSlot) slot).getItemStackCount() >= 1;
    }
}
