package com.vnd.mco2restructure;

import com.vnd.mco2restructure.component.VendingMachineButton;
import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.model.MaintenanceData;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;

import java.util.HashMap;

public class ProgramData {
    private HashMap<VendingMachineButton, VendingMachine> vendingMachines;
    private HashMap<VendingMachineButton, MaintenanceData> maintenanceDatas;
    private HashMap<VendingMachineButton, int[]> independentItemPrices;
    private HashMap<VendingMachineButton, int[]> dependentItemPrices;
    private VendingMachine currentVendingMachine;
    private MaintenanceData currentMaintenanceData;
    private CustomizableItemEnum currentSlotItem;
    private StockEditInfo currentStockEditInfo;


    public ProgramData() {
        vendingMachines = new HashMap<>();
        maintenanceDatas = new HashMap<>();
        independentItemPrices = new HashMap<>();
        dependentItemPrices = new HashMap<>();
    }

    public HashMap<VendingMachineButton, VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    public VendingMachine getCurrentVendingMachine() {
        return currentVendingMachine;
    }

    public void setCurrentVendingMachine(VendingMachine currentVendingMachine) {
        this.currentVendingMachine = currentVendingMachine;
    }

    public HashMap<VendingMachineButton, MaintenanceData> getMaintenanceDatas() {
        return maintenanceDatas;
    }

    public MaintenanceData getCurrentMaintenanceData() {
        return currentMaintenanceData;
    }

    public void setCurrentMaintenanceData(MaintenanceData currentMaintenanceData) {
        this.currentMaintenanceData = currentMaintenanceData;
    }

    public CustomizableItemEnum getCurrentSlotItem() {
        return currentSlotItem;
    }

    public void setCurrentSlotItem(CustomizableItemEnum currentSlotItem) {
        this.currentSlotItem = currentSlotItem;
    }

    public StockEditInfo getCurrentStockEditInfo() {
        return currentStockEditInfo;
    }

    public void setCurrentStockEditInfo(StockEditInfo currentStockEditInfo) {
        this.currentStockEditInfo = currentStockEditInfo;
    }

    public HashMap<VendingMachineButton, int[]> getIndependentItemPrices() {
        return independentItemPrices;
    }

    public HashMap<VendingMachineButton, int[]> getDependentItemPrices() {
        return dependentItemPrices;
    }

}
