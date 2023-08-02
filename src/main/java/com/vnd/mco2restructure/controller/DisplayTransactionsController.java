package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.model.ItemTransactData;
import com.vnd.mco2restructure.model.Transactions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller class for the display transactions view.
 */
public class DisplayTransactionsController implements Initializable {

    @FXML private Label totalMoneyLabel;
    @FXML private TableColumn<ItemTransactData, String> itemNameColumn;
    @FXML private TableColumn<ItemTransactData, String> itemTypeColumn;
    @FXML private TableColumn<ItemTransactData, String> itemPrevStockColumn;
    @FXML private TableColumn<ItemTransactData, String> itemInStockColumn;
    @FXML private TableColumn<ItemTransactData, String> noOfItemSoldColumn;
    @FXML private TableColumn<ItemTransactData, String> profitCollectedColumn;
    @FXML private TableView<ItemTransactData> tableView;
    private Transactions transactions;

    private WindowManager windowManager;

    /**
     * Handles the action when the "Exit" button is clicked.
     * Goes back to the main features view.
     */
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    /**
     * Renders and update the table data view and other info regarding transaction history
     */
    public void updateView() {
        tableView.setItems((FXCollections.observableArrayList(transactions.getItemLogs().values())));
        tableView.sort();
        tableView.refresh();
        itemTypeColumn.setSortType(TableColumn.SortType.ASCENDING);
        itemTypeColumn.setComparator(String::compareToIgnoreCase);
        tableView.getSortOrder().add(itemTypeColumn);
        totalMoneyLabel.setText("Total Money Collected: " +
                transactions.getItemLogs().values().stream().mapToInt(
                        v -> v.getProfitCollected() != null ? v.getProfitCollected() : 0 ).sum());
    }

    /**
     * Sets the WindowManager for this controller.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * This method sets the transaction instance
     * @param transactions - Vending machine transaction handler
     */
    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
        System.out.println(transactions == null);
    }

    /**
     * Initializes the table columns
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemTypeColumn.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        itemPrevStockColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrevStock"));
        itemInStockColumn.setCellValueFactory(new PropertyValueFactory<>("itemInStock"));
        noOfItemSoldColumn.setCellValueFactory(new PropertyValueFactory<>("noOfItemSold"));
        profitCollectedColumn.setCellValueFactory(new PropertyValueFactory<>("profitCollected"));
    }
}
