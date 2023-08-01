package com.vnd.mco2restructure.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Denomination class represents the currency denominations used in the vending machine.
 */
public class Denomination {
    private final Map<Integer, Integer> CURRENCY;

    /**
     * Constructs a Denomination object with empty currency for each bill.
     */
    public Denomination() {
        CURRENCY = new LinkedHashMap<>();
        CURRENCY.put(1000, 10);
        CURRENCY.put(500, 10);
        CURRENCY.put(200, 10);
        CURRENCY.put(100, 10);
        CURRENCY.put(50, 10);
        CURRENCY.put(20, 10);
        CURRENCY.put(10, 10);
        CURRENCY.put(5, 10);
        CURRENCY.put(1, 10);
    }

    /**
     * Processes the payment by checking if the vending machine has enough bills to provide the change.
     * If there is enough change, the payment is transferred, and the denominations are updated.
     *
     * @param payment    The user's payment.
     * @param totalPrice The total price of the products.
     * @return True if the payment process is successful, false otherwise.
     */
    public boolean processPayment(Map<Integer, Integer> payment, int totalPrice) {
        // Calculate the change first
        int change = getTotalPayment(payment) - totalPrice;
        // Store previous data of payment and currency in case the transaction process fails
        Map<Integer, Integer> paymentHolder = new LinkedHashMap<>(payment);
        Map<Integer, Integer> currencyHolder = new LinkedHashMap<>(CURRENCY);
        int[] changeDenomination;

        // If change is a non-negative number
        if (change >= 0) {
            // Transfer user payment to denomination currency
            transferPayment(payment);
            changeDenomination = new int[CURRENCY.size()];
            int changeToPass = change;
            int i = 0;
            /* Loops through each denomination in stable order then reduce each denomination by how much it
             * can actually decrease to the current change. */

            for (Map.Entry<Integer, Integer> entry : CURRENCY.entrySet()) {
                for (int j = 0; j < entry.getValue() && changeToPass - entry.getKey() >= 0
                        && entry.getValue() > changeDenomination[i]; j++) {
                    changeDenomination[i]++;
                    changeToPass -= entry.getKey();
                }
                i++;
                // If giving the change to the user is successful
                if (changeToPass == 0) {
                    i = 0;
                    // Pass the change to the payment and update the denomination
                    for (Map.Entry<Integer, Integer> entry2 : CURRENCY.entrySet()) {
                        int newNo = entry2.getValue() - changeDenomination[i];
                        CURRENCY.put(entry2.getKey(), newNo);
                        payment.put(entry2.getKey(), changeDenomination[i]);
                        i++;
                    }
                    return true;
                }
            }
        }

        // If the transaction process failed, restore previous data
        payment.putAll(paymentHolder);
        CURRENCY.putAll(currencyHolder);
        return false;
    }

    /**
     * Transfers the user's payment to the vending machine's currency.
     *
     * @param payment The user's payment.
     */
    private void transferPayment(Map<Integer, Integer> payment) {
        for (Map.Entry<Integer, Integer> entry : payment.entrySet()) {
            int entryAmount = CURRENCY.get(entry.getKey());
            CURRENCY.put(entry.getKey(), entryAmount + entry.getValue());
            payment.put(entry.getKey(), 0);
        }
    }

    /**
     * Returns the total amount of money in the vending machine and clears each denomination to 0.
     *
     * @return The total amount of money in the vending machine.
     */
    public int passDenomination() {
        int total = getTotalPayment(CURRENCY);
        CURRENCY.replaceAll((k, v) -> 0);
        return total;
    }

    /**
     * Gets the total amount of money in the payment.
     *
     * @param payment The user's payment.
     * @return The total amount of money in the payment.
     */
    private int getTotalPayment(Map<Integer, Integer> payment) {
        return payment.entrySet().stream().mapToInt(v -> v.getKey() * v.getValue()).sum();
    }

    /**
     * Retrieves the total amount of money in the vending machine.
     *
     * @return The total amount of money in the vending machine.
     */
    public int getTotalMoney() {
        return CURRENCY.entrySet().stream().mapToInt(v -> v.getKey() * v.getValue()).sum();
    }

    /**
     * Retrieves the currency denominations in the vending machine.
     *
     * @return The currency denominations.
     */
    public Map<Integer, Integer> getCurrency() {
        return CURRENCY;
    }
}
