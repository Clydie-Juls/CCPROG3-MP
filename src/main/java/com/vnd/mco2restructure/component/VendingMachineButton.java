package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import com.vnd.mco2restructure.controller.VendingMachineButtonController;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Represents a custom HBox component for a vending machine button.
 */
public class VendingMachineButton extends HBox {
    /**
     * Initalizes a vending machine button gui
     * @param vndName - name of the vending machine
     * @param vndType - type of the vending machine(regular or special)
     * @param noOfSlots - no of slots the vending machine has
     * @param slotCapacity - capacity of each slot
     */
    public VendingMachineButton(@NamedArg("vndName") String vndName, @NamedArg("vndType") String vndType,
                                int noOfSlots, int slotCapacity) {
        setView(vndName, vndType, noOfSlots, slotCapacity);
    }

    private void setView(String vndName, String vndType, int noOfSlots, int slotCapacity) {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/VendingMachineButton.fxml"));
        view.setRoot(this);

        try {
            view.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        VendingMachineButtonController vendingMachineButtonController = view.getController();
        vendingMachineButtonController.initialize(vndName, vndType, noOfSlots, slotCapacity);
    }
}
