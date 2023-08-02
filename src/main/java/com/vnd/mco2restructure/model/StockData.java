package com.vnd.mco2restructure.model;

/**
 * The StockData class represents the stock data of a slot in the vending machine.
 */
public class StockData {
    private int slotId;

    /**
     * Retrieves the slot ID.
     *
     * @return The slot ID.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the slot ID.
     *
     * @param slotId The slot ID to be set.
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
