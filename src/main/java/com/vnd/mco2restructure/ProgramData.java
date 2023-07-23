package com.vnd.mco2restructure;

import com.vnd.mco2restructure.component.VendingMachineButton;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;

import java.util.HashMap;

public class ProgramData {
    private HashMap<VendingMachineButton, VendingMachine> vendingMachines;
    private VendingMachine currentVendingMachine;

    public ProgramData() {
        vendingMachines = new HashMap<>();
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
}
