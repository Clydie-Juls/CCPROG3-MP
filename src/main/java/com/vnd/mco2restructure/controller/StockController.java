package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.menu.NonCustomizable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    private WindowManager windowManager;
    @FXML private FlowPane menuLayout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (CustomizableItemEnum item : CustomizableItemEnum.values()) {
            //TODO: create fxml structure for this
            Label itemName = new Label(item.toString().toLowerCase().replace('_',' '));
            VBox vBox = new VBox(itemName);
            vBox.setStyle("-fx-background-color: #f0f0f0;");

            for (CustomizableItemEnum.ItemType ingredient : item.getIngredients()) {
                Label ingredientTypesName = new Label(ingredient.getItemTypeName());
                vBox.getChildren().add(ingredientTypesName);
                for (NonCustomizable ingredientItem : ingredient.getItems()) {
                    if (ingredientItem instanceof IndependentItemEnum independentItemEnum) {
                        String name = independentItemEnum.toString().toLowerCase().replace('_',' ');
                        CheckBox itemCheckBox = new CheckBox(name);
                        itemCheckBox.setSelected(true);
                        vBox.getChildren().add(itemCheckBox);
                    } else if (ingredientItem instanceof DependentItemEnum dependentItemEnum) {
                        String name = dependentItemEnum.toString().toLowerCase().replace('_',' ');
                        CheckBox itemCheckBox = new CheckBox(name);
                        itemCheckBox.setSelected(true);
                        vBox.getChildren().add(itemCheckBox);
                    }
                }
            }
            NumberField numberField = new NumberField();
            vBox.getChildren().add(numberField);
            menuLayout.getChildren().add(vBox);
        }

        for (IndependentItemEnum item : IndependentItemEnum.values()) {
            //TODO: create fxml structure for this
            Label itemName = new Label(item.toString().toLowerCase().replace('_',' '));
            VBox vBox = new VBox(itemName);
            vBox.setStyle("-fx-background-color: #f0f0f0;");
            NumberField numberField = new NumberField();
            vBox.getChildren().add(numberField);
            menuLayout.getChildren().add(vBox);
        }

        for (DependentItemEnum item : DependentItemEnum.values()) {
            //TODO: create fxml structure for this
            Label itemName = new Label(item.toString().toLowerCase().replace('_',' '));
            VBox vBox = new VBox(itemName);
            vBox.setStyle("-fx-background-color: #f0f0f0;");
            NumberField numberField = new NumberField();
            vBox.getChildren().add(numberField);
            menuLayout.getChildren().add(vBox);
        }
    }

    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
