package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.Application;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemPriceInterface;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.util.Objects;
import java.util.function.IntConsumer;


/**
 * Controller class for the change item price view.
 */
public class ChangeItemPriceController {

    private WindowManager windowManager;
    @FXML private FlowPane menuLayout;

    /**
     * Handles the action when the "Exit" button is clicked.
     * Goes back to the main features view.
     */
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
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
     * Updates the view by displaying the item prices and providing buttons to change the prices.
     */
    public void updateView(boolean isSpecialVendingMachine) {
        menuLayout.getChildren().clear();
        for (IndependentItemEnum value : IndependentItemEnum.values()) {
            createChangeItemInterface(value.name(), value.getImageFile(), value.getPrice(), value::setPrice);
        }

        if(isSpecialVendingMachine) {
            for (DependentItemEnum value : DependentItemEnum.values()) {
                createChangeItemInterface(value.name(), value.getImageFile(), value.getPrice(), value::setPrice);
            }
        }
    }

    /**
     * Creates and adds the item price interface to the view for a specific item.
     *
     * @param name The name of the item.
     * @param price The current price of the item.
     * @param callback The callback function to update the price when changed.
     * @param imageFile The image file of the item.
     */
    private void createChangeItemInterface(String name, String imageFile, int price, IntConsumer callback) {
        ItemPriceInterface itemPriceInterface = new ItemPriceInterface();
        itemPriceInterface.getItemNameLabel().setText(name.toLowerCase().replaceAll("_", " "));
        itemPriceInterface.getItemPriceLabel().setText("Price: " + price);
        itemPriceInterface.getItemPriceNumberField().getTextField().setText("" + price);
        itemPriceInterface.getItemImageView().setImage(new
                Image(Objects.requireNonNull(Application.class.getResourceAsStream(imageFile))));
        itemPriceInterface.getChangePriceButton().setOnAction(event -> {
            try {
                int newPrice = Integer.parseInt(itemPriceInterface.getItemPriceNumberField().getTextField().getText());
                callback.accept(newPrice);
                itemPriceInterface.getItemPriceLabel().setText("Price: " + newPrice);
            } catch (Exception e) {
                System.out.println("Change item price error");
            }
        });
        menuLayout.getChildren().add(itemPriceInterface);
    }
}
