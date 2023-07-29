package com.vnd.mco2restructure.model.slots;


import com.vnd.mco2restructure.model.items.Item;

public class IdSlot extends Slot<Item> {

    /**
     * Constructs a Slot object with the specified capacity.
     *
     * @param capacity The maximum capacity of the slot for holding items.
     */
    public IdSlot(int capacity) {
        super(capacity);
    }

    @Override
    public void putItem(Item item) {
        this.item = item;
    }

}
