package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.items.Item;

import java.util.ArrayList;

public class Stocks {
    private ArrayList<ItemEnum<? extends Item>> itemEnums;
    private ArrayList<StockEditInfo> stockEditInfos;
    public Stocks() {
        this.itemEnums = new ArrayList<>();
        stockEditInfos = new ArrayList<>();
    }

    public ArrayList<ItemEnum<? extends Item>> getItemEnums() {
        return itemEnums;
    }

    public void setItemData(int size) {
        this.itemEnums = new ArrayList<>();
        this.stockEditInfos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            itemEnums.add(null);
            stockEditInfos.add(null);
        }
    }

    public ArrayList<StockEditInfo> getStockEditInfos() {
        return stockEditInfos;
    }
}
