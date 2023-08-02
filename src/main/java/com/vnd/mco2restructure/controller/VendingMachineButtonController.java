package com.vnd.mco2restructure.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * The VendingMachineButtonController class represents a controller for the vending machine button view.
 * It initializes and displays information about the vending machine.
 */
public class VendingMachineButtonController {

    /**
     * Name label of the vending machine
     */
    @FXML public Label vndNameLabel;
    /**
     * type label of the vending machine
     */
    @FXML public Label vndTypeLabel;
    @FXML private Label noOfSlotsLabel;
    @FXML private Label slotCapacityLabel;

    /**
     * Initializes the vending machine button view with the provided information.
     *
     * @param vndName    The name of the vending machine.
     * @param vndType    The type of the vending machine.
     * @param noOfSlots  The number of slots in the vending machine.
     * @param slotCapacity The capacity of each slot in the vending machine.
     */
    public void initialize(String vndName, String vndType, int noOfSlots, int slotCapacity) {
        vndNameLabel.setText(vndName);
        vndTypeLabel.setText(vndType + " Vending Machine");
        noOfSlotsLabel.setText("No. of Slots: " + noOfSlots);
        slotCapacityLabel.setText("Slot Capacity: " + slotCapacity);
    }
}
