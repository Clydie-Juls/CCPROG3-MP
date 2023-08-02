package com.vnd.mco2restructure.model.vendingmachine;


import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.slots.Slot;
import com.vnd.mco2restructure.model.slots.StorageSlot;

/**
 * This class represents the vending machine that is regular
 */
public class RegularVendingMachine extends VendingMachine {

    /**
     * Initializes a Regular vending machine with the default size
     */
    public RegularVendingMachine() {
        this(8, 10);
    }

    /**
     * Constructs a VendingMachine object with the specified number of slots or capacity.
     *
     * @param noOfSlots                 The number of slots of the vending machine
     * @param itemCapacity              The capacity of each slots
     */
    public RegularVendingMachine(int noOfSlots, int itemCapacity) {
        super(noOfSlots);
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new StorageSlot(itemCapacity);
        }
    }

    /**
     * Constructs a VendingMachine object with the specified number of slots or capacity.
     *
     * @param no               The number of slots or capacity based on the value of isNoOfCapacity.
     * @param isNoOfCapacity   Determines whether the value of no represents the number of slots or the item capacity.
     */
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

    /**
     * dispense an item from a specific slot
     * @param slotNo Slot Number of the vending machine.
     * @return the dispensed item
     */
    @Override
    public Item dispenseItem(int slotNo) {
        StorageSlot selectedSlot = (StorageSlot)slots[slotNo];
        return selectedSlot.giveItem();
    }

    /**
     * Checks if a specific slot has at least one item
     * @param slot specified slot
     * @return true if it at least has an item. False otherwise
     */
    @Override
    public boolean hasStock(Slot<? extends Item> slot) {
        if (slot.getItem() == null) {
            return false;
        }

        return ((StorageSlot) slot).getItemStackCount() >= 1;
    }
}
