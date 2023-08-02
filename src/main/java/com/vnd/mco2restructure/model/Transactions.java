package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.*;

/**
 * The Transactions class represents a log of item transactions in the vending machine.
 */
public class Transactions {
    private final ObservableMap<String, ItemTransactData> ITEM_LOGS;

    /**
     * Constructs a Transactions object with an initial item log.
     */
    public Transactions() {
        this.ITEM_LOGS = FXCollections.observableHashMap();
    }

    /**
     * Adds a transaction for the specified item to the current item log.
     *
     * @param item The item involved in the transaction.
     * @param itemStockAmountDetail stock of an item in string form
     */
    public void addTransaction(Item item, String itemStockAmountDetail) {
        if(!ITEM_LOGS.containsKey(item.getName())) {
            String itemType = item instanceof CustomizableItem ? "Customizable" : item instanceof IndependentItem ?
            "Independent" : "Dependent";
            String prevStock = "0";
            boolean isDependent = item instanceof DependentItem;
            if(item instanceof CustomizableItem) {
                prevStock = null;
            }

            ItemTransactData itemTransactData = new ItemTransactData(item.getName(), itemType, prevStock,
                    prevStock, isDependent ? null : 0, isDependent ? null : 0);
            itemTransactData.setItemDuringStock(itemStockAmountDetail);
            ITEM_LOGS.put(item.getName(), itemTransactData);
        } else {
            ITEM_LOGS.get(item.getName()).setItemDuringStock(itemStockAmountDetail);
        }
    }

    /**
     * Creates a new item log for recording future transactions.
     * @param items items to update
     */
    public void UpdateStockTransactionInfo(Set<String> items) {
        for (String item : items) {
            ITEM_LOGS.get(item).setItemPrevStock(ITEM_LOGS.get(item).getItemInStock());
            ITEM_LOGS.get(item).setItemInStock(ITEM_LOGS.get(item).getItemDuringStock());
        }
    }

    /**
     * Updates the table after the user buys an item
     * @param item item to update
     */
    public void updateTableAfterBuy(Item item) {
        if(item instanceof CustomizableItem customizableItem) {
            for (NonCustomizableItem content : customizableItem.getItemContents()) {
                ITEM_LOGS.get(content.getName()).setItemInStock(" " +
                        (Integer.parseInt(ITEM_LOGS.get(content.getName()).getItemInStock() ) - 1));
            }
        } else {
            ITEM_LOGS.get(item.getName()).setItemInStock(" " +
                    (Integer.parseInt(ITEM_LOGS.get(item.getName()).getItemInStock() ) - 1));
        }
        ITEM_LOGS.get(item.getName()).setNoOfItemSold(ITEM_LOGS.get(item.getName()).getNoOfItemSold() + 1);
        ITEM_LOGS.get(item.getName()).setProfitCollected(ITEM_LOGS.get(item.getName()).getProfitCollected()
                + item.getPrice());

    }

    /**
     * Retrieves the list of item logs representing the transaction history.
     *
     * @return The list of item logs.
     */
    public ObservableMap<String, ItemTransactData> getItemLogs() {
        return ITEM_LOGS;
    }
}
