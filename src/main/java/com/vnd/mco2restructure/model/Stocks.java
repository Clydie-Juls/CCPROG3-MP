package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.items.Item;

import java.util.ArrayList;

public class Stocks {
    private ArrayList<ItemEnum<? extends Item>> itemEnums;

    public Stocks() {
        this.itemEnums = new ArrayList<>();
    }

    public ArrayList<ItemEnum<? extends Item>> getItemEnums() {
        return itemEnums;
    }

    //TODO: Update this to an actual setter
    public void setItemEnums(int size) {
        this.itemEnums = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            itemEnums.add(null);
        }
    }
}
