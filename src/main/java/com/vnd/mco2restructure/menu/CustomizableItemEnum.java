package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;

import java.util.ArrayList;
import java.util.HashMap;

public enum CustomizableItemEnum implements ItemEnum<CustomizableItem>, Sellable {
    BASIC_BURGER("basic_burger.png", ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    VEGGIE_BURGER("veggie_burger.png",ItemType.BUN_TYPES, ItemType.PINEAPPLE_PATTY, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    CHICKEN_BURGER("chicken_burger.png",ItemType.BUN_TYPES, ItemType.CHICKEN_BURGER, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    CHEESEBURGER("cheese_burger.png",ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.CHEESE_TYPES, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    DELUXE_BURGER("deluxe_burger.png",ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.CHEESE_TYPES, ItemType.OTHER_TYPES,
            ItemType.SAUCE_AND_CONDIMENT_TYPES);

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
        return new CustomizableItem(this.toString().toLowerCase().replaceAll("_", " ")
                , itemsDerived, this.ordinal(), getImageFile());
    }

    public enum ItemType implements NonCustomizable {
        // Independent Items
        BUN_TYPES ("Buns",
                        IndependentItemEnum.SESAME_SEED_BUN, IndependentItemEnum.BRIOCHE_BUN, IndependentItemEnum.WHOLE_WHEAT_BUN),
        CHEESE_TYPES("Cheese",
                IndependentItemEnum.AMERICAN_CHEESE, IndependentItemEnum.FETA_CHEESE, IndependentItemEnum.SWISS_CHEESE),
        COLESLAW(null, IndependentItemEnum.COLESLAW),

        // Dependent items
        PATTY_TYPES("Patties", DependentItemEnum.A5_WAGYU_PATTY, DependentItemEnum.BEEF_PATTY,
                DependentItemEnum.CHICKEN_PATTY, DependentItemEnum.PINEAPPLE_PATTY, DependentItemEnum.CHICKPEA_PATTY),
        SAUCE_AND_CONDIMENT_TYPES("Sauce and Condiments", DependentItemEnum.TERIYAKI_SAUCE,
                DependentItemEnum.MAYO, DependentItemEnum.KETCHUP, DependentItemEnum.MUSTARD),
        OTHER_TYPES("Others", DependentItemEnum.PINEAPPLE_SLICES, DependentItemEnum.CARAMELIZED_ONIONS,
                DependentItemEnum.ARUGULA, DependentItemEnum.LETTUCE, DependentItemEnum.TOMATO,
                DependentItemEnum.PICKLES),

        //Single Types
        PINEAPPLE_PATTY(null, DependentItemEnum.PINEAPPLE_PATTY),
        CHICKEN_BURGER(null, DependentItemEnum.CHICKEN_PATTY);

        private String itemTypeName;
        private NonCustomizable[] items;

        ItemType(String itemTypeName, NonCustomizable... items) {
            this.itemTypeName = itemTypeName;
            this.items = items;
        }

        public String getItemTypeName() {
            return itemTypeName;
        }

        public NonCustomizable[] getItems() {
            return items;
        }
    }

    private ItemType[] ingredients;
    private int[] ingredientTypes;

    private String imageFile;

    CustomizableItemEnum(String imageFile, ItemType... ingredients) {
        this.ingredients = ingredients;
        this.imageFile = imageFile;
        ingredientTypes = new int[ingredients.length];
    }


    public ItemType[] getIngredients() {
        return ingredients;
    }

    public String getImageFile() {
        return "images/customizable-item/" + imageFile;
    }
}
