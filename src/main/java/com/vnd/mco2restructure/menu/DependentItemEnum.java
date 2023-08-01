package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.DependentItem;

/**
 * The DependentItemEnum enum represents different types of dependent items in the vending machine.
 * It implements the ItemEnum interface for DependentItem, the NonCustomizable interface, and the Sellable interface.
 */
public enum DependentItemEnum implements ItemEnum<DependentItem>, NonCustomizable, Sellable {

    // Patties
    PINEAPPLE_PATTY(150, 120, "pineapple_patty.png"),
    A5_WAGYU_PATTY(500, 400, "a5_wagyu_patty.png"),
    BEEF_PATTY(250, 350, "beef_patty.png"),
    CHICKEN_PATTY(200, 300, "chicken_patty.png"),
    CHICKPEA_PATTY(180, 250, "chickpea_patty.png"),

    // Sauce and condiments
    TERIYAKI_SAUCE(50, 40, "teriyaki_sauce.png"),
    MAYO(30, 50, "mayo.png"),
    KETCHUP(20, 30, "ketchup.png"),
    MUSTARD(25, 20, "lettuce.png"),

    // Others
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

    /**
     * Constructs a DependentItemEnum with the provided calorie count, price, and image file.
     *
     * @param calories The calorie count of the dependent item.
     * @param price The price of the dependent item.
     * @param imageFile The image file for the dependent item.
     */
    DependentItemEnum(int calories, int price, String imageFile) {
        this.CALORIES = calories;
        this.PRICE = price;
        this.imageFile = imageFile;
    }

    /**
     * Converts the DependentItemEnum to a DependentItem instance based on the calorie count, price, and image file.
     *
     * @return The corresponding DependentItem instance.
     */
    @Override
    public DependentItem enumToItem() {
        return new DependentItem(this.name().toLowerCase().replaceAll("_", " "),
                CALORIES, this.ordinal(),  itemPrices[this.ordinal()], getImageFile());
    }

    /**
     * Gets the price of the dependent item.
     *
     * @return The price of the dependent item.
     */
    public int getPrice() {
        return itemPrices[this.ordinal()];
    }

    /**
     * Sets the prices for all dependent items.
     *
     * @param itemPrices The array of item prices.
     */
    public static void setItemPrices(int[] itemPrices) {
        DependentItemEnum.itemPrices = itemPrices;
    }

    /**
     * Sets the price for the current dependent item.
     *
     * @param price The price to be set for the dependent item.
     */
    public void setPrice(int price) {
        itemPrices[this.ordinal()] = price;
    }

    /**
     * Creates a new array of item prices for all dependent items.
     *
     * @return The array of new item prices.
     */
    public static int[] createNewItemPrices() {
        int[] newItemPrices = new int[DependentItemEnum.values().length];
        for (int i = 0; i < newItemPrices.length; i++) {
            newItemPrices[i] = DependentItemEnum.values()[i].PRICE;
        }
        return newItemPrices;
    }

    /**
     * Gets the image file path for the dependent item.
     *
     * @return The image file path.
     */
    public String getImageFile() {
        return "images/dependent-item/" + imageFile;
    }

    public int getCalories() {
        return CALORIES;
    }
}
