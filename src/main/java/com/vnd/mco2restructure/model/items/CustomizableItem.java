package com.vnd.mco2restructure.model.items;

import java.util.HashMap;
import java.util.Arrays;

/**
 * The CustomizableItem class represents a customizable item in the vending machine.
 * It extends the Item class and implements the Cloneable interface.
 */
public class CustomizableItem extends Item {

    private HashMap<String, NonCustomizableItem[]> itemsDerived;
    private NonCustomizableItem[] itemContents;

    /**
     * Constructs a CustomizableItem object with the specified name, itemsDerived, id, and image file.
     *
     * @param name         The name of the customizable item.
     * @param itemsDerived The mapping of item types to their corresponding non-customizable items.
     * @param id           The unique identifier of the customizable item.
     * @param imageFile    The file path of the image for the customizable item.
     */
    public CustomizableItem(String name, HashMap<String, NonCustomizableItem[]> itemsDerived, int id, String imageFile) {
        super(name, 0, id, 0, imageFile);
        this.itemsDerived = itemsDerived;
    }

    /**
     * Gets the mapping of item types to their corresponding non-customizable items.
     *
     * @return The mapping of item types to their corresponding non-customizable items.
     */
    public HashMap<String, NonCustomizableItem[]> getItemsDerived() {
        return itemsDerived;
    }

    /**
     * Gets the array of non-customizable items contained in the customizable item.
     *
     * @return The array of non-customizable items.
     */
    public NonCustomizableItem[] getItemContents() {
        return itemContents;
    }

    /**
     * Sets the array of non-customizable items contained in the customizable item.
     * It also updates the total price and calories of the customizable item based on the item contents.
     *
     * @param itemContents The array of non-customizable items to be set for the customizable item.
     */
    public void setItemContents(NonCustomizableItem[] itemContents) {
        this.itemContents = itemContents;
        setPrice(Arrays.stream(itemContents).mapToInt(Item::getPrice).sum());
        setCalories(Arrays.stream(itemContents).mapToInt(Item::getCalories).sum());
    }

    /**
     * Gets the total price of the customizable item based on the item contents.
     * The price is calculated as the sum of the prices of all non-customizable items.
     *
     * @return The total price of the customizable item.
     */
    @Override
    public int getPrice() {
        price = 0;
        for (NonCustomizableItem itemContent : itemContents) {
            price += itemContent.getPrice();
        }
        return price;
    }

    /**
     * Creates a clone of the customizable item.
     * It also copies the mutable state to prevent changes to the original item.
     *
     * @return The cloned CustomizableItem object.
     */
    @Override
    public CustomizableItem clone() {
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        CustomizableItem customizableItem = (CustomizableItem) super.clone();
        this.itemContents = customizableItem.itemContents;
        this.itemsDerived = customizableItem.itemsDerived;
        return customizableItem;
    }
}
