package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CollectMoneyController {

    private WindowManager windowManager;
    @FXML private Label moneyLabel;

    public void updateView(int totalMoney) {
        moneyLabel.setText("Would you like to collect" + totalMoney + " from the vending machine?");
    }

    public void collectMoney() {
        windowManager.collectMoney();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
