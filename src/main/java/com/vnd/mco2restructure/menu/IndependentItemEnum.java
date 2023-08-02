package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.IndependentItem;

/**
 * The IndependentItemEnum enum represents different types of independent items in the vending machine.
 * It implements the ItemEnum interface for IndependentItem, the NonCustomizable interface, and the Sellable interface.
 */
public enum IndependentItemEnum implements ItemEnum<IndependentItem>, NonCustomizable, Sellable {

    /**
     * Coleslaw preset
     */
    COLESLAW(80, 100, "coleslaw.png"),

    // Cheese
    /**
     * American cheese preset
     */
    AMERICAN_CHEESE(40, 60, "american_cheese.png"),
    /**
     * Swiss cheese preset
     */
    SWISS_CHEESE(50, 70, "swiss_cheese.png"),
    /**
     * Feta cheese preset
     */
    FETA_CHEESE(60, 80, "feta_cheese.png"),

    // Buns
    /**
     * Sesame seed bun preset
     */
    SESAME_SEED_BUN(30, 100, "sesame_seed_bun.png"),
    /**
     * brioche bun preset
     */
    BRIOCHE_BUN(40, 120, "brioche_bun.png"),
    /**
     * Whole wheat bun preset
     */
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

    /**
     * Constructs an IndependentItemEnum with the provided calorie count, price, and image file.
     *
     * @param calories The calorie count of the independent item.
     * @param price The price of the independent item.
     * @param imageFile The image file for the independent item.
     */
    IndependentItemEnum(int calories, int price, String imageFile) {
        this.CALORIES = calories;
        this.PRICE = price;
        this.imageFile = imageFile;
    }

    /**
     * Converts the IndependentItemEnum to an IndependentItem instance based on the calorie count, price, and image file.
     *
     * @return The corresponding IndependentItem instance.
     */
    @Override
    public IndependentItem enumToItem() {
        return new IndependentItem(this.name().toLowerCase().replaceAll("_", " ")
                , CALORIES, this.ordinal(), itemPrices[this.ordinal()], getImageFile());
    }

    /**
     * Gets the price of the independent item.
     *
     * @return The price of the independent item.
     */
    public int getPrice() {
        return itemPrices[this.ordinal()];
    }

    /**
     * Sets the price for the current independent item.
     *
     * @param price The price to be set for the independent item.
     */
    public void setPrice(int price) {
        itemPrices[this.ordinal()] = price;
    }

    /**
     * Sets the prices for all independent items.
     *
     * @param itemPrices The array of item prices.
     */
    public static void setItemPrices(int[] itemPrices) {
        IndependentItemEnum.itemPrices = itemPrices;
    }

    /**
     * Gets the image file path for the independent item.
     *
     * @return The image file path.
     */
    public String getImageFile() {
        return "images/independent-item/" + imageFile;
    }

    /**
     * Creates a new array of item prices for all independent items.
     *
     * @return The array of new item prices.
     */
    public static int[] createNewItemPrices() {
        int[] newItemPrices = new int[IndependentItemEnum.values().length];
        for (int i = 0; i < newItemPrices.length; i++) {
            newItemPrices[i] = IndependentItemEnum.values()[i].PRICE;
        }
        return newItemPrices;
    }

    /**
     * This method returns the calories of the preset item
     * @return calories of the preset item
     */
    public int getCalories() {
        return CALORIES;
    }
}
