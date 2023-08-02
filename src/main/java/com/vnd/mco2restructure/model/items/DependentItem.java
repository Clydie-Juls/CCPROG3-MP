package com.vnd.mco2restructure.model.items;

import com.vnd.mco2restructure.menu.DependentItemEnum;

/**
 * The DependentItem class represents a dependent item in the vending machine.
 * It extends the NonCustomizableItem class.
 */
public class DependentItem extends NonCustomizableItem {

    /**
     * Constructs a DependentItem object with the specified name, calories, id, price, and image file.
     *
     * @param name      The name of the dependent item.
     * @param calories  The number of calories in the dependent item.
     * @param id        The unique identifier of the dependent item.
     * @param price     The price of the dependent item.
     * @param imageFile The file path of the image for the dependent item.
     */
    public DependentItem(String name, int calories, int id, int price, String imageFile) {
        super(name, calories, id, price, imageFile);
    }

    /**
     * Gets the price of the dependent item.
     * The price is fetched from the corresponding DependentItemEnum based on the item's ID.
     *
     * @return The price of the dependent item.
     */
    @Override
    public int getPrice() {
        price = DependentItemEnum.values()[ID].getPrice();
        return price;
    }
}
