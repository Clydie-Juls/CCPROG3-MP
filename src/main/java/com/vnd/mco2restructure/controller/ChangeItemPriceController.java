package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemPriceInterface;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.IntConsumer;

/**
 * Controller class for the change item price view.
 */
public class ChangeItemPriceController implements Initializable {

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
     * Initializes the view and updates the item prices display.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateView();
    }

    /**
     * Updates the view by displaying the item prices and providing buttons to change the prices.
     */
    private void updateView() {
        menuLayout.getChildren().clear();
        for (IndependentItemEnum value : IndependentItemEnum.values()) {
            createChangeItemInterface(value.name(), value.getImageFile(), value.getPrice(), value::setPrice);
        }

        for (DependentItemEnum value : DependentItemEnum.values()) {
            createChangeItemInterface(value.name(), value.getImageFile(), value.getPrice(), value::setPrice);
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
        System.out.println(imageFile);
        itemPriceInterface.getItemImageView().setImage(new
                Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream(imageFile))));
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
