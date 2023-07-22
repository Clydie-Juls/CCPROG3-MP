package com.vnd.mco2restructure.menu;

public enum IndependentItemEnum implements ItemEnum, NonCustomizable {
    COLESLAW(0, 0),

    // Cheese
    AMERICAN_CHEESE(0, 0),
    SWISS_CHEESE(0, 0),
    FETA_CHEESE(0, 0),

    //Buns
    SESAME_SEED_BUN(0, 0),
    BRIOCHE_BUN(0, 0),
    WHOLE_WHEAT_BUN(0, 0);
    private final int CALORIES;
    private final int PRICE;

    IndependentItemEnum(int calories, int price) {
        this.CALORIES = calories;
        this.PRICE = price;
    }

    public int getCalories() {
        return CALORIES;
    }

    public int getPrice() {
        return PRICE;
    }
}
