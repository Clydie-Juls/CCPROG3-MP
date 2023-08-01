package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.component.VendingMachineButton;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.model.MaintenanceData;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controller class for the home view.
 */
public class HomeController {
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML private SlidePopup slidePopup;
    @FXML private TextField vndNameTextField;
    @FXML private ComboBox<String> vndTypeChoice;
    @FXML private VBox vndMachineButtonsLayout;
    @FXML private NumberField noOfSlotsNumberField;
    @FXML private NumberField slotCapacityNumberField;

    /**
     * Handles the action when the "Show Popup" button is clicked.
     * Shows the slide popup.
     */
    @FXML
    private void showPopup() {
        slidePopup.slideUpAnimation();
    }

    /**
     * Handles the action when the "Add Vending Machine" button is clicked.
     * Adds a new vending machine based on the entered data.
     */
    @FXML
    private void addVendingMachine() {
        int noOfSlots = Math.max(8, ((NumberFieldController) noOfSlotsNumberField.getLoader().getController()).getValue());
        int slotCapacity = Math.max(10, ((NumberFieldController) slotCapacityNumberField.getLoader().getController()).getValue());

        VendingMachineButton vendingMachineButton = new VendingMachineButton(
                vndNameTextField.getText().isEmpty() ? "Vending Machine " +
                        (programData.getVendingMachines().size() + 1) : vndNameTextField.getText(),
                vndTypeChoice.getValue(), noOfSlots, slotCapacity
        );

        vendingMachineButton.setOnMouseClicked(event -> {
            programData.setCurrentVendingMachine(programData.getVendingMachines().get(vendingMachineButton));
            programData.setCurrentMaintenanceData(programData.getMaintenanceDatas().get(vendingMachineButton));
            IndependentItemEnum.setItemPrices(programData.getIndependentItemPrices().get(vendingMachineButton));
            DependentItemEnum.setItemPrices(programData.getDependentItemPrices().get(vendingMachineButton));
            windowManager.gotoVndFeaturesView();
        });

        vndMachineButtonsLayout.getChildren().add(vendingMachineButton);
        if (vndTypeChoice.getValue().equalsIgnoreCase("Regular")) {
            System.out.println("Add regular vending machine");
            programData.getVendingMachines().put(vendingMachineButton, new RegularVendingMachine(noOfSlots, slotCapacity));
        } else {
            System.out.println("Add special vending machine");
            programData.getVendingMachines().put(vendingMachineButton, new SpecialVendingMachine(noOfSlots, slotCapacity));
        }
        programData.getMaintenanceDatas().put(vendingMachineButton, new MaintenanceData());
        programData.getIndependentItemPrices().put(vendingMachineButton, IndependentItemEnum.createNewItemPrices());
        programData.getDependentItemPrices().put(vendingMachineButton, DependentItemEnum.createNewItemPrices());

        vndTypeChoice.setValue("Regular");
        vndNameTextField.setText("");
        noOfSlotsNumberField.getTextField().setText("0");
        slotCapacityNumberField.getTextField().setText("0");
        slidePopup.slideDownAnimation();
    }

    /**
     * Sets the WindowManager for this controller.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Sets the ProgramData for this controller.
     *
     * @param programData The ProgramData instance.
     */
    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }
}
