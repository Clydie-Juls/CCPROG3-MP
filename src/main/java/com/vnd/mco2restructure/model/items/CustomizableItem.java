package com.vnd.mco2restructure.model.items;

import java.util.Arrays;

public class CustomizableItem extends Item{

    NonCustomizableItem[] itemsDerived;
    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     */
    public CustomizableItem(String name, NonCustomizableItem[] itemsDerived, int id) {
        super(name,
                Arrays.stream(itemsDerived).mapToInt(Item::getCalories).sum(), id,
                Arrays.stream(itemsDerived).mapToInt(Item::getPrice).sum());
        this.itemsDerived = itemsDerived;

    }
}
