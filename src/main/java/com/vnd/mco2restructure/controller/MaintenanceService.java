package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.SlidePopup;
import javafx.fxml.FXML;

public class MaintenanceService {

    private WindowManager windowManager;
    public SlidePopup slidePopup;

    @FXML
    private void gotoCollectMoneyView() {
        slidePopup.setCenter(windowManager.getCollectMoneyView());
        slidePopup.slideUpAnimation();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
