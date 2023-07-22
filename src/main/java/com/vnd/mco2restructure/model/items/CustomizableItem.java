package com.vnd.mco2restructure.model.items;

public class CustomizableItem extends Item{

    NonCustomizableItem[] itemsDerived;
    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     * @param calories The number of calories in the item.
     * @param price    The price of the item.
     */
    public CustomizableItem(String name, int calories, int price, NonCustomizableItem[] itemsDerived) {
        super(name, calories, price);
        this.itemsDerived = itemsDerived;
    }
}
