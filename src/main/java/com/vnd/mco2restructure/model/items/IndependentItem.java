package com.vnd.mco2restructure.model.items;

import com.vnd.mco2restructure.menu.IndependentItemEnum;

/**
 * The IndependentItem class represents an independent item in the vending machine.
 * It extends the NonCustomizableItem class.
 */
public class IndependentItem extends NonCustomizableItem {

    /**
     * Constructs an IndependentItem object with the specified name, calories, id, price, and image file.
     *
     * @param name      The name of the independent item.
     * @param calories  The number of calories in the independent item.
     * @param id        The unique identifier of the independent item.
     * @param price     The price of the independent item.
     * @param imageFile The file path of the image for the independent item.
     */
    public IndependentItem(String name, int calories, int id, int price, String imageFile) {
        super(name, calories, id, price, imageFile);
    }

    /**
     * Gets the price of the independent item.
     * The price is fetched from the corresponding IndependentItemEnum based on the item's ID.
     *
     * @return The price of the independent item.
     */
    @Override
    public int getPrice() {
        price = IndependentItemEnum.values()[ID].getPrice();
        return price;
    }
}
