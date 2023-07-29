package com.vnd.mco2restructure.model.items;

import java.util.Objects;

/**
 * The Item class represents an item in the vending machine.
 */
public abstract class Item {
    private final String NAME;
    private final int CALORIES;
    private final int ID;
    private int price;


    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     * @param calories The number of calories in the item.
     * @param price    The price of the item.
     */
    public Item(String name, int calories, int id, int price) {
        this.NAME = name;
        this.CALORIES = calories;
        this.ID = id;
        this.price = price;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return NAME;
    }

    /**
     * Retrieves the number of calories in the item.
     *
     * @return The number of calories in the item.
     */
    public int getCalories() {
        return CALORIES;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public int getPrice() {
        return price;
    }

    public int getId() {
        return ID;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The price of the item to be set.
     */
    public void setPrice(int price) {
        if (price > 0) {
            this.price = price;
        }
    }

    /**
     * Returns the item name and its calories.
     * @return  The item name and its calories.
     */
    @Override
    public String toString() {
        return NAME + " - " + CALORIES + " cal";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(NAME, item.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME);
    }
}
