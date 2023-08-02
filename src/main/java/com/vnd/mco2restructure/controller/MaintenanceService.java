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

/**
 * The MaintenanceService class is responsible for managing maintenance operations on the vending machine.
 * It provides functionalities to stock items, change item prices, collect money, and replenish the denomination.
 */
public class MaintenanceService {
    private MaintenanceData maintenanceData;
    private VendingMachine vendingMachine;

    private WindowManager windowManager;
    @FXML
    private SlidePopup slidePopup;
    @FXML
    private Label moneyLabel;



    /**
     * Stocks the vending machine with items based on the provided item enums and stock edit information.
     *
     * @param itemEnums      The list of item enums representing the items to be restocked.
     * @param stockEditInfos The list of StockEditInfo objects representing the restock information.
     */
    public void stock(ArrayList<ItemEnum<? extends Item>> itemEnums, ArrayList<StockEditInfo> stockEditInfos) {
        Set<String> itemNames = new HashSet<>();
        // if the vending machine is only regular add stock via storage slot
        if (vendingMachine instanceof RegularVendingMachine regularVendingMachine) {
            for (int i = 0; i < itemEnums.size(); i++) {
                if (stockEditInfos.get(i) != null) {
                    if (regularVendingMachine.getSlots()[i] instanceof StorageSlot storageSlot) {
                        IndependentItemEnum item = ((IndependentItemEnum) itemEnums.get(i));
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
            // If the vending machine is a special vending machine, add stock via item storage
        } else if (vendingMachine instanceof SpecialVendingMachine specialVendingMachine) {
            for (int i = 0; i < itemEnums.size(); i++) {

                if(stockEditInfos.get(i) != null) {
                    // goes through each item derived if item is customizable
                    for (Map.Entry<NonCustomizable, StockEditInfo.ItemEditInfo[]> entry : stockEditInfos.get(i).getItemAmount().entrySet()) {
                        if (entry.getKey() instanceof CustomizableItemEnum.ItemType itemType) {
                            for (int i1 = 0; i1 < itemType.getItems().length; i1++) {
                                if (itemType.getItems()[i1] instanceof IndependentItemEnum independentItemEnum) {
                                    if (!specialVendingMachine.getItemStorage().containsKey(independentItemEnum.enumToItem())) {
                                        specialVendingMachine.getItemStorage().put(independentItemEnum.enumToItem(), new ArrayList<>());
                                    }

                                    for (int integer = 0; integer < entry.getValue()[i1].getAmount()
                                            && specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).size()
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
                                            && specialVendingMachine.getItemStorage().get(dependentItemEnum.enumToItem()).size()
                                            < specialVendingMachine.getSlots()[0].getCapacity() &&
                                            entry.getValue()[i1].isChecked(); integer++) {
                                        specialVendingMachine.getItemStorage().get(dependentItemEnum.enumToItem()).add(dependentItemEnum.enumToItem());
                                    }
                                    itemNames.add(dependentItemEnum.enumToItem().getName());
                                }
                            }
                            // Simple stock it if item is independent
                        } else if (entry.getKey() instanceof IndependentItemEnum independentItemEnum) {
                            if (!specialVendingMachine.getItemStorage().containsKey(independentItemEnum.enumToItem())) {
                                specialVendingMachine.getItemStorage().put(independentItemEnum.enumToItem(), new ArrayList<>());
                            }
                            for (int integer = 0; integer < entry.getValue()[0].getAmount()
                                    && specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).size()
                                    < specialVendingMachine.getSlots()[0].getCapacity() &&
                                    entry.getValue()[0].isChecked(); integer++) {
                                specialVendingMachine.getItemStorage().get(independentItemEnum.enumToItem()).add(independentItemEnum.enumToItem());
                            }
                        }
                    }
                    // put item in the id slot to get position
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

    /**
     * update customizable item to transaction table
     * @param item item to update
     * @param specialVendingMachine - current special vending machine
     */
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


    /**
     * Collects the money from the vending machine.
     */

    public void collectMoney() {
        maintenanceData.setTotalMoney(maintenanceData.getTotalMoney() +
                vendingMachine.getDenomination().passDenomination());
    }

    /**
     * transfer requested money and convert them to a single integer and put at the total money collected.
     * @param denomination - hashmap of money
     */
    public void replenishDenomination(LinkedHashMap<Integer, ArrayList<Money>> denomination) {
        for (Map.Entry<Integer, ArrayList<Money>> entry : denomination.entrySet()) {
            vendingMachine.getDenomination().getCurrency().get(entry.getKey()).addAll(entry.getValue());
        }
    }

    /**
     * Updates the view with the current maintenance data.
     */
    public void updateView() {
        moneyLabel.setText("" + maintenanceData.getTotalMoney());
    }


    /**
     * Navigates to the view for collecting money from the vending machine.
     */
    @FXML
    private void gotoCollectMoneyView() {
        slidePopup.setCenter(windowManager.getCollectMoneyView(vendingMachine.getDenomination().getTotalMoney()));
        slidePopup.slideUpAnimation();
    }

    /**
     * Navigates to the view for collecting denomination from the vending machine.
     */
    @FXML
    private void gotoCollectDenomView() {
        slidePopup.setCenter(windowManager.getCollectDenomView());
        slidePopup.slideUpAnimation();
    }

    /**
     * Navigates to the view for managing stock in the vending machine.
     */
    @FXML
    private void gotoStockView() {
        windowManager.gotoStockManagerView(true);
    }


    /**
     * Sets the WindowManager for this controller.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Navigates to the view for changing item prices in the vending machine.
     */
    @FXML
    private void gotoChangeItemPriceView() {
        windowManager.gotoChangeItemPriceView();
    }

    /**
     * Navigates to the view for displaying transactions in the vending machine.
     */
    @FXML
    private void gotoDisplayTransactionsView() {
        windowManager.gotoDisplayTransactionsView();
    }

    /**
     * Returns the SlidePopup instance used for the maintenance service view.
     *
     * @return The SlidePopup instance.
     */
    public SlidePopup getSlidePopup() {
        return slidePopup;
    }

    /**
     * Sets the MaintenanceData for this controller.
     *
     * @param maintenanceData The MaintenanceData instance.
     */
    public void setMaintenanceData(MaintenanceData maintenanceData) {
        this.maintenanceData = maintenanceData;
    }

    /**
     * Sets the VendingMachine for this controller.
     *
     * @param vendingMachine The VendingMachine instance.
     */
    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}
