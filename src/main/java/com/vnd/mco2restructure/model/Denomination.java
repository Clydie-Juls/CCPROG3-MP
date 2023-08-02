package com.vnd.mco2restructure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Denomination class represents the currency denominations used in the vending machine.
 */
public class Denomination {
    private final Map<Integer, ArrayList<Money>> CURRENCY;

    /**
     * Constructs a Denomination object with empty currency for each bill.
     */
    public Denomination() {
        CURRENCY = new LinkedHashMap<>();
        int[] currencyPlaceholder = new int[] {1000, 500, 200, 100, 50, 20, 10 ,5, 1};
        for (int i : currencyPlaceholder) {
            CURRENCY.put(i, new ArrayList<>(
                    Stream.generate(() -> new Money(i)).limit(10).collect(Collectors.toList())));
        }
    }

    /**
     * Processes the payment by checking if the vending machine has enough bills to provide the change.
     * If there is enough change, the payment is transferred, and the denominations are updated.
     *
     * @param payment    The user's payment.
     * @param totalPrice The total price of the products.
     * @return True if the payment process is successful, false otherwise.
     */
    public boolean processPayment(Map<Integer, ArrayList<Money>> payment, int totalPrice) {
        System.out.println("before " + payment);
        // calculates the change first
        int change = getTotalPayment(payment) - totalPrice;
        //stores previous data of payment and currency in case transaction process has failed
        Map<Integer, ArrayList<Money>> paymentHolder = new LinkedHashMap<>(payment);
        Map<Integer, ArrayList<Money>> currencyHolder = new LinkedHashMap<>(CURRENCY);
        ArrayList<ArrayList<Money>> changeDenomination;

        // If change is a non-negative number
        if (change >= 0) {
            // Transfer user payment to denomination currency
            transferPayment(payment);
            changeDenomination = Stream.generate(ArrayList<Money>::new).limit(CURRENCY.size()).
                    collect(Collectors.toCollection(ArrayList::new));
            System.out.println(changeDenomination.get(0) == changeDenomination.get(1));
            int changeToPass = change;
            int i = 0;
            /* Loops through each denomination in stable order then reduce each denomination by how much it
             * can actually decrease to the current change. */

            for (Map.Entry<Integer, ArrayList<Money>> entry : CURRENCY.entrySet()) {
                for (int j = 0; j < entry.getValue().size() && changeToPass - entry.getKey() >= 0
                        && entry.getValue().size() > changeDenomination.get(i).size(); j++) {
                    Money money = new Money(entry.getKey());
                    changeDenomination.get(i).add(money);
                    System.out.println(changeDenomination);
                    changeToPass -= entry.getKey();
                }
                i++;
                // If giving the change to the user is successful
                if (changeToPass == 0) {
                    i = 0;
                    // pass the change to the payment and update the denomination
                    for (Map.Entry<Integer, ArrayList<Money>> entry2 : CURRENCY.entrySet()) {
                        for (Money money : changeDenomination.get(i)) {
                            CURRENCY.get(entry2.getKey()).remove(CURRENCY.get(entry2.getKey()).size() - 1);
                            payment.get(entry2.getKey()).add(money);
                        }
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
    private void transferPayment(Map<Integer, ArrayList<Money>> payment) {
        for (Map.Entry<Integer, ArrayList<Money>> entry : payment.entrySet()) {
            CURRENCY.get(entry.getKey()).addAll(payment.get(entry.getKey()));
            payment.put(entry.getKey(), new ArrayList<>());
        }
    }

    /**
     * Returns the total amount of money in the vending machine and clears each denomination to 0.
     *
     * @return The total amount of money in the vending machine.
     */
    public int passDenomination() {
        int total = getTotalPayment(CURRENCY);
        CURRENCY.replaceAll((k, v) -> new ArrayList<>());
        return total;
    }

    /**
     * Gets the total amount of money in the payment.
     *
     * @param payment The user's payment.
     * @return The total amount of money in the payment.
     */
    private int getTotalPayment(Map<Integer, ArrayList<Money>> payment) {
        return payment.entrySet().stream().mapToInt(v -> v.getKey() * v.getValue().size()).sum();
    }

    /**
     * Retrieves the total amount of money in the vending machine.
     *
     * @return The total amount of money in the vending machine.
     */
    public int getTotalMoney() {
        return CURRENCY.entrySet().stream().mapToInt(v -> v.getKey() * v.getValue().size()).sum();
    }

    /**
     * Retrieves the currency denominations in the vending machine.
     *
     * @return The currency denominations.
     */
    public Map<Integer, ArrayList<Money>> getCurrency() {
        return CURRENCY;
    }
}
