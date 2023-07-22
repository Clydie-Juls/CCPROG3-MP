package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.SlidePopup;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MaintenanceService {

    private WindowManager windowManager;
    public SlidePopup slidePopup;

    @FXML
    private void gotoCollectMoneyView() {
        slidePopup.setCenter(windowManager.getCollectMoneyView());
        slidePopup.slideUpAnimation();
    }

    @FXML
    private void gotoCollectDenomView() {
        slidePopup.setCenter(windowManager.getCollectDenomView());
        slidePopup.slideUpAnimation();
    }

    @FXML
    private void gotoStockView() {
        windowManager.gotoStockView();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
