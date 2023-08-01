package com.vnd.mco2restructure.model.slots;

import com.vnd.mco2restructure.model.items.Item;

/**
 * The IdSlot class represents a slot in the vending machine that can hold an item.
 * This slot uses the item's ID as the key identifier for storing and retrieving items.
 *
 * @param <T> The type of item that the slot can hold.
 */
public class IdSlot<T extends Item> extends Slot<T> {

    /**
     * Constructs an IdSlot object with the specified capacity.
     *
     * @param capacity The maximum capacity of the slot for holding items.
     */
    public IdSlot(int capacity) {
        super(capacity);
    }

    /**
     * Puts an item into the slot.
     *
     * @param item The item to be put into the slot.
     */
    @Override
    public void putItem(T item) {
        this.item = item;
    }
}
