package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.model.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemChoices;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.IndependentItem;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.slots.Slot;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ItemBuyController {
    @FXML private Button buyItemButton;
    @FXML private SlidePopup slidePopup;
    @FXML private ImageView itemImageView;
    @FXML private VBox infoLayout;
    @FXML private Label itemName;
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML
    private void exit() {
        windowManager.gotoVndFeaturesView();
    }

    public void updateView(int slotIndex) {
        infoLayout.getChildren().clear();
        Slot<? extends Item> slot = programData.getCurrentVendingMachine().getSlots()[slotIndex];
        itemName.setText(slot.getItem().getName());
        itemImageView.setImage(new Image(
                Objects.requireNonNull(HelloApplication.class.
                        getResourceAsStream(slot.getItem().getImageFile()))));

        if(programData.getCurrentVendingMachine() instanceof SpecialVendingMachine specialVendingMachine) {
            ArrayList<ToggleGroup> itemsChose = new ArrayList<>();
            if(slot.getItem() instanceof CustomizableItem customizableItem) {
                for (Map.Entry<String, NonCustomizableItem[]> entry : customizableItem.getItemsDerived().entrySet()) {
                    ItemChoices itemChoices = new ItemChoices();
                    ToggleGroup toggleGroup = new ToggleGroup();
                    int i = 0;
                    for (NonCustomizableItem nonCustomizableItem : entry.getValue()) {
                        if(specialVendingMachine.getItemStorage().containsKey(nonCustomizableItem)) {
                            if(specialVendingMachine.getItemStorage().get(nonCustomizableItem).size() > 0) {
                                RadioButton itemChoice = new RadioButton(nonCustomizableItem.getName());
                                itemChoice.setToggleGroup(toggleGroup);
                                itemChoice.setUserData(i);
                                itemChoices.getItemChoicesLayout().getChildren().add(itemChoice);
                                System.out.println(itemChoice.getUserData());
                            }
                        }
                        i++;
                    }

                    toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                            int selectedOption = (int)((RadioButton) newValue).getUserData();
                            itemChoices.getItemTypeLabel().setText(entry.getKey() + "\nItem Price: " +
                                    entry.getValue()[selectedOption].getPrice() + "\nItem Calories: " +
                                    entry.getValue()[selectedOption].getCalories());
                        }
                    });

                    itemsChose.add(toggleGroup);
                    toggleGroup.getToggles().get(0).setSelected(true);
                    infoLayout.getChildren().add(itemChoices);
                }

                buyItemButton.setOnAction(event -> buyItem(itemsChose, customizableItem, slotIndex));
            } else if(slot.getItem() instanceof IndependentItem independentItem) {
                if(specialVendingMachine.getItemStorage().containsKey(independentItem)) {
                    if (specialVendingMachine.getItemStorage().get(independentItem).size() > 0) {
                        buyItemButton.setOnAction(event -> buyItem(independentItem, slotIndex));
                    }
                }
            }
        } else if(programData.getCurrentVendingMachine() instanceof RegularVendingMachine regularVendingMachine){
            if(slot.getItem() instanceof IndependentItem independentItem) {
                if(regularVendingMachine.getSlots()[slotIndex].getItem() != null) {
                    buyItemButton.setOnAction(event -> buyItem(independentItem, slotIndex));
                }
            }
        }
    }

    public void buyItem(ArrayList<ToggleGroup> itemsChose, CustomizableItem itemToBuy, int slotIndex) {
        System.out.println("SLot index:" + slotIndex);
        NonCustomizableItem[] items = new NonCustomizableItem[itemsChose.size()];
        int i = 0;
        for (Map.Entry<String, NonCustomizableItem[]> entry : itemToBuy.getItemsDerived().entrySet()) {
            items[i] = entry.getValue()[(int)itemsChose.get(i).getSelectedToggle().getUserData()];
            i++;
        }
        itemToBuy.setItemContents(items);
        Label finalItem = new Label("Total Price:" + itemToBuy.getPrice() +
                "\nTotal Calories: " + itemToBuy.getCalories());
        VBox vBox = new VBox(finalItem, windowManager.getPaymentView(slotIndex, itemToBuy.getPrice()));
        slidePopup.setCenter(vBox);
        slidePopup.slideUpAnimation();
    }

    public void buyItem( IndependentItem itemToBuy, int slotIndex) {
        System.out.println("SLot index:" + slotIndex);
        Label finalItem = new Label("Total Price:" + itemToBuy.getPrice() +
                "\nTotal Calories: " + itemToBuy.getCalories());
        VBox vBox = new VBox(finalItem, windowManager.
                getPaymentView(slotIndex, itemToBuy.getPrice()));
        slidePopup.setCenter(vBox);
        slidePopup.slideUpAnimation();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }

    public SlidePopup getSlidePopup() {
        return slidePopup;
    }
}
