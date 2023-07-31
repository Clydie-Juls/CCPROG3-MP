package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.model.MaintenanceData;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.slots.IdSlot;
import com.vnd.mco2restructure.model.slots.StorageSlot;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MaintenanceService {
    private MaintenanceData maintenanceData;
    private VendingMachine vendingMachine;

    private WindowManager windowManager;
    @FXML private SlidePopup slidePopup;
    @FXML private Label moneyLabel;


    //TODO: DO THIS
    public void stock(ArrayList<ItemEnum<? extends Item>> itemEnums, ArrayList<StockEditInfo> stockEditInfos) {
        if(vendingMachine instanceof RegularVendingMachine regularVendingMachine) {
            for (int i = 0; i < itemEnums.size(); i++) {
                if (stockEditInfos.get(i) != null) {
                    if (regularVendingMachine.getSlots()[i] instanceof StorageSlot storageSlot) {
                        IndependentItemEnum item = ((IndependentItemEnum)itemEnums.get(i));
                        System.out.println(stockEditInfos.get(i).getItemAmount().get(item)[0].getAmount());
                        for (int i1 = 0; i1 < stockEditInfos.get(i).getItemAmount().get(item)[0].getAmount()
                                && stockEditInfos.get(i).getItemAmount().get(item)[0].isChecked() &&
                                storageSlot.getItemStackCount() < storageSlot.getCapacity(); i1++) {
                            storageSlot.putItem(item.enumToItem());
                        }
                    }
                }
            }

            System.out.println("Regular vending machine stock done");
        } else if (vendingMachine instanceof SpecialVendingMachine specialVendingMachine){
            for (int i = 0; i < itemEnums.size(); i++) {
                if(stockEditInfos.get(i) != null) {
                    if (specialVendingMachine.getSlots()[i] instanceof IdSlot idSlot) {
                        idSlot.putItem(itemEnums.get(i).enumToItem());
                    }
                    for (Map.Entry<NonCustomizable, StockEditInfo.ItemEditInfo[]> entry : stockEditInfos.get(i).getItemAmount().entrySet()) {
                        if(entry.getKey() instanceof CustomizableItemEnum.ItemType itemType) {
                            for (int i1 = 0; i1 < itemType.getItems().length; i1++) {
                                if(itemType.getItems()[i1] instanceof IndependentItemEnum independentItemEnum) {
                                    if(!specialVendingMachine.getItemStorage().containsKey(independentItemEnum.enumToItem())) {
                                        specialVendingMachine.getItemStorage().put(independentItemEnum.enumToItem(), new ArrayList<>());
                                    }

                                    for (int integer = 0; integer < entry.getValue()[i1].getAmount()
                                            &&  specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).size()
                                            < specialVendingMachine.getSlots()[0].getCapacity() &&
                                            entry.getValue()[i1].isChecked(); integer++) {
                                        specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).add(independentItemEnum.enumToItem());
                                    }
                                } else if (itemType.getItems()[i1] instanceof DependentItemEnum dependentItemEnum ) {
                                    if(!specialVendingMachine.getItemStorage().containsKey(dependentItemEnum.enumToItem())) {
                                        specialVendingMachine.getItemStorage().put(dependentItemEnum.enumToItem(), new ArrayList<>());
                                    }

                                    for (int integer = 0; integer < entry.getValue()[i1].getAmount()
                                            &&  specialVendingMachine.getItemStorage().get(dependentItemEnum.enumToItem()).size()
                                            < specialVendingMachine.getSlots()[0].getCapacity() &&
                                            entry.getValue()[i1].isChecked(); integer++) {
                                        specialVendingMachine.getItemStorage().get(dependentItemEnum.enumToItem()).add(dependentItemEnum.enumToItem());
                                    }
                                }
                            }
                        } else if(entry.getKey() instanceof IndependentItemEnum independentItemEnum) {
                            if(!specialVendingMachine.getItemStorage().containsKey(independentItemEnum.enumToItem())) {
                                specialVendingMachine.getItemStorage().put(independentItemEnum.enumToItem(), new ArrayList<>());
                            }
                            for (int integer = 0; integer < entry.getValue()[0].getAmount()
                                    &&  specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).size()
                                    < specialVendingMachine.getSlots()[0].getCapacity() &&
                                    entry.getValue()[0].isChecked(); integer++) {
                                specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).add(independentItemEnum.enumToItem());
                            }
                        }
                    }
                }

            }
            System.out.println("Special Vending Stocking Done");
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
        maintenanceData.setTotalMoney(maintenanceData.getTotalMoney() +
                vendingMachine.getDenomination().passDenomination());
    }

    // TODO: REDO THIS
    public void replenishDenomination(LinkedHashMap<Integer, Integer> denomination) {
        for (Map.Entry<Integer, Integer> entry : denomination.entrySet()) {
            vendingMachine.getDenomination().getCurrency().replace(entry.getKey(),
                    entry.getValue() + vendingMachine.getDenomination().getCurrency().get(entry.getKey()));
        }
        System.out.println(vendingMachine.getDenomination().getCurrency());
    }

    public void updateView() {
        moneyLabel.setText("" + maintenanceData.getTotalMoney());
    }


    @FXML
    private void gotoCollectMoneyView() {
        slidePopup.setCenter(windowManager.getCollectMoneyView(vendingMachine.getDenomination().getTotalMoney()));
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
    private void gotoChangeItemPriceView() {
        windowManager.gotoChangeItemPriceView();
    }

    @FXML
    private void gotoDisplayTransactionsView() {
        windowManager.gotoDisplayTransactionsView();
    }

    public SlidePopup getSlidePopup() {
        return slidePopup;
    }

    public void setMaintenanceData(MaintenanceData maintenanceData) {
        this.maintenanceData = maintenanceData;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}
