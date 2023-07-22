package com.vnd.mco2restructure;

import com.vnd.mco2restructure.component.VendingMachineButton;
import com.vnd.mco2restructure.model.vendingmachine.VendingMachine;

import java.util.HashMap;

public class ProgramData {
    //TODO: Change the value to VendingMachine class
    HashMap<VendingMachineButton, VendingMachine> vendingMachines;

    public ProgramData() {
        vendingMachines = new HashMap<>();
    }

    public HashMap<VendingMachineButton, VendingMachine> getVendingMachines() {
        return vendingMachines;
    }
}
