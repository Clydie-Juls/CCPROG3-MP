package com.vnd.mco2restructure.model;

import java.util.Objects;

/**
 * Represents a simple abstract money
 */
public class Money {
    private final int amount;

    /**
     * Intializes the currency of the money
     * @param amount currency of the money
     */
    public Money(int amount) {
        this.amount = amount;
    }

    /**
     * This method returns the currency of the money
     * @return currency of the money
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the amount in string form
     * @return amount in string form
     */
    @Override
    public String toString() {
        return "" + amount;
    }

    /**
     * Checks if two money have the same amount
     * @param o the money to compare
     * @return true if both money has the same amount, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    /**
     * This method returns the hashcode of the amount
     * @return hashcode of the amount
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
