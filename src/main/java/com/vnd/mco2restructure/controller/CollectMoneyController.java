package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the collect money view.
 */
public class CollectMoneyController {

    private WindowManager windowManager;
    @FXML private Label moneyLabel;

    /**
     * Updates the view to display the total money to be collected.
     *
     * @param totalMoney The total amount of money to be collected.
     */
    public void updateView(int totalMoney) {
        moneyLabel.setText("Would you like to collect " + totalMoney + " from the vending machine?");
    }

    /**
     * Handles the action when the "Collect Money" button is clicked.
     * Collects the money from the vending machine.
     */
    public void collectMoney() {
        windowManager.collectMoney();
    }

    /**
     * Sets the WindowManager for this controller.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
