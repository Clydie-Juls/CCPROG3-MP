package com.vnd.mco2restructure.model;

public class ItemTransactData {
    private String itemName;
    private String itemType;
    private String itemPrevStock;
    private String itemInStock;
    private String itemDuringStock;
    private Integer noOfItemSold;
    private Integer profitCollected;

    public ItemTransactData(String itemName, String itemType, String itemPrevStock,
                            String itemInStock, Integer noOfItemSold, Integer profitCollected) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrevStock = itemPrevStock;
        this.itemInStock = itemInStock;
        this.noOfItemSold = noOfItemSold;
        this.profitCollected = profitCollected;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemPrevStock() {
        return itemPrevStock;
    }

    public void setItemPrevStock(String itemPrevStock) {
        this.itemPrevStock = itemPrevStock;
    }

    public String getItemInStock() {
        return itemInStock;
    }

    public void setItemInStock(String itemInStock) {
        this.itemInStock = itemInStock;
    }

    public Integer getNoOfItemSold() {
        return noOfItemSold;
    }

    public void setNoOfItemSold(Integer noOfItemSold) {
        this.noOfItemSold = noOfItemSold;
    }

    public Integer getProfitCollected() {
        return profitCollected;
    }

    public void setProfitCollected(Integer profitCollected) {
        this.profitCollected = profitCollected;
    }

    public String getItemDuringStock() {
        return itemDuringStock;
    }

    public void setItemDuringStock(String itemDuringStock) {
        this.itemDuringStock = itemDuringStock;
    }
}
