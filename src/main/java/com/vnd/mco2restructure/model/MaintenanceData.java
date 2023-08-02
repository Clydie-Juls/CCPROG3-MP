package com.vnd.mco2restructure.model;

/**
 * The MaintenanceData class holds the maintenance's data.
 */
public class MaintenanceData {
    private int totalMoney;

    /**
     * Initializes the MaintenanceData and its total money.
     */
    public MaintenanceData() {
        totalMoney = 0;
    }

    /**
     * Retrieves the maintenance's total money collected.
     *
     * @return Total money collected.
     */
    public int getTotalMoney() {
        return totalMoney;
    }

    /**
     * Sets the total money collected.
     *
     * @param totalMoney Total money collected.
     */
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
