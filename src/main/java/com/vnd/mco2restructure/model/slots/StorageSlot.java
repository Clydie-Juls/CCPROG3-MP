package com.vnd.mco2restructure.model.slots;


import com.vnd.mco2restructure.model.items.NonCustomizableItem;

import java.util.ArrayDeque;

public class StorageSlot<T extends NonCustomizableItem> extends Slot<T>{

    private final ArrayDeque<T> itemStack;
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
    public void putItem(T item) {
        if(itemStack.isEmpty()) {
            itemStack.add(item);
        } else if(item.equals(itemStack.peek())) {
            itemStack.add(item);
        }
    }

    public T giveItem() {
        return itemStack.pollLast();
    }
}
