package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.component.SlidePopup;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class SlidePopupController {


    @FXML public BorderPane layout;

    @FXML
    private void exit() {
        ((SlidePopup)layout).slideDownAnimation();
    }
}
