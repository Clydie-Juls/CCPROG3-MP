package com.vnd.mco2restructure.model.vendingmachine;

import com.vnd.mco2restructure.model.Denomination;
import com.vnd.mco2restructure.model.Transactions;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.slots.Slot;

import java.lang.reflect.Array;


/**
 * The VendingMachine class represents a vending machine that contains slots with items.
 */
public abstract class VendingMachine {

    protected Slot<? extends Item>[] slots;
    private final Denomination DENOMINATION;
    private final Transactions TRANSACTIONS;


    /**
     * Constructs a VendingMachine object with the specified number of slots.
     *
     * @param noOfSlots    The number of slots in the vending machine.
     */
    public VendingMachine(int noOfSlots) {
        DENOMINATION = new Denomination();
        TRANSACTIONS = new Transactions();
        slots = (Slot<? extends Item>[]) Array.newInstance(Slot.class, noOfSlots);
    }

    /**
     * Constructs a VendingMachine object with the specified number of slots or capacity.
     *
     * @param no               The number of slots or capacity based on the value of isNoOfCapacity.
     * @param isNoOfCapacity   Determines whether the value of no represents the number of slots or the item capacity.
     */
    public VendingMachine(int no, boolean isNoOfCapacity) {
        DENOMINATION = new Denomination();
        TRANSACTIONS = new Transactions();
        // if the "no" is meant for the slot capacity
        if (isNoOfCapacity) {
            no = Math.max(8, no);
            slots = (Slot<? extends Item>[]) Array.newInstance(Slot.class, no);
            // If the "no" is meant for the number of slots
        } else {
            slots = (Slot<? extends Item>[]) Array.newInstance(Slot.class, 8);
        }
    }

    /**
     * Dispenses an existing item if it has enough item amount.
     * @param slotNo Slot Number of the vending machine.
     * @param amount Amount of items requested.
     * @return An array of items requested if the vending machine has enough. Returns null otherwise.
     */
    public abstract Item[] dispenseItem(int slotNo, int amount); //{
//        Slot<? extends Item> selectedSlot = slots[slotNo - 1];
//            if (selectedSlot.sellItem(amount)) {
//                Item item = selectedSlot.getItem();
//
//                Item[] dispensedItems = new Item[amount];
//
//                for (int i = 0; i < amount; i++) {
//                    dispensedItems[i] = new Item(item.getName(), item.getCalories(), item.getPrice());
//                }
//
//                return dispensedItems;
//            }
//
//        return null;
//    }

    /**
     * Retrieves the array of slots in the vending machine.
     *
     * @return The array of slots.
     */
    public Slot<? extends Item>[] getSlots() {
        return slots;
    }

    /**
     * Retrieves the denomination object representing the currency used in the vending machine.
     *
     * @return The denomination object.
     */
    public Denomination getDenomination() {
        return DENOMINATION;
    }

    /**
     * Retrieves the transactions object representing the logs of item transactions.
     *
     * @return The transactions object.
     */
    public Transactions getTransactions() {
        return TRANSACTIONS;
    }


}
