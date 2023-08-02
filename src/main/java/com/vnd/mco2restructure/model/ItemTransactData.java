package com.vnd.mco2restructure.model;

/**
 * This class is a represents the data for the transaction history
 */
public class ItemTransactData {
    private String itemName;
    private String itemType;
    private String itemPrevStock;
    private String itemInStock;
    private String itemDuringStock;
    private Integer noOfItemSold;
    private Integer profitCollected;

    /**
     * initializes an item transact data
     * @param itemName - name of the item
     * @param itemType - type of the item(dependent, independent, customizable)
     * @param itemPrevStock - previous stock of the item
     * @param itemInStock - current stock of the item
     * @param noOfItemSold - total no of item sold
     * @param profitCollected - total profit from the item
     */
    public ItemTransactData(String itemName, String itemType, String itemPrevStock,
                            String itemInStock, Integer noOfItemSold, Integer profitCollected) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrevStock = itemPrevStock;
        this.itemInStock = itemInStock;
        this.noOfItemSold = noOfItemSold;
        this.profitCollected = profitCollected;
    }

    /**
     * This method returns the item name
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *  This method sets the item name
     * @param itemName name of the item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * This method returns the item type
     * @return item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     *  This method sets the item type
     * @param itemType item type
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * This method returns the item previous stock
     * @return item previous stock
     */
    public String getItemPrevStock() {
        return itemPrevStock;
    }

    /**
     * This method sets the item previous stock
     * @param itemPrevStock item previous stock
     */
    public void setItemPrevStock(String itemPrevStock) {
        this.itemPrevStock = itemPrevStock;
    }

    /**
     * This method returns the item current stock
     * @return item current stock
     */
    public String getItemInStock() {
        return itemInStock;
    }

    /**
     * This method sets the item current stock
     * @param itemInStock item current stock
     */
    public void setItemInStock(String itemInStock) {
        this.itemInStock = itemInStock;
    }

    /**
     * This method returns the no of item sold
     * @return no of item sold
     */
    public Integer getNoOfItemSold() {
        return noOfItemSold;
    }

    /**
     * This method sets the no. of item sold
     * @param noOfItemSold no. of item sold
     */
    public void setNoOfItemSold(Integer noOfItemSold) {
        this.noOfItemSold = noOfItemSold;
    }

    /**
     * This method returns the total profit collected
     * @return total profit collected
     */
    public Integer getProfitCollected() {
        return profitCollected;
    }

    /**
     * This method sets the total profit collected
     * @param profitCollected total profit collected
     */
    public void setProfitCollected(Integer profitCollected) {
        this.profitCollected = profitCollected;
    }

    /**
     * This method returns the item during stock
     * @return item name
     */
    public String getItemDuringStock() {
        return itemDuringStock;
    }

    /**
     * This method sets the item during stock
     * @param itemDuringStock item during stock
     */
    public void setItemDuringStock(String itemDuringStock) {
        this.itemDuringStock = itemDuringStock;
    }
}
