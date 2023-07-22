package com.vnd.mco2restructure;

import com.vnd.mco2restructure.component.VendingMachineButton;

import java.util.HashMap;

public class ProgramData {
    //TODO: Change the value to VendingMachine class
    HashMap<VendingMachineButton, String> vendingMachines;

    public ProgramData() {
        vendingMachines = new HashMap<>();
    }

    public HashMap<VendingMachineButton, String> getVendingMachines() {
        return vendingMachines;
    }
}
