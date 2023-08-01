package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.component.SlotInterface;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.Stocks;
import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

/**
 * The StockManagerController class manages the stock of items in the vending machine.
 * It handles updating the view, setting item enums, and navigating to the stock edit view.
 */
public class StockManagerController {
    private Stocks stocks;
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML private FlowPane menuLayout;

    /**
     * Updates the view of the stock in the vending machine.
     */
    public void updateView() {
        if (stocks.getItemEnums().size() == 0) {
            resetStockEnums();
        }
        menuLayout.getChildren().clear();
        for (int i = 0; i < stocks.getItemEnums().size(); i++) {
            SlotInterface slotInterface = new SlotInterface();
            int finalI = i;
            if (stocks.getItemEnums().get(finalI) != null) {
                if (stocks.getItemEnums().get(finalI) instanceof CustomizableItemEnum customizableItemEnum) {
                    slotInterface.getItemNameLabel().setText(customizableItemEnum.name().toLowerCase().
                            replaceAll("_", " "));
                    slotInterface.getItemTypeLabel().setText("Item type: Customizable Item");
                    Button editButton = new Button("Edit");
                    editButton.setOnAction(event -> gotoStockEdit(customizableItemEnum, stocks.getStockEditInfos().get(finalI)));
                    slotInterface.getBottomLayout().getChildren().add(editButton);
                    slotInterface.getItemImageView().setImage(new Image(
                            HelloApplication.class.getResourceAsStream(customizableItemEnum.getImageFile())));


                } else if (stocks.getItemEnums().get(finalI) instanceof IndependentItemEnum independentItemEnum) {
                    slotInterface.getItemNameLabel().setText(independentItemEnum.name().toLowerCase().
                            replaceAll("_", " "));
                    slotInterface.getItemTypeLabel().setText("Item type: Independent Item");
                    NumberField numberField = new NumberField();
                    slotInterface.setCenter(numberField);
                    StockEditInfo.ItemEditInfo itemEditInfo = stocks.getStockEditInfos().get(finalI).
                            getItemAmount().get(independentItemEnum)[0];
                    numberField.getTextField().setText(itemEditInfo.getAmount().toString());
                    numberField.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
                        // Try to parse the text into an integer
                        try {
                            int value = Integer.parseInt(newValue);
                            itemEditInfo.setAmount(value);
                        } catch (NumberFormatException ignored) {

                        }
                    });
                    slotInterface.getItemImageView().setImage(new Image(
                            HelloApplication.class.getResourceAsStream(independentItemEnum.getImageFile())));
                }
            }
            slotInterface.getChangeButton().setOnAction(event -> windowManager.gotoStockView(finalI,
                    programData.getCurrentVendingMachine() instanceof SpecialVendingMachine));
            menuLayout.getChildren().add(slotInterface);
        }
    }

    /**
     * Exits the stock manager view and navigates to the main features view.
     */
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    /**
     * Sets the WindowManager instance for the controller.
     *
     * @param windowManager The WindowManager instance to set.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Sets the ItemEnum and StockEditInfo for the given index in the stocks list.
     *
     * @param itemEnum The ItemEnum to set.
     * @param index    The index in the stocks list.
     */
    public void setSlotItemEnum(ItemEnum<? extends Item> itemEnum, int index) {
        stocks.getItemEnums().set(index, itemEnum);
        stocks.getStockEditInfos().set(index, new StockEditInfo());
        if(itemEnum instanceof CustomizableItemEnum customizableItemEnum) {
            for (CustomizableItemEnum.ItemType ingredient : customizableItemEnum.getIngredients()) {
                stocks.getStockEditInfos().get(index).getItemAmount().put(ingredient,
                        new StockEditInfo.ItemEditInfo[ingredient.getItems().length]);

                for (int i = 0; i < stocks.getStockEditInfos().get(index).getItemAmount().get(ingredient).length; i++) {
                    stocks.getStockEditInfos().get(index).getItemAmount().get(ingredient)[i] = new StockEditInfo.ItemEditInfo();
                }

            }
        } else if(itemEnum instanceof IndependentItemEnum independentItemEnum) {
            stocks.getStockEditInfos().get(index).getItemAmount().put(independentItemEnum, new StockEditInfo.ItemEditInfo[1]);
            stocks.getStockEditInfos().get(index).getItemAmount().get(independentItemEnum)[0] = new StockEditInfo.ItemEditInfo();
        }
    }

    /**
     * Sets the ProgramData instance for the controller.
     *
     * @param programData The ProgramData instance to set.
     */
    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }

    /**
     * Sets the Stocks instance for the controller.
     *
     * @param stocks The Stocks instance to set.
     */
    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }

    /**
     * Navigates to the stock edit view for the specified item and stock edit info.
     *
     * @param currentItem The current item to edit.
     * @param stockEditInfo The stock edit info for the item.
     */
    public void gotoStockEdit(CustomizableItemEnum currentItem, StockEditInfo stockEditInfo) {
        programData.setCurrentStockEditInfo(stockEditInfo);
        programData.setCurrentSlotItem(currentItem);
        windowManager.gotoStockEditView();
    }

    /**
     * Resets the item enums in the stocks list based on the current vending machine's slots.
     */
    public void resetStockEnums() {
        stocks.setItemData(programData.getCurrentVendingMachine().getSlots().length);
        for (int i = 0; i < programData.getCurrentVendingMachine().getSlots().length; i++) {
            System.out.println("FOSFKOSFKFSO");
            if(programData.getCurrentVendingMachine().getSlots()[i].getItem() != null) {
                int id = programData.getCurrentVendingMachine().getSlots()[i].getItem().getId();
                System.out.println(id);
                if( programData.getCurrentVendingMachine().getSlots()[i].getItem() instanceof CustomizableItem) {
                    setSlotItemEnum(CustomizableItemEnum.values()[id], i);
                } else {
                    setSlotItemEnum(IndependentItemEnum.values()[id], i);
                }
            }
        }
    }

    /**
     * Adds stocks to the vending machine and navigates back to the main features view.
     */
    public void addStocks() {
        System.out.println("SOmeoemeomeoe");
        windowManager.stock(stocks.getItemEnums(), stocks.getStockEditInfos());
        windowManager.gotoMntFeaturesView();
    }
}
