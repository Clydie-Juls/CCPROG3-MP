package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.model.items.Item;

import java.util.*;

/**
 * The Transactions class represents a log of item transactions in the vending machine.
 */
public class Transactions {
    private final List<List<Item>> ITEM_LOGS;

    /**
     * Constructs a Transactions object with an initial item log.
     */
    public Transactions() {
        this.ITEM_LOGS = new ArrayList<>();
        ITEM_LOGS.add(new ArrayList<>());
    }

    /**
     * Adds a transaction for the specified item to the current item log.
     *
     * @param item The item involved in the transaction.
     */
    public void addTransaction(Item item) {
        List<Item> currentLog = ITEM_LOGS.get(ITEM_LOGS.size() - 1);
        currentLog.add(item);
    }

    /**
     * Creates a new item log for recording future transactions.
     */
    public void resetTransactions() {
        ITEM_LOGS.add(new ArrayList<>());
    }

    /**
     * Retrieves the list of item logs representing the transaction history.
     *
     * @return The list of item logs.
     */
    public List<List<Item>> getItemLogs() {
        return ITEM_LOGS;
    }
}
