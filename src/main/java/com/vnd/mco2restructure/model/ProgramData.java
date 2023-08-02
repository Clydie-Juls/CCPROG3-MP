package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.component.VendingMachineButton;
import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.DependentItemEnum;
import com.vnd.mco2restructure.menu.IndependentItemEnum;
import com.vnd.mco2restructure.model.MaintenanceData;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;

import java.util.HashMap;

/**
 * The ProgramData class holds various data related to the vending machine simulator program.
 */
public class ProgramData {
    private HashMap<VendingMachineButton, VendingMachine> vendingMachines;
    private HashMap<VendingMachineButton, MaintenanceData> maintenanceDatas;
    private HashMap<VendingMachineButton, int[]> independentItemPrices;
    private HashMap<VendingMachineButton, int[]> dependentItemPrices;
    private VendingMachine currentVendingMachine;
    private MaintenanceData currentMaintenanceData;
    private CustomizableItemEnum currentSlotItem;
    private StockEditInfo currentStockEditInfo;

    /**
     * Constructs a ProgramData object with empty data containers.
     */
    public ProgramData() {
        vendingMachines = new HashMap<>();
        maintenanceDatas = new HashMap<>();
        independentItemPrices = new HashMap<>();
        dependentItemPrices = new HashMap<>();
    }

    // Getters and setters for various data

    /**
     *  this method returns the list of vending machines
     * @return hashmap of vending machines
     */
    public HashMap<VendingMachineButton, VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    /**
     *  this method returns the list of vending machines
     * @return current vending machines
     */
    public VendingMachine getCurrentVendingMachine() {
        return currentVendingMachine;
    }

    /**
     * this method sets the current vending machine
     * @param currentVendingMachine - current vending machine to test
     */
    public void setCurrentVendingMachine(VendingMachine currentVendingMachine) {
        this.currentVendingMachine = currentVendingMachine;
    }

    /**
     * this method returns a hashmap of maintenance datas
     * @return hashmap of maintenance datas
     */
    public HashMap<VendingMachineButton, MaintenanceData> getMaintenanceDatas() {
        return maintenanceDatas;
    }

    /**
     * this method returns the current maintenance data used
     * @return current maintenance data used
     */
    public MaintenanceData getCurrentMaintenanceData() {
        return currentMaintenanceData;
    }

    /**
     * this method set the current maintenance data used
     * @param currentMaintenanceData current maintenance data used
     */
    public void setCurrentMaintenanceData(MaintenanceData currentMaintenanceData) {
        this.currentMaintenanceData = currentMaintenanceData;
    }

    /**
     * this method returns the current slot item
     * @return - current slot item
     */
    public CustomizableItemEnum getCurrentSlotItem() {
        return currentSlotItem;
    }

    /**
     * this method sets the current slot item
     * @param currentSlotItem - current slot item used
     */
    public void setCurrentSlotItem(CustomizableItemEnum currentSlotItem) {
        this.currentSlotItem = currentSlotItem;
    }

    /**
     * this method returns the current stock edit info
     * @return current stock edit info
     */
    public StockEditInfo getCurrentStockEditInfo() {
        return currentStockEditInfo;
    }

    /**
     * this method sets the current stock edit info
     * @param currentStockEditInfo  current stock edit info
     */

    public void setCurrentStockEditInfo(StockEditInfo currentStockEditInfo) {
        this.currentStockEditInfo = currentStockEditInfo;
    }

    /**
     * this method returns the independent Item prices(acts as a database)
     * @return independent Item prices(acts as a database)
     */
    public HashMap<VendingMachineButton, int[]> getIndependentItemPrices() {
        return independentItemPrices;
    }

    /**
     * this method returns the dependent Item prices(acts as a database)
     * @return dependent Item prices(acts as a database)
     */
    public HashMap<VendingMachineButton, int[]> getDependentItemPrices() {
        return dependentItemPrices;
    }

}
