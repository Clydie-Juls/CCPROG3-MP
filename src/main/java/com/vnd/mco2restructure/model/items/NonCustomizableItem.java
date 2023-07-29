package com.vnd.mco2restructure.model.items;

public class NonCustomizableItem extends Item{

    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     * @param calories The number of calories in the item.
     * @param price    The price of the item.
     */
    public NonCustomizableItem(String name, int calories, int id, int price) {
        super(name, calories, id, price);
    }
}
