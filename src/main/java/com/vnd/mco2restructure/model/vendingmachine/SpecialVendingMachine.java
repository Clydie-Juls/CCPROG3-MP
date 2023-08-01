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

public class SpecialVendingMachine extends VendingMachine{
    private HashMap<NonCustomizableItem, List<NonCustomizableItem>> itemStorage;

    public SpecialVendingMachine() {
        this(8, 10);
    }

    public SpecialVendingMachine(int noOfSlots, int itemCapacity) {
        super(noOfSlots);
        itemStorage = new HashMap<>();
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new IdSlot(itemCapacity);
        }
    }

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

    public HashMap<NonCustomizableItem, List<NonCustomizableItem>> getItemStorage() {
        return itemStorage;
    }
}
