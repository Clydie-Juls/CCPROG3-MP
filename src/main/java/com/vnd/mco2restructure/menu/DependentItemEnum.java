package com.vnd.mco2restructure.menu;

public enum DependentItemEnum implements ItemEnum, NonCustomizable, Sellable{
    // Patties
    PINEAPPLE_PATTY(0, 0),
    A5_WAGYU_PATTY(0, 0),
    BEEF_PATTY(0, 0),
    CHICKEN_PATTY(0, 0),
    CHICKPEA_PATTY(0, 0),

    //Sauce and condiments
    TERIYAKI_SAUCE(0, 0),
    MAYO(0, 0),
    KETCHUP(0, 0),
    MUSTARD(0, 0),

    //Others
    PINEAPPLE_SLICES(0, 0),
    CARAMELIZED_ONIONS(0, 0),
    ARUGULA(0, 0),
    LETTUCE(0, 0),
    TOMATO(0, 0),
    PICKLES(0, 0);

    private int calories;
    private int price;

    DependentItemEnum(int calories, int price) {
        this.calories = calories;
        this.price = price;
    }
}
