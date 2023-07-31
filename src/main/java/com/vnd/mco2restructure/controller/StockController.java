package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.model.StockData;
import com.vnd.mco2restructure.component.NumberField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

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
                // TODO: create fxml structure for this
                Label itemName = new Label(item.toString().toLowerCase().replace('_', ' '));
                VBox vBox = new VBox(itemName);
                vBox.setStyle("-fx-background-color: #aaaaaa;");

                Button addButton = new Button("Add");
                vBox.getChildren().add(addButton);
                addButton.setOnAction(event -> {
                    windowManager.setStockManagerStock(item, stockData.getSlotId());
                    exit();
                });
                menuLayout.getChildren().add(vBox);
            }
        }

        for (IndependentItemEnum item : IndependentItemEnum.values()) {
            // TODO: create fxml structure for this
            Label itemName = new Label(item.toString().toLowerCase().replace('_', ' '));
            VBox vBox = new VBox(itemName);
            vBox.setStyle("-fx-background-color: #aaaaaa;");
            menuLayout.getChildren().add(vBox);
            Button addButton = new Button("Add");
            addButton.setOnAction(event -> {
                windowManager.setStockManagerStock(item, stockData.getSlotId());
                exit();
            });
            vBox.getChildren().add(addButton);

        }
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
