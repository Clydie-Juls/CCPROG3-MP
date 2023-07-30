package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemChoices;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.slots.Slot;
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
        ArrayList<ToggleGroup> itemsChose = new ArrayList<>();

        infoLayout.getChildren().clear();
        Slot<? extends Item> slot = programData.getCurrentVendingMachine().getSlots()[slotIndex];
        itemName.setText(slot.getItem().getName());
        if(programData.getCurrentVendingMachine() instanceof SpecialVendingMachine specialVendingMachine) {
            if(slot.getItem() instanceof CustomizableItem customizableItem) {
                itemImageView.setImage(new Image(
                        Objects.requireNonNull(HelloApplication.class.
                                getResourceAsStream(customizableItem.getImageFile()))));
                for (Map.Entry<String, NonCustomizableItem[]> entry : customizableItem.getItemsDerived().entrySet()) {
                    ItemChoices itemChoices = new ItemChoices();
                    ToggleGroup toggleGroup = new ToggleGroup();
                    itemChoices.getItemTypeLabel().setText(entry.getKey());
                    for (NonCustomizableItem nonCustomizableItem : entry.getValue()) {
                        if(specialVendingMachine.getItemStorage().containsKey(nonCustomizableItem)) {
                            if(specialVendingMachine.getItemStorage().get(nonCustomizableItem).size() > 0) {
                                RadioButton itemChoice = new RadioButton(nonCustomizableItem.getName());
                                itemChoice.setToggleGroup(toggleGroup);
                                itemChoices.getItemChoicesLayout().getChildren().add(itemChoice);
                            }
                        }
                    }
                    itemsChose.add(toggleGroup);
                    toggleGroup.getToggles().get(0).setSelected(true);
                    infoLayout.getChildren().add(itemChoices);
                }

                buyItemButton.setOnAction(event -> buyItem(itemsChose, customizableItem));
            }
        }
    }

    public void buyItem(ArrayList<ToggleGroup> itemsChose, CustomizableItem itemToBuy) {
        NonCustomizableItem[] items = new NonCustomizableItem[itemsChose.size()];
        int i = 0;
        for (Map.Entry<String, NonCustomizableItem[]> entry : itemToBuy.getItemsDerived().entrySet()) {
            items[i] = entry.getValue()[itemsChose.get(i).
                    getToggles().indexOf(itemsChose.get(i).getSelectedToggle())];
            i++;
        }
        itemToBuy.setItemContents(items);

        for (NonCustomizableItem content : itemToBuy.getItemContents()) {
            System.out.println(content.getName());
        }

        slidePopup.setCenter(windowManager.getPaymentView());
        slidePopup.slideUpAnimation();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }

}
