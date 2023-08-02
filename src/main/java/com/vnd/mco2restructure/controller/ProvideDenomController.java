package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.callbacks.DenomCallback;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.model.Money;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProvideDenomController implements Initializable {
    @FXML private Button button;
    private DenomCallback denomCallback;
    @FXML private Label totalMoneyLabel;
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

    }

    private void setUpdateFunction(NumberField numberField, int minimumNo) {
        numberField.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            // This method will be called whenever the TextField text is updated
            updateView(minimumNo);
        });
    }

    public void updateView(int minimumNo) {
        setUpdateFunction(numberField1000, minimumNo);
        setUpdateFunction(numberField500, minimumNo);
        setUpdateFunction(numberField200, minimumNo);
        setUpdateFunction(numberField100, minimumNo);
        setUpdateFunction(numberField50, minimumNo);
        setUpdateFunction(numberField20, minimumNo);
        setUpdateFunction(numberField10, minimumNo);
        setUpdateFunction(numberField5, minimumNo);
        setUpdateFunction(numberField1, minimumNo);
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
            int totalMoney = money1000 * 1000 + money500 * 500 + money200 * 200 + money100 * 100 +
                    money50 * 50 + money20 * 20 + money10 * 10 +
                    money5 * 5 + money1;
            totalMoneyLabel.setText("Total: " + totalMoney);

            button.setDisable(totalMoney < minimumNo);
        } catch (Exception e) {
            System.out.println("Updating failed");
        }
    }

    @FXML
    private void replenish() {
        LinkedHashMap<Integer, ArrayList<Money>> denomination = new LinkedHashMap<>();
        int money1000 = Integer.parseInt(numberField1000.getTextField().getText());
        int money500 = Integer.parseInt(numberField500.getTextField().getText());
        int money200 = Integer.parseInt(numberField200.getTextField().getText());
        int money100 = Integer.parseInt(numberField100.getTextField().getText());
        int money50 = Integer.parseInt(numberField50.getTextField().getText());
        int money20 = Integer.parseInt(numberField20.getTextField().getText());
        int money10 = Integer.parseInt(numberField10.getTextField().getText());
        int money5 = Integer.parseInt(numberField5.getTextField().getText());
        int money1 = Integer.parseInt(numberField1.getTextField().getText());
        int[] currencies = new int[] {1000, 500, 200, 100 ,50, 20, 10, 5, 1};
        int[] amounts = new int[] {money1000, money500, money200, money100 ,money50, money20, money10, money5, money1};

        for (int i = 0; i < currencies.length; i++) {
            int finalI = i;
            denomination.put(currencies[i], Stream.generate(() ->
                    new Money(currencies[finalI])).limit(amounts[finalI]).
                    collect(Collectors.toCollection(ArrayList::new)));
        }

        denomCallback.onCallBack(denomination);
    }


    public void setDenomCallback(DenomCallback denomCallback) {
        this.denomCallback = denomCallback;
    }
}
