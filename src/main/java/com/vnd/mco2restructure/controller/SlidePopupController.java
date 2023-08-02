package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.component.SlidePopup;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * Controller class for the SlidePopup component.
 */
public class SlidePopupController {

    /**
     * layout of the slide popup
     */
    @FXML public BorderPane layout;

    /**
     * Method to exit from the SlidePopup view.
     */
    @FXML
    private void exit() {
        ((SlidePopup)layout).slideDownAnimation();
    }
}
