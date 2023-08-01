package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.Item;

/**
 * The ItemEnum interface represents an enumeration of items in the vending machine.
 *
 * @param <T> The type of item that this enumeration represents.
 */
public interface ItemEnum<T extends Item> {

    /**
     * Converts the enum to an instance of the item type.
     *
     * @return The instance of the item type.
     */
    T enumToItem();
}
