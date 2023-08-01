package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemTransaction;
import com.vnd.mco2restructure.model.ItemTransactData;
import com.vnd.mco2restructure.model.Transactions;
import com.vnd.mco2restructure.model.items.CustomizableItem;
import com.vnd.mco2restructure.model.items.Item;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

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

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
        System.out.println(transactions == null);
    }

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
