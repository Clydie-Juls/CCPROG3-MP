package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ProvideDenomController implements Initializable {
    private WindowManager windowManager;
    @FXML private Label totalMoney;
    @FXML private NumberField numberField1000;
    @FXML private NumberField numberField500;
    @FXML private NumberField numberField200;
    @FXML private NumberField numberField100;
    @FXML private NumberField numberField50;
    @FXML private NumberField numberField20;
    @FXML private NumberField numberField10;
    @FXML private NumberField numberField5;
    @FXML private NumberField numberField1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpdateFunction(numberField1000);
        setUpdateFunction(numberField500);
        setUpdateFunction(numberField200);
        setUpdateFunction(numberField100);
        setUpdateFunction(numberField50);
        setUpdateFunction(numberField20);
        setUpdateFunction(numberField10);
        setUpdateFunction(numberField5);
        setUpdateFunction(numberField1);
        updateView();
    }

    private void setUpdateFunction(NumberField numberField) {
        numberField.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            // This method will be called whenever the TextField text is updated
            updateView();
        });
    }

    private void updateView() {
        try {
            int money1000 = Integer.parseInt(numberField1000.getTextField().getText());
            int money500 = Integer.parseInt(numberField500.getTextField().getText());
            int money200 = Integer.parseInt(numberField200.getTextField().getText());
            int money100 = Integer.parseInt(numberField100.getTextField().getText());
            int money50 = Integer.parseInt(numberField50.getTextField().getText());
            int money20 = Integer.parseInt(numberField20.getTextField().getText());
            int money10 = Integer.parseInt(numberField10.getTextField().getText());
            int money5 = Integer.parseInt(numberField5.getTextField().getText());
            int money1 = Integer.parseInt(numberField1.getTextField().getText());
            totalMoney.setText("Total: " + (money1000 * 1000 + money500 * 500 + money200 * 200 + money100 * 100 +
                    money50 * 50 + money20 * 20 + money10 * 10 +
                    money5 * 5 + money1));
        } catch (Exception e) {
            System.out.println("Updating failed");
        }
    }

    @FXML
    private void replenish() {
        Map<Integer, Integer> denomination = new HashMap<>();
        int money1000 = Integer.parseInt(numberField1000.getTextField().getText());
        int money500 = Integer.parseInt(numberField500.getTextField().getText());
        int money200 = Integer.parseInt(numberField200.getTextField().getText());
        int money100 = Integer.parseInt(numberField100.getTextField().getText());
        int money50 = Integer.parseInt(numberField50.getTextField().getText());
        int money20 = Integer.parseInt(numberField20.getTextField().getText());
        int money10 = Integer.parseInt(numberField10.getTextField().getText());
        int money5 = Integer.parseInt(numberField5.getTextField().getText());
        int money1 = Integer.parseInt(numberField1.getTextField().getText());
        denomination.put(1000, money1000);
        denomination.put(500, money500);
        denomination.put(200, money200);
        denomination.put(100, money100);
        denomination.put(50, money50);
        denomination.put(20, money20);
        denomination.put(10, money10);
        denomination.put(5, money5);
        denomination.put(1, money1);
        windowManager.replenishDenomination(denomination);
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
