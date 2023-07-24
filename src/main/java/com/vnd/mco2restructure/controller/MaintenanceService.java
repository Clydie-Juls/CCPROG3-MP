package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.MaintenanceData;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaintenanceService {
    private MaintenanceData maintenanceData;
    private VendingMachine vendingMachine;

    private WindowManager windowManager;
    public SlidePopup slidePopup;


    //TODO: DO THIS
    public void stock(ArrayList<ArrayList<ItemEnum<? extends Item>>> menuItems) {
        // TODO: fill in Storage slot of regularVendingMachine
        if(vendingMachine instanceof RegularVendingMachine regularVendingMachine) {

        } else if (vendingMachine instanceof SpecialVendingMachine specialVendingMachine){
            //TODO: FILL the ItemStorage(Not the slot) of the SpecialVendingMachine
        }
//        // If all parameters are valid
//        Item newItem = new Item(itemName, calories, price);
//        vendingMachine.getSlots()[slotNo - 1].setItem(newItem);
//        vendingMachine.getSlots()[slotNo - 1].setAmount(amount);
//        vendingMachine.getTransactions().resetTransactions();
    }


  // TODO: REDO THIS
    public void changeItemPrice() {

    }

    // TODO: REDO THIS
    public void collectMoney() {

    }

    // TODO: REDO THIS
    public void replenishDenomination() {

    }


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
        windowManager.gotoStockManagerView(true);
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

    public void setMaintenanceData(MaintenanceData maintenanceData) {
        this.maintenanceData = maintenanceData;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}
