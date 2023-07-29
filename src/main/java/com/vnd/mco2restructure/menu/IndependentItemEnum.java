package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.IndependentItem;

public enum IndependentItemEnum implements ItemEnum<IndependentItem>, NonCustomizable, Sellable {
    COLESLAW(80, 100),

    // Cheese
    AMERICAN_CHEESE(40, 60),
    SWISS_CHEESE(50, 70),
    FETA_CHEESE(60, 80),

    //Buns
    SESAME_SEED_BUN(30, 100),
    BRIOCHE_BUN(40, 120),
    WHOLE_WHEAT_BUN(35, 90);
    private final int CALORIES;
    private final int PRICE;

    IndependentItemEnum(int calories, int price) {
        this.CALORIES = calories;
        this.PRICE = price;
    }

    @Override
    public IndependentItem enumToItem() {
        return new IndependentItem(this.name().toLowerCase(), CALORIES, this.ordinal(), PRICE);
    }
}
