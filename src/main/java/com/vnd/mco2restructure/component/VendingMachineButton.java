package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.controller.VendingMachineButtonController;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class VendingMachineButton extends HBox {
    public VendingMachineButton(@NamedArg("vndName") String vndName, @NamedArg("vndType") String vndType) {
        setView(vndName, vndType);
    }

    private void setView(String vndName, String vndType) {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/VendingMachineButton.fxml"));
        view.setRoot(this);

        try {
            view.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        VendingMachineButtonController vendingMachineButtonController = view.getController();
        vendingMachineButtonController.initialize(vndName, vndType);
    }
}
