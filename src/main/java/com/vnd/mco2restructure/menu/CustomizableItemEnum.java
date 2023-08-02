package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;

import java.util.HashMap;

/**
 * The CustomizableItemEnum enum represents different types of customizable items in the vending machine.
 * It implements the ItemEnum interface for CustomizableItem and the Sellable interface.
 */
public enum CustomizableItemEnum implements ItemEnum<CustomizableItem>, Sellable {

    /**
     * Basic burger preset
     */
    BASIC_BURGER("basic_burger.png", ItemType.BUN_TYPES, ItemType.PATTY_TYPES,
            ItemType.SAUCE_AND_CONDIMENT_TYPES),
    /**
     * Veggie burger preset
     */
    VEGGIE_BURGER("veggie_burger.png", ItemType.BUN_TYPES, ItemType.PINEAPPLE_PATTY,
            ItemType.SAUCE_AND_CONDIMENT_TYPES),
    /**
     * Chicken burger preset
     */
    CHICKEN_BURGER("chicken_burger.png", ItemType.BUN_TYPES, ItemType.CHICKEN_BURGER,
            ItemType.SAUCE_AND_CONDIMENT_TYPES),
    /**
     * Cheeseburger preset
     */
    CHEESEBURGER("cheese_burger.png", ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.CHEESE_TYPES,
            ItemType.SAUCE_AND_CONDIMENT_TYPES),
    /**
     * Deluxe burger preset
     */
    DELUXE_BURGER("deluxe_burger.png", ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.CHEESE_TYPES,
            ItemType.OTHER_TYPES, ItemType.SAUCE_AND_CONDIMENT_TYPES);

    /**
     * Converts the CustomizableItemEnum to a CustomizableItem instance based on the ingredients and image file.
     *
     * @return The corresponding CustomizableItem instance.
     */
    @Override
    public CustomizableItem enumToItem() {
        HashMap<String, NonCustomizableItem[]> itemsDerived = new HashMap<>();
        for (ItemType ingredient : ingredients) {
            NonCustomizableItem[] nonCustomizableItems = new NonCustomizableItem[ingredient.items.length];
            for (int j = 0; j < ingredient.items.length; j++) {
                if (ingredient.items[j] instanceof IndependentItemEnum independentItemEnum) {
                    nonCustomizableItems[j] = independentItemEnum.enumToItem();
                } else if (ingredient.items[j] instanceof DependentItemEnum dependentItemEnum) {
                    nonCustomizableItems[j] = dependentItemEnum.enumToItem();
                }
            }
            itemsDerived.put(ingredient.itemTypeName, nonCustomizableItems);
        }
        return new CustomizableItem(this.toString().toLowerCase().replaceAll("_", " "),
                itemsDerived, this.ordinal(), getImageFile());
    }

    /**
     * Represents the types of items that can be combined to create a customizable item.
     */
    public enum ItemType implements NonCustomizable {
        // Independent Items
        /**
         * all available types of bun
         */
        BUN_TYPES("Buns",
                IndependentItemEnum.SESAME_SEED_BUN, IndependentItemEnum.BRIOCHE_BUN,
                IndependentItemEnum.WHOLE_WHEAT_BUN),
        /**
         * all available types of cheese
         */
        CHEESE_TYPES("Cheese",
                IndependentItemEnum.AMERICAN_CHEESE, IndependentItemEnum.FETA_CHEESE, IndependentItemEnum.SWISS_CHEESE),
        COLESLAW(null, IndependentItemEnum.COLESLAW),

        // Dependent items
        /**
         * all available types of patty
         */
        PATTY_TYPES("Patties", DependentItemEnum.A5_WAGYU_PATTY, DependentItemEnum.BEEF_PATTY,
                DependentItemEnum.CHICKEN_PATTY, DependentItemEnum.PINEAPPLE_PATTY, DependentItemEnum.CHICKPEA_PATTY),
        /**
         * all available types of sauce and condiments
         */
        SAUCE_AND_CONDIMENT_TYPES("Sauce and Condiments", DependentItemEnum.TERIYAKI_SAUCE,
                DependentItemEnum.MAYO, DependentItemEnum.KETCHUP, DependentItemEnum.MUSTARD),
        /**
         * all available types of others
         */
        OTHER_TYPES("Others", DependentItemEnum.PINEAPPLE_SLICES, DependentItemEnum.CARAMELIZED_ONIONS,
                DependentItemEnum.ARUGULA, DependentItemEnum.LETTUCE, DependentItemEnum.TOMATO,
                DependentItemEnum.PICKLES),

        // Single Types
        /**
         * pineapple patty single type
         */
        PINEAPPLE_PATTY(null, DependentItemEnum.PINEAPPLE_PATTY),
        /**
         * chicken burger preset
         */
        CHICKEN_BURGER(null, DependentItemEnum.CHICKEN_PATTY);

        private String itemTypeName;
        private NonCustomizable[] items;

        /**
         * initializes the item type presets
         * @param itemTypeName name of the item type section
         * @param items - single type items
         */
        ItemType(String itemTypeName, NonCustomizable... items) {
            this.itemTypeName = itemTypeName;
            this.items = items;
        }

        /**
         * Gets the item type name.
         *
         * @return The item type name.
         */
        public String getItemTypeName() {
            return itemTypeName;
        }

        /**
         * Gets the array of non-customizable items in this item type.
         *
         * @return The array of non-customizable items.
         */
        public NonCustomizable[] getItems() {
            return items;
        }
    }

    private ItemType[] ingredients;


    private String imageFile;

    /**
     * Constructs a CustomizableItemEnum with the provided image file and ingredients.
     *
     * @param imageFile The image file for the customizable item.
     * @param ingredients The item types that can be combined to create this customizable item.
     */
    CustomizableItemEnum(String imageFile, ItemType... ingredients) {
        this.ingredients = ingredients;
        this.imageFile = imageFile;
    }


    /**
     * Gets the array of item types that can be combined to create this customizable item.
     *
     * @return The array of item types.
     */
    public ItemType[] getIngredients() {
        return ingredients;
    }

    /**
     * Gets the image file path for this customizable item.
     *
     * @return The image file path.
     */
    public String getImageFile() {
        return "images/customizable-item/" + imageFile;
    }
}
