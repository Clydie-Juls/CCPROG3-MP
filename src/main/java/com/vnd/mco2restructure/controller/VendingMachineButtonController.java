package com.vnd.mco2restructure.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VendingMachineButtonController {

    @FXML  public Label vndNameLabel;
    @FXML public Label vndTypeLabel;

    public void initialize(String vndName, String vndType) {
        vndNameLabel.setText(vndName);
        vndTypeLabel.setText(vndType);
    }


}
