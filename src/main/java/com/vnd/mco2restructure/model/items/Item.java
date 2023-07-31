package com.vnd.mco2restructure.model.items;

import java.util.Objects;

/**
 * The Item class represents an item in the vending machine.
 */
public abstract class Item implements Cloneable {
    private final String NAME;
    private int calories;
    protected final int ID;
    protected int price;
    private String imageFile;


    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     * @param calories The number of calories in the item.
     * @param price    The price of the item.
     */
    public Item(String name, int calories, int id, int price, String imageFile) {
        this.NAME = name;
        this.calories = calories;
        this.ID = id;
        this.price = price;
        this.imageFile = imageFile;
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
        return calories;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public abstract int getPrice();

    public int getUnchangedPrice() {
        return price;
    }

    public int getId() {
        return ID;
    }

    public String getImageFile() {
        return imageFile;
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

    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Returns the item name and its calories.
     * @return  The item name and its calories.
     */
    @Override
    public String toString() {
        return NAME + " - " + calories + " cal";
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

    @Override
    public Item clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
