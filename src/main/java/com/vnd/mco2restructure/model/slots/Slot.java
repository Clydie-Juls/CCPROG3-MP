package com.vnd.mco2restructure.model.slots;

import com.vnd.mco2restructure.model.items.Item;

/**
 * The Slot class represents a slot in the vending machine that holds an item.
 */
public abstract class Slot<T extends Item> {
    protected T item;
    private final int CAPACITY;

    /**
     * Constructs a Slot object with the specified capacity.
     *
     * @param capacity The maximum capacity of the slot for holding items.
     */
    public Slot(int capacity) {
        this.CAPACITY = capacity;
    }

    /**
     * Retrieves the capacity of the slot for holding items.
     *
     * @return The capacity of the slot/
     */
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Retrieves the item held in the slot.
     *
     * @return The item held in the slot.
     */
    public T getItem() {
        return item;
    }


    public abstract void putItem(T item);
}
