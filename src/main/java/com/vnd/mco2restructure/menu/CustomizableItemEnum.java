package com.vnd.mco2restructure.menu;

import java.util.ArrayList;

public enum CustomizableItemEnum implements ItemEnum, Sellable {
    BASIC_BURGER(ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    VEGGIE_BURGER(ItemType.BUN_TYPES, ItemType.PINEAPPLE_PATTY, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    CHICKEN_BURGER(ItemType.BUN_TYPES, ItemType.CHICKEN_BURGER, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    CHEESEBURGER(ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.CHEESE_TYPES, ItemType.SAUCE_AND_CONDIMENT_TYPES),
    DELUXE_BURGER(ItemType.BUN_TYPES, ItemType.PATTY_TYPES, ItemType.CHEESE_TYPES, ItemType.OTHER_TYPES,
            ItemType.SAUCE_AND_CONDIMENT_TYPES);

    public enum ItemType  {
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
    private ArrayList<NonCustomizable> addons;

    CustomizableItemEnum(ItemType... ingredients) {
        this.ingredients = ingredients;
        this.addons = new ArrayList<>();
    }

    public void addAddon(NonCustomizable addon) {
        addons.add(addon);
    }

    public ItemType[] getIngredients() {
        return ingredients;
    }

    public ArrayList<NonCustomizable> getAddons() {
        return addons;
    }
}
