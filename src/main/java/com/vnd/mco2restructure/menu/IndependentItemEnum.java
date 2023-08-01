package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.IndependentItem;

public enum IndependentItemEnum implements ItemEnum<IndependentItem>, NonCustomizable, Sellable {
    COLESLAW( 80, 100,"coleslaw.png"),

    // Cheese
    AMERICAN_CHEESE(40, 60,"american_cheese.png"),
    SWISS_CHEESE(50, 70, "swiss_cheese.png"),
    FETA_CHEESE(60, 80, "feta_cheese.png"),

    //Buns
    SESAME_SEED_BUN(30, 100, "sesame_seed_bun.png"),
    BRIOCHE_BUN(40, 120, "brioche_bun.png"),
    WHOLE_WHEAT_BUN(35, 90, "whole_wheat_bun.png");

    private static int[] itemPrices;

    static {
        itemPrices = new int[IndependentItemEnum.values().length];
        for (int i = 0; i < itemPrices.length; i++) {
            itemPrices[i] = IndependentItemEnum.values()[i].PRICE;
        }
    }

    private final int CALORIES;
    private final int PRICE;
    private String imageFile;

    IndependentItemEnum(int calories, int price, String imageFile) {
        this.CALORIES = calories;
        this.PRICE = price;
        this.imageFile = imageFile;
    }

    @Override
    public IndependentItem enumToItem() {
        return new IndependentItem(this.name().toLowerCase(), CALORIES, this.ordinal(), itemPrices[this.ordinal()], getImageFile());
    }

    public int getPrice() {
        return itemPrices[this.ordinal()];
    }

    public void setPrice(int price) {
        itemPrices[this.ordinal()] = price;
    }

    public static void setItemPrices(int[] itemPrices) {
        IndependentItemEnum.itemPrices = itemPrices;
    }

    public String getImageFile() {
        return "images/independent-item/" + imageFile;
    }

    public static int[] createNewItemPrices() {
        int[] newItemPrices = new int[IndependentItemEnum.values().length];
        for (int i = 0; i < newItemPrices.length; i++) {
            newItemPrices[i] = IndependentItemEnum.values()[i].PRICE;
        }
        return newItemPrices;
    }
}
