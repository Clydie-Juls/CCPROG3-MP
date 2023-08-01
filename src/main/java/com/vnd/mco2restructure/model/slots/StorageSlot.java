package com.vnd.mco2restructure.model.slots;

import com.vnd.mco2restructure.model.items.NonCustomizableItem;

import java.util.ArrayDeque;

/**
 * The StorageSlot class represents a slot in the vending machine's storage that holds non-customizable items.
 */
public class StorageSlot extends Slot<NonCustomizableItem> {
    private final ArrayDeque<NonCustomizableItem> itemStack;

    /**
     * Constructs a StorageSlot object with the specified capacity.
     *
     * @param capacity The maximum capacity of the slot for holding items.
     */
    public StorageSlot(int capacity) {
        super(capacity);
        itemStack = new ArrayDeque<>();
    }

    /**
     * Puts an item into the storage slot.
     * If the item is the same as the one already in the slot, it is stacked on top.
     *
     * @param item The non-customizable item to be put into the slot.
     */
    @Override
    public void putItem(NonCustomizableItem item) {
        if (itemStack.isEmpty() || item.equals(itemStack.peek())) {
            itemStack.add(item);
            super.item = item;
        }
    }

    /**
     * Removes and returns the top item from the storage slot.
     * If there's only one item left, it will also clear the slot.
     *
     * @return The top non-customizable item in the slot.
     */
    public NonCustomizableItem giveItem() {
        if (itemStack.size() == 1) {
            item = null;
        }
        return itemStack.pollLast();
    }

    /**
     * Retrieves the number of items stacked in the storage slot.
     *
     * @return The count of items stacked in the slot.
     */
    public int getItemStackCount() {
        return itemStack.size();
    }
}
