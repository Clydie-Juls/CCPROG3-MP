package com.vnd.mco2restructure.model.items;

public class DependentItem extends NonCustomizableItem{
    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     * @param calories The number of calories in the item.
     * @param price    The price of the item.
     */
    public DependentItem(String name, int calories, int id, int price, String imageFile) {
        super(name, calories, id, price, imageFile);
    }
}
