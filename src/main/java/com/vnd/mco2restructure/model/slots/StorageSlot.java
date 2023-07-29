package com.vnd.mco2restructure.model.slots;


import com.vnd.mco2restructure.model.items.NonCustomizableItem;

import java.util.ArrayDeque;

public class StorageSlot extends Slot<NonCustomizableItem>{

    private final ArrayDeque<NonCustomizableItem> itemStack;
    /**
     * Constructs a Slot object with the specified capacity.
     *
     * @param capacity The maximum capacity of the slot for holding items.
     */
    public StorageSlot(int capacity) {
        super(capacity);
        itemStack = new ArrayDeque<>();
    }

    @Override
    public void putItem(NonCustomizableItem item) {
        if(itemStack.isEmpty()) {
            itemStack.add(item);
            super.item = item;
        } else if(item.equals(itemStack.peek())) {
            itemStack.add(item);
            super.item = item;
        }
    }

    public NonCustomizableItem giveItem() {
        return itemStack.pollLast();
    }

    public int getItemStackCount() {
        return itemStack.size();
    }
}
