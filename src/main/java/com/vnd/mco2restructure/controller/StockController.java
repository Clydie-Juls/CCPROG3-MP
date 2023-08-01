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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class StockController {

    private StockData stockData;
    private WindowManager windowManager;
    @FXML private FlowPane menuLayout;

    public void setView(boolean isSpecialVendingMachine) {
        menuLayout.getChildren().clear();
        if(isSpecialVendingMachine) {
            for (CustomizableItemEnum item : CustomizableItemEnum.values()) {
                createStockInterface(item, item.getImageFile());
            }
        }

        for (IndependentItemEnum item : IndependentItemEnum.values()) {
            createStockInterface(item, item.getImageFile());
        }
    }

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

    public void setSlotId(int slotId) {
        stockData.setSlotId(slotId);
    }

    @FXML
    private void exit() {
        windowManager.gotoStockManagerView(false);
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setStockData(StockData stockData) {
        this.stockData = stockData;
    }
}
