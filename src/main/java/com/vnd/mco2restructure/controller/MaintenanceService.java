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

    @FXML
    private void gotoRestockView() {
        windowManager.gotoRestockView();
    }

    @FXML
    private void gotoChangeItemPriceView() {
        windowManager.gotoChangeItemPriceView();
    }

    @FXML
    private void gotoDisplayTransactionsView() {
        windowManager.gotoDisplayTransactionsView();
    }
}
