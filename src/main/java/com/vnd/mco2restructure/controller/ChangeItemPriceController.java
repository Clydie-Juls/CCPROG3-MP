package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemPriceInterface;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ChangeItemPriceController implements Initializable {
    private WindowManager windowManager;
    @FXML private FlowPane menuLayout;

    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateView();
    }

    private void updateView() {
        menuLayout.getChildren().clear();
        for (IndependentItemEnum value : IndependentItemEnum.values()) {
            createChangeItemInterface(value.name(), value.getPrice(), value::setPrice);
        }

        for (DependentItemEnum value : DependentItemEnum.values()) {
            createChangeItemInterface(value.name(), value.getPrice(), value::setPrice);
        }
    }

    private void createChangeItemInterface(String name, int price, IntConsumer callback) {
        ItemPriceInterface itemPriceInterface = new ItemPriceInterface();
        itemPriceInterface.getItemNameLabel().setText(name.toLowerCase().replaceAll("_", " "));
        itemPriceInterface.getItemPriceLabel().setText("Price: " + price);
        itemPriceInterface.getItemPriceNumberField().getTextField().setText("" +price);
        itemPriceInterface.getChangePriceButton().setOnAction(event -> {
            try {
                int newPrice = Integer.parseInt(itemPriceInterface.getItemPriceNumberField().getTextField().getText());
                callback.accept(newPrice);
            } catch (Exception e) {
                System.out.println("Change item price error");
            }
            updateView();
        });
        menuLayout.getChildren().add(itemPriceInterface);
    }
}
