package com.vnd.mco2restructure.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VendingMachineButtonController {

    @FXML  public Label vndNameLabel;
    @FXML public Label vndTypeLabel;
    @FXML private Label noOfSlotsLabel;
    @FXML private Label slotCapacityLabel;

    public void initialize(String vndName, String vndType, int noOfSlots, int slotCapacity) {
        vndNameLabel.setText(vndName);
        vndTypeLabel.setText(vndType + " Vending Machine");
        noOfSlotsLabel.setText("No. of Slots: " + noOfSlots);
        slotCapacityLabel.setText("Slot Capacity: " + slotCapacity);
    }


}
