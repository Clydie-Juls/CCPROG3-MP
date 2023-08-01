package com.vnd.mco2restructure.controller;


import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemInterface;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.slots.Slot;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The VendingMachineController class represents the controller for the vending machine view.
 * It handles the vending machine actions such as buying items and updating the view.
 */
public class VendingMachineController implements Initializable {

    private WindowManager windowManager;
    private VendingMachine vendingMachine;
    @FXML private SlidePopup slidePopup;
    @FXML private FlowPane itemLayout;

    public VendingMachineController() {
    }

    /**
     * Buys an existing item of the vending machine. If the transaction process failed(i.e. not enough denomination) or
     * there is not enough item, the buy process will fail. Else, it will pass the items the user bought.
     * 
     * @param payment  Users payment.
     * @param slotNo  Slot number of the vending machine.
     * @return  array of items the user bought if successful, returns null otherwise.
     */
    public Item buy(LinkedHashMap<Integer, Integer> payment, int slotNo) {

            Slot<? extends Item> selectedSlot = vendingMachine.getSlots()[slotNo];
            // if transactions process has failed
            if (vendingMachine.getDenomination().processPayment(payment,
                    selectedSlot.getItem().getPrice())) {

                Item dispensedItem = vendingMachine.dispenseItem(slotNo);

                // If dispense item process is successful
                if(dispensedItem != null) {
                    vendingMachine.getTransactions().updateTableAfterBuy(dispensedItem);
                    return dispensedItem;
                }
            }

        return null;
    }


    /**
     * Gets the vending machine's instance.
     *
     * @return The vending machine's instance.
     */
    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    /**
     * Retrieves the price of an existing item in the specified slot.
     *
     * @param slotNo    Slot number of the vending machine.
     * @return The price of the existing item, or -1 if slot number is out of bounds or the slot has no item.
     */
    public int getItemPrice(int slotNo) {
        // If slotNo is out of bounds
        if (slotNo - 1 < 0 || slotNo - 1 >= vendingMachine.getSlots().length) {
            return -1;
            // If Slot object has no existing item
        } else if(vendingMachine.getSlots()[slotNo - 1].getItem() == null) {
            return -1;
        }
        return vendingMachine.getSlots()[slotNo - 1].getItem().getPrice();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemInterface itemInterface = new ItemInterface();
        itemInterface.setOnMouseClicked(event -> {
            slidePopup.slideUpAnimation();
        });
        itemLayout.getChildren().add(itemInterface);
    }

    /**
     * Updates the view of the vending machine with the current slot items.
     */
    public void updateView() {
        itemLayout.getChildren().clear();
        for (int i = 0; i < vendingMachine.getSlots().length; i++) {
            if (vendingMachine.hasStock(vendingMachine.getSlots()[i])) {
                ItemInterface itemInterface = new ItemInterface();
                itemInterface.getItemNameLabel().setText(vendingMachine.getSlots()[i].getItem().getName());
                itemInterface.getItemImageView().setImage(new Image(
                        Objects.requireNonNull(HelloApplication.class.getResourceAsStream(
                                vendingMachine.getSlots()[i].getItem().getImageFile()))));
                int finalI = i;
                itemInterface.setOnMouseClicked(event -> windowManager.gotoItemBuyView(finalI));

                itemLayout.getChildren().add(itemInterface);
            }
        }
    }

    /**
     * Sets the vending machine instance for the controller.
     *
     * @param vendingMachine The vending machine instance to set.
     */
    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    /**
     * Sets the WindowManager instance for the controller.
     *
     * @param windowManager The WindowManager instance to set.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
