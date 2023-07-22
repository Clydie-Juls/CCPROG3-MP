package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.model.items.Item;

import java.util.*;

/**
 * The Transactions class represents a log of item transactions in the vending machine.
 */
public class Transactions {
    private final List<Map<Item, Integer>> ITEM_LOGS;

    /**
     * Constructs a Transactions object with an initial item log.
     */
    public Transactions() {
        this.ITEM_LOGS = new ArrayList<>();
        ITEM_LOGS.add(new HashMap<>());
    }

    /**
     * Adds a transaction for the specified item and quantity to the current item log.
     *
     * @param item     The item involved in the transaction.
     * @param quantity The quantity of the item involved in the transaction.
     */
    public void addTransaction(Item item, int quantity) {
        //TODO: Create a new instance of the item(new Item will not work)
        Item newItem = item;
        Map<Item, Integer> currentLog = ITEM_LOGS.get(ITEM_LOGS.size() - 1);
        currentLog.put(newItem, quantity);
    }

    /**
     * Creates a new item log for recording future transactions.
     */
    public void resetTransactions() {
        ITEM_LOGS.add(new LinkedHashMap<>());
    }

    /**
     * Retrieves the list of item logs representing the transaction history.
     *
     * @return The list of item logs.
     */
    public List<Map<Item, Integer>> getItemLogs() {
        return ITEM_LOGS;
    }
}
