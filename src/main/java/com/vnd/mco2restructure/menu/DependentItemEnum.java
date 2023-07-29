package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.DependentItem;

public enum DependentItemEnum implements ItemEnum<DependentItem>, NonCustomizable, Sellable{
    // Patties
    PINEAPPLE_PATTY(150, 120),
    A5_WAGYU_PATTY(500,400),
    BEEF_PATTY(250, 350),
    CHICKEN_PATTY(200, 300),
    CHICKPEA_PATTY(180, 250),

    //Sauce and condiments
    TERIYAKI_SAUCE(50, 40),
    MAYO(30, 50),
    KETCHUP(20, 30),
    MUSTARD(25, 20),

    //Others
    PINEAPPLE_SLICES(40, 20),
    CARAMELIZED_ONIONS(60, 30),
    ARUGULA(25, 15),
    LETTUCE(20, 10),
    TOMATO(30, 15),
    PICKLES(35, 10);

    private final int CALORIES;
    private final int PRICE;

    DependentItemEnum(int calories, int price) {
        this.CALORIES = calories;
        this.PRICE = price;
    }

    @Override
    public DependentItem enumToItem() {
        return new DependentItem(this.name().toLowerCase(), CALORIES, this.ordinal(), PRICE);
    }
}
