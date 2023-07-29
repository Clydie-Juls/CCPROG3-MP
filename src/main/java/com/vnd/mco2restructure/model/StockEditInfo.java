package com.vnd.mco2restructure.model;

import com.vnd.mco2restructure.menu.CustomizableItemEnum;
import com.vnd.mco2restructure.menu.NonCustomizable;

import java.util.HashMap;

public class StockEditInfo {
    private HashMap<NonCustomizable, ItemEditInfo[]> itemAmount;

    public static class ItemEditInfo {
        private Integer amount;
        private boolean isChecked;

        public ItemEditInfo() {
            amount = 1;
            isChecked = true;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }

    public StockEditInfo() {
        itemAmount = new HashMap<>();
    }

    public HashMap<NonCustomizable, ItemEditInfo[]> getItemAmount() {
        return itemAmount;
    }

}
