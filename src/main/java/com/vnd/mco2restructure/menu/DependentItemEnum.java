package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.DependentItem;

public enum DependentItemEnum implements ItemEnum<DependentItem>, NonCustomizable, Sellable{
    // Patties
    PINEAPPLE_PATTY(150, 120, "pineapple_patty.png"),
    A5_WAGYU_PATTY(500,400, "a5_wagyu_patty.png"),
    BEEF_PATTY(250, 350, "beef_patty.png"),
    CHICKEN_PATTY(200, 300, "chicken_patty.png"),
    CHICKPEA_PATTY(180, 250, "chickpea_patty.png"),

    //Sauce and condiments
    TERIYAKI_SAUCE(50, 40, "teriyaki_sauce.png"),
    MAYO(30, 50, "mayo.png"),
    KETCHUP(20, 30, "ketchup.png"),
    MUSTARD(25, 20, "lettuce.png"),

    //Others
    PINEAPPLE_SLICES(40, 20, "pineapple_slices.png"),
    CARAMELIZED_ONIONS(60, 30, "caramelized_onions.png"),
    ARUGULA(25, 15, "arugula.png"),
    LETTUCE(20, 10, "lettuce.png"),
    TOMATO(30, 15, "tomato.png"),
    PICKLES(35, 10, "pickles.png");

    private static int[] itemPrices;

    static {
        itemPrices = new int[DependentItemEnum.values().length];
        for (int i = 0; i < itemPrices.length; i++) {
            itemPrices[i] = DependentItemEnum.values()[i].PRICE;
        }
    }
    private final int CALORIES;
    private final int PRICE;
    private String imageFile;

    DependentItemEnum(int calories, int price, String imageFile) {
        this.CALORIES = calories;
        this.PRICE = price;
        this.imageFile = imageFile;
    }

    @Override
    public DependentItem enumToItem() {
        return new DependentItem(this.name().toLowerCase(), CALORIES, this.ordinal(),  itemPrices[this.ordinal()], getImageFile());
    }

    public int getPrice() {
        return itemPrices[this.ordinal()];
    }

    public static void setItemPrices(int[] itemPrices) {
        DependentItemEnum.itemPrices = itemPrices;
    }

    public void setPrice(int price) {
        itemPrices[this.ordinal()] = price;
    }

    public static int[] createNewItemPrices() {
        int[] newItemPrices = new int[DependentItemEnum.values().length];
        for (int i = 0; i < newItemPrices.length; i++) {
            newItemPrices[i] = DependentItemEnum.values()[i].PRICE;
        }
        return newItemPrices;
    }

    public String getImageFile() {
        return "images/dependent-item/" + imageFile;
    }
}
