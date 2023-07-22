package com.vnd.mco2restructure.controller;


import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.slots.Slot;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;

import java.util.LinkedHashMap;

/**
 * Teh VendingMachineController class represents the vending machine's controller.
 */
public class VendingMachineController {
    private final VendingMachine VENDING_MACHINE;
    /**
     * Initializes a vending machine controller with its model and view.
     * @param vendingMachine  model of the vending machine controller
     */
    public VendingMachineController(VendingMachine vendingMachine) {
        this.VENDING_MACHINE = vendingMachine;
    }

    /**
     * Buys an existing item of the vending machine. If the transaction process failed(i.e. not enough denomination) or
     * there is not enough item, the buy process will fail. Else, it will pass the items the user bought.
     * @param payment  Users payment.
     * @param slotNo  Slot number of the vending machine.
     * @param amount  amount of items the user wants to buy.
     * @return  array of items the user bought if successful, returns null otherwise.
     */
    public Item[] buy(LinkedHashMap<Integer, Integer> payment, int slotNo, int amount) {
        // if range is out of bounds
        if (slotNo - 1 < 0 || slotNo - 1 >= VENDING_MACHINE.getSlots().length) {
        } else {
            Slot<? extends Item> selectedSlot = VENDING_MACHINE.getSlots()[slotNo - 1];
            // if transactions process has failed
            if (!VENDING_MACHINE.getDenomination().processPayment(payment,
                    selectedSlot.getItem().getPrice() * amount)) {

            } else {
                Item[] dispensedItem = VENDING_MACHINE.dispenseItem(slotNo, amount);

                // If dispense item process is successful
                if(dispensedItem != null) {
                    VENDING_MACHINE.getTransactions().addTransaction(dispensedItem[0], amount);
                    return dispensedItem;
                }
            }
        }
        return null;
    }

    /**
     *  Checks if the vending machine has at least one stock of item.
     * @return  true if there is at least one stock of item, false otherwise.
     */
    //TODO: create hasStock in model
    public boolean hasStock() {
        boolean hasItem = false;
//        for (int i = 0; i < VENDING_MACHINE.getSlots().length; ++i) {
//            if (VENDING_MACHINE.getSlots()[i].getAmount() > 0) {
//                hasItem = true;
//            }
//        }
        return hasItem;
    }

    /**
     * A getter for the vending machine.
     * @return  The controller's vending machine.
     */
    public VendingMachine getVendingMachine() {
        return VENDING_MACHINE;
    }

    /**
     * Retrieves the price of an existing item.
     * @param slotNo  Slot number of the vending machine.
     * @return  The price of the existing item.
     */
    public int getItemPrice(int slotNo) {
        // If slotNo is out of bounds
        if (slotNo - 1 < 0 || slotNo - 1 >= VENDING_MACHINE.getSlots().length) {
            return -1;
            // If Slot object has no existing item
        } else if(VENDING_MACHINE.getSlots()[slotNo - 1].getItem() == null) {
            return -1;
        }
        return VENDING_MACHINE.getSlots()[slotNo - 1].getItem().getPrice();
    }
}
