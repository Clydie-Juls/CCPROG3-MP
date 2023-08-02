package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.StockInterface;
import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.StockData;
import com.vnd.mco2restructure.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import java.util.Objects;


/**
 * Controller class for handling stocks.
 */
public class StockController {

    private WindowManager windowManager;
    private StockData stockData;
    @FXML private FlowPane menuLayout;

    /**
     * Sets the view for the StockController based on the vending machine type.
     *
     * @param isSpecialVendingMachine True if the vending machine is special, false otherwise.
     */
    public void setView(boolean isSpecialVendingMachine) {
        menuLayout.getChildren().clear();
        if (isSpecialVendingMachine) {
            for (CustomizableItemEnum item : CustomizableItemEnum.values()) {
                createStockInterface(item, item.getImageFile());
            }
        }

        for (IndependentItemEnum item : IndependentItemEnum.values()) {
            createStockInterface(item, item.getImageFile());
        }
    }

    /**
     * Creates a stock interface to show the item in stock or want to stock
     * @param item item to render the data
     * @param imageFile image of the item
     */
    private void createStockInterface(ItemEnum<? extends Item> item, String imageFile) {
        StockInterface stockInterface = new StockInterface();
        stockInterface.getItemImageView().setImage(new Image(
                Objects.requireNonNull(HelloApplication.class.getResourceAsStream(imageFile))));
        stockInterface.getItemNameLabel().setText(
                item.toString().toLowerCase().replace('_',' '));
        stockInterface.getAddButton().setOnAction(event -> {
            windowManager.setStockManagerStock(item, stockData.getSlotId());
            exit();
        });
        menuLayout.getChildren().add(stockInterface);
    }

    /**
     * Sets the slot ID for the stock.
     *
     * @param slotId The slot ID to set.
     */
    public void setSlotId(int slotId) {
        stockData.setSlotId(slotId);
    }

    /**
     * Method to exit from the StockController view.
     */
    @FXML
    private void exit() {
        windowManager.gotoStockManagerView(false);
    }

    /**
     * Sets the WindowManager instance for this controller.
     *
     * @param windowManager The WindowManager instance to set.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Sets the stock data for the StockController.
     *
     * @param stockData The stock data to set.
     */
    public void setStockData(StockData stockData) {
        this.stockData = stockData;
    }
}
