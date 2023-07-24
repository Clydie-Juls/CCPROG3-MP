package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.model.StockData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    private StockData stockData;
    private WindowManager windowManager;
    @FXML private FlowPane menuLayout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (CustomizableItemEnum item : CustomizableItemEnum.values()) {
            //TODO: create fxml structure for this
            Label itemName = new Label(item.toString().toLowerCase().replace('_',' '));
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

        for (IndependentItemEnum item : IndependentItemEnum.values()) {
            //TODO: create fxml structure for this
            Label itemName = new Label(item.toString().toLowerCase().replace('_',' '));
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
