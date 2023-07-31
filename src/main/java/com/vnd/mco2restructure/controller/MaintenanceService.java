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
import java.util.Map;

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
                    }
                }
            }

            System.out.println("Regular vending machine stock done");
        } else if (vendingMachine instanceof SpecialVendingMachine specialVendingMachine) {
            for (int i = 0; i < itemEnums.size(); i++) {
                if (stockEditInfos.get(i) != null) {
                    if (specialVendingMachine.getSlots()[i] instanceof IdSlot idSlot) {
                        idSlot.putItem(itemEnums.get(i).enumToItem());
                    }
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
                                } else if (itemType.getItems()[i1] instanceof DependentItemEnum dependentItemEnum) {
                                    if (!specialVendingMachine.getItemStorage().containsKey(dependentItemEnum.enumToItem())) {
                                        specialVendingMachine.getItemStorage().put(dependentItemEnum.enumToItem(), new ArrayList<>());
                                    }

                                    for (int integer = 0; integer < entry.getValue()[i1].getAmount()
                                            && specialVendingMachine.getItemStorage().get(dependentItemEnum.enumToItem()).size()
                                            < specialVendingMachine.getSlots()[0].getCapacity() &&
                                            entry.getValue()[i1].isChecked(); integer++) {
                                        specialVendingMachine.getItemStorage().get(dependentItemEnum.enumToItem()).add(dependentItemEnum.enumToItem());
                                    }
                                }
                            }
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


    /**
     * Changes the prices of items in the vending machine.
     * TODO: Implement the logic for changing item prices.
     */
    public void changeItemPrice() {
        // TODO: Implement the logic for changing item prices.
    }

    /**
     * Collects the money from the vending machine.
     */
    public void collectMoney() {
        maintenanceData.setTotalMoney(maintenanceData.getTotalMoney() +
                vendingMachine.getDenomination().passDenomination());
    }

    /**
     * Replenishes the denomination of the vending machine with new currency notes/coins.
     *
     * @param denomination The map representing the denomination to be replenished.
     */
    public void replenishDenomination(Map<Integer, Integer> denomination) {
        for (Map.Entry<Integer, Integer> entry : denomination.entrySet()) {
            vendingMachine.getDenomination().getCurrency().replace(entry.getKey(),
                    entry.getValue() + vendingMachine.getDenomination().getCurrency().get(entry.getKey()));
        }
        System.out.println(vendingMachine.getDenomination().getCurrency());
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
