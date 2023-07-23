package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.component.VendingMachineButton;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HomeController {
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML private SlidePopup slidePopup;
    @FXML private TextField vndNameTextField;
    @FXML private ComboBox<String> vndTypeChoice;
    @FXML private VBox vndMachineButtonsLayout;
    @FXML private NumberField noOfSlotsNumberField;
    @FXML private NumberField slotCapacityNumberField;


    @FXML
    private void showPopup() {
        slidePopup.slideUpAnimation();
    }

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
            windowManager.gotoVndFeaturesView();
            programData.setCurrentVendingMachine(programData.getVendingMachines().get(vendingMachineButton));
        });


        vndMachineButtonsLayout.getChildren().add(vendingMachineButton);
        if (vndTypeChoice.getValue().equalsIgnoreCase("Regular")) {
            System.out.println("Add regular vending machine");
            programData.getVendingMachines().put(vendingMachineButton, new RegularVendingMachine(noOfSlots, slotCapacity));
        } else {
            System.out.println("Add special vending machine");
            programData.getVendingMachines().put(vendingMachineButton, new SpecialVendingMachine(noOfSlots, slotCapacity));
        }


        vndTypeChoice.setValue("Regular");
        vndNameTextField.setText("");
        slidePopup.slideDownAnimation();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }
}
