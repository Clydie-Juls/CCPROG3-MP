package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.NonCustomizable;

import java.util.HashMap;

/**
 * The StockEditInfo class represents the stock edit information for customizable and non-customizable items in the vending machine.
 */
public class StockEditInfo {
    private HashMap<NonCustomizable, ItemEditInfo[]> itemAmount;

    /**
     * The ItemEditInfo class represents the amount and checked status of an item in the stock edit information.
     */
    public static class ItemEditInfo {
        private Integer amount;
        private boolean isChecked;

        /**
         * Constructs an ItemEditInfo object with the default amount as 1 and checked status as true.
         */
        public ItemEditInfo() {
            amount = 1;
            isChecked = true;
        }

        /**
         * Retrieves the amount of the item.
         *
         * @return The amount of the item.
         */
        public Integer getAmount() {
            return amount;
        }

        /**
         * Sets the amount of the item.
         *
         * @param amount The amount of the item to be set.
         */
        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        /**
         * Checks if the item is marked as checked.
         *
         * @return True if the item is checked, false otherwise.
         */
        public boolean isChecked() {
            return isChecked;
        }

        /**
         * Sets the checked status of the item.
         *
         * @param checked True if the item should be marked as checked, false otherwise.
         */
        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }

    /**
     * Constructs a StockEditInfo object with an empty item amount map.
     */
    public StockEditInfo() {
        itemAmount = new HashMap<>();
    }

    /**
     * Retrieves the map of non-customizable items to their corresponding edit information.
     *
     * @return The map of non-customizable items to their edit information.
     */
    public HashMap<NonCustomizable, ItemEditInfo[]> getItemAmount() {
        return itemAmount;
    }
}
