package com.vnd.mco2restructure.model.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CustomizableItem extends Item{

    private HashMap<String, NonCustomizableItem[]> itemsDerived;
    private NonCustomizableItem[] itemContents;
    /**
     * Constructs an Item object with the specified name, calories, and price.
     *
     * @param name     The name of the item.
     */
    public CustomizableItem(String name,  HashMap<String, NonCustomizableItem[]> itemsDerived, int id, String imageFile) {
        super(name, 0, id, 0, imageFile);
        this.itemsDerived = itemsDerived;
    }

    public HashMap<String, NonCustomizableItem[]> getItemsDerived() {
        return itemsDerived;
    }

    public NonCustomizableItem[] getItemContents() {
        return itemContents;
    }

    public void setItemContents(NonCustomizableItem[] itemContents) {
        this.itemContents = itemContents;
        setPrice(Arrays.stream(itemContents).mapToInt(Item::getPrice).sum());
        setCalories(Arrays.stream(itemContents).mapToInt(Item::getCalories).sum());
    }
}
