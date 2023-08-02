package com.vnd.mco2restructure.model.vendingmachine;

import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.IndependentItem;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.slots.IdSlot;
import com.vnd.mco2restructure.model.slots.Slot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the vending machine that is regular
 */
public class SpecialVendingMachine extends VendingMachine{
    private HashMap<NonCustomizableItem, List<NonCustomizableItem>> itemStorage;

    /**
     * Initializes a Special vending machine with the default size
     */
    public SpecialVendingMachine() {
        this(8, 10);
    }

    /**
     * Constructs a VendingMachine object with the specified number of slots or capacity.
     *
     * @param noOfSlots                 The number of slots of the vending machine
     * @param itemCapacity              The capacity of each slots
     */
    public SpecialVendingMachine(int noOfSlots, int itemCapacity) {
        super(noOfSlots);
        itemStorage = new HashMap<>();
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new IdSlot(itemCapacity);
        }
    }

    /**
     * Constructs a VendingMachine object with the specified number of slots or capacity.
     *
     * @param no               The number of slots or capacity based on the value of isNoOfCapacity.
     * @param isNoOfCapacity   Determines whether the value of no represents the number of slots or the item capacity.
     */
    public SpecialVendingMachine(int no, boolean isNoOfCapacity) {
        super(no, isNoOfCapacity);
        itemStorage = new HashMap<>();
        if (isNoOfCapacity) {
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new IdSlot(10);
            }
        } else {
            no = Math.max(10, no);
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new IdSlot(no);
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
        IdSlot slot = (IdSlot) slots[slotNo];
        if(slot.getItem() instanceof CustomizableItem customizableItem) {
            for (NonCustomizableItem content : customizableItem.getItemContents()) {
                System.out.println(content.getName());
                itemStorage.get(content).remove(itemStorage.get(content).size() - 1);
            }
        } else if (slot.getItem() instanceof IndependentItem independentItem){
            itemStorage.get(independentItem).remove(itemStorage.get(independentItem).size() - 1);
        }
        return slot.getItem();
    }

    /**
     * Checks if a specific slot has at least one item
     * @param slot specified slot
     * @return true if it at least has an item. False otherwise
     */
    @Override
    public boolean hasStock(Slot<? extends Item> slot) {
        boolean isCompleteItems = true;
        if (slot.getItem() == null) {
            return false;
        }

        if(slot.getItem() instanceof CustomizableItem customizableItem) {
            for (Map.Entry<String, NonCustomizableItem[]> entry : customizableItem.getItemsDerived().entrySet()) {
                if(!isCompleteItems) {
                    break;
                }

                boolean containsType = false;
                for (NonCustomizableItem item : entry.getValue()) {
                    if(itemStorage.containsKey(item)) {
                        if(!itemStorage.get(item).isEmpty()) {
                            containsType = true;
                        }
                    }
                }
                isCompleteItems = containsType;
            }

        } else if(slot.getItem() instanceof IndependentItem independentItem) {
            isCompleteItems = false;
            if (itemStorage.containsKey(independentItem)) {
                if(!itemStorage.get(independentItem).isEmpty()) {
                    isCompleteItems = true;
                }
            }
        }
        return isCompleteItems;
    }

    /**
     * This method returns the item storage of the special vending machine
     * @return item storage of the special vending machine
     */
    public HashMap<NonCustomizableItem, List<NonCustomizableItem>> getItemStorage() {
        return itemStorage;
    }
}
