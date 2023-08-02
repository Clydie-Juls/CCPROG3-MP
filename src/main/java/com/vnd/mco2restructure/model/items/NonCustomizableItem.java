package com.vnd.mco2restructure.model.items;

/**
 * The NonCustomizableItem class represents a non-customizable item in the vending machine.
 * Non-customizable items are items that cannot be modified or customized by the user.
 */
public abstract class NonCustomizableItem extends Item {

    /**
     * Constructs a NonCustomizableItem object with the specified name, calories, id, price, and image file.
     *
     * @param name      The name of the item.
     * @param calories  The number of calories in the item.
     * @param id        The unique identifier of the item.
     * @param price     The price of the item.
     * @param imageFile The file path of the image for the item.
     */
    public NonCustomizableItem(String name, int calories, int id, int price, String imageFile) {
        super(name, calories, id, price, imageFile);
    }
}
