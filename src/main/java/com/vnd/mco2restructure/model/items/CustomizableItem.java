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

    @Override
    public int getPrice() {
        price = 0;
        for (NonCustomizableItem itemContent : itemContents) {
            price += itemContent.getPrice();
        }
        return price;
    }

    @Override
    public CustomizableItem clone() {
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        CustomizableItem customizableItem = (CustomizableItem) super.clone();
        this.itemContents = customizableItem.itemContents;
        this.itemsDerived = customizableItem.itemsDerived;
        return customizableItem;
    }
}
