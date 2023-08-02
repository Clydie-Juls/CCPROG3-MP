package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.SlidePopup;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.model.MaintenanceData;
import com.vnd.mco2restructure.model.Money;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.IndependentItem;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.slots.IdSlot;
import com.vnd.mco2restructure.model.slots.StorageSlot;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.*;

public class MaintenanceService {
    private MaintenanceData maintenanceData;
    private VendingMachine vendingMachine;

    private WindowManager windowManager;
    @FXML private SlidePopup slidePopup;
    @FXML private Label moneyLabel;

    public void stock(ArrayList<ItemEnum<? extends Item>> itemEnums, ArrayList<StockEditInfo> stockEditInfos) {
        Set<String> itemNames = new HashSet<>();

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
                        vendingMachine.getTransactions().addTransaction(storageSlot.getItem(),
                                "" + storageSlot.getItemStackCount());
                        itemNames.add(storageSlot.getItem().getName());
                    }
                }
            }
            System.out.println("Regular vending machine stock done");
        } else if (vendingMachine instanceof SpecialVendingMachine specialVendingMachine){
            for (int i = 0; i < itemEnums.size(); i++) {
                if(stockEditInfos.get(i) != null) {
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

                                    itemNames.add(independentItemEnum.enumToItem().getName());
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
                                    itemNames.add(dependentItemEnum.enumToItem().getName());
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
                    if (specialVendingMachine.getSlots()[i] instanceof IdSlot idSlot) {
                        idSlot.putItem(itemEnums.get(i).enumToItem());
                        itemNames.add(idSlot.getItem().getName());
                        if(itemEnums.get(i).enumToItem() instanceof CustomizableItem customizableItem) {
                            customizableItemToTransaction(customizableItem, specialVendingMachine);
                        } else if(itemEnums.get(i).enumToItem() instanceof IndependentItem independentItem) {
                            int itemAmount = specialVendingMachine.getItemStorage().containsKey(independentItem) ?
                                    specialVendingMachine.getItemStorage().get(independentItem).size() : 0;
                            specialVendingMachine.getTransactions().addTransaction(independentItem,
                                    "" + itemAmount);
                        }
                    }
                }
            }
            System.out.println("Special Vending Stocking Done");
        }

        vendingMachine.getTransactions().UpdateStockTransactionInfo(itemNames);
    }

    private void customizableItemToTransaction(CustomizableItem item, SpecialVendingMachine specialVendingMachine) {
        for (NonCustomizableItem[] value : item.getItemsDerived().values()) {
            for (NonCustomizableItem nonCustomizableItem : value) {
                int itemAmount = specialVendingMachine.getItemStorage().containsKey(nonCustomizableItem) ?
                        specialVendingMachine.getItemStorage().get(nonCustomizableItem).size() : 0;
                vendingMachine.getTransactions().addTransaction(nonCustomizableItem, "" + itemAmount);
            }
        }
        vendingMachine.getTransactions().addTransaction(item, null);
    }

    public void collectMoney() {
        maintenanceData.setTotalMoney(maintenanceData.getTotalMoney() +
                vendingMachine.getDenomination().passDenomination());
    }

    public void replenishDenomination(LinkedHashMap<Integer, ArrayList<Money>> denomination) {
        for (Map.Entry<Integer, ArrayList<Money>> entry : denomination.entrySet()) {
            vendingMachine.getDenomination().getCurrency().get(entry.getKey()).addAll(entry.getValue());
        }
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
