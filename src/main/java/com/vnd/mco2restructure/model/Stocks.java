package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.items.Item;

import java.util.ArrayList;

/**
 * The Stocks class represents the stocks of items in the vending machine.
 */
public class Stocks {
    private ArrayList<ItemEnum<? extends Item>> itemEnums;
    private ArrayList<StockEditInfo> stockEditInfos;

    /**
     * Constructs a Stocks object with empty lists for item enums and stock edit information.
     */
    public Stocks() {
        this.itemEnums = new ArrayList<>();
        stockEditInfos = new ArrayList<>();
    }

    /**
     * Retrieves the list of item enums.
     *
     * @return The list of item enums.
     */
    public ArrayList<ItemEnum<? extends Item>> getItemEnums() {
        return itemEnums;
    }

    /**
     * Sets the item data for the stocks.
     *
     * @param size The size of the item data to be set.
     */
    public void setItemData(int size) {
        this.itemEnums = new ArrayList<>();
        this.stockEditInfos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            itemEnums.add(null);
            stockEditInfos.add(null);
        }
    }

    /**
     * Retrieves the list of stock edit information.
     *
     * @return The list of stock edit information.
     */
    public ArrayList<StockEditInfo> getStockEditInfos() {
        return stockEditInfos;
    }
}
