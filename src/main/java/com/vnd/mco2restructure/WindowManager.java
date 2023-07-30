package com.vnd.mco2restructure;

import com.vnd.mco2restructure.controller.*;
import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.StockData;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.Stocks;
import com.vnd.mco2restructure.model.items.Item;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WindowManager {
    private final int IPHONE13_PRO_WIDTH = 1170;
    private final int IPHONE13_PRO_HEIGHT = 2532;
    private final Stage window;
    private final ProgramData PROGRAM_DATA;
    private HomeController homeController;
    private MainMenuController mainMenuController;
    private MaintenanceService maintenanceService;
    private VendingMachineController vendingMachineController;
    private StockController stockController;
    private RestockController restockController;
    private ChangeItemPriceController changeItemPriceController;
    private DisplayTransactionsController displayTransactionsController;
    private StockManagerController stockManagerController;
    private StockEditController stockEditController;
    private CollectMoneyController collectMoneyController;
    private ProvideDenomController provideDenomController;
    private ItemBuyController itemBuyController;
    private Scene homeScene;
    private Scene mainMenuScene;
    private StackPane vndFeaturesLayout;
    private StackPane mntFeaturesLayout;
    private BorderPane stockLayout;
    private BorderPane restockLayout;
    private BorderPane changeItemPriceLayout;
    private BorderPane displayTransactionsLayout;
    private BorderPane stockManagerLayout;
    private BorderPane stockEditLayout;
    private StackPane itemBuyLayout;
    private Pane currentMntLayout;

    public WindowManager(Stage window) {
        this.window = window;
        PROGRAM_DATA = new ProgramData();
        setup();
    }
    void setup() {
        try {
            // Home View Setup
            FXMLLoader homeView = new FXMLLoader(getClass().getResource("pages/HomeView.fxml"));
            homeScene = createIphoneScene(homeView.load());
            homeController = homeView.getController();
            homeController.setWindowManager(this);
            homeController.setProgramData(PROGRAM_DATA);

            //Main Menu View Setup
            FXMLLoader mainMenuView = new FXMLLoader(getClass().getResource("pages/MainMenuView.fxml"));
            mainMenuScene = createIphoneScene(mainMenuView.load());
            mainMenuController = mainMenuView.getController();
            mainMenuController.setWindowManager(this);

            //Vnd Features View Setup
            FXMLLoader vndFeaturesView = new FXMLLoader(getClass().getResource("pages/VndFeaturesView.fxml"));
            vndFeaturesLayout = vndFeaturesView.load();
            vendingMachineController = vndFeaturesView.getController();
            vendingMachineController.setWindowManager(this);

            //Mnt Features View Setup
            FXMLLoader mntFeaturesView = new FXMLLoader(getClass().getResource("pages/MntFeaturesView.fxml"));
            mntFeaturesLayout = mntFeaturesView.load();
            maintenanceService = mntFeaturesView.getController();
            maintenanceService.setWindowManager(this);

            //Stock View Setup
            FXMLLoader stockView = new FXMLLoader(getClass().getResource("pages/StockView.fxml"));
            stockLayout = stockView.load();
            stockController = stockView.getController();
            stockController.setWindowManager(this);
            stockController.setStockData(new StockData());

            //Restock View Setup
            FXMLLoader restockView = new FXMLLoader(getClass().getResource("pages/RestockView.fxml"));
            restockLayout = restockView.load();
            restockController = restockView.getController();
            restockController.setWindowManager(this);

            //Change Item Price View Setup
            FXMLLoader changeItemPriceView = new FXMLLoader(getClass().getResource("pages/ChangeItemPriceView.fxml"));
            changeItemPriceLayout = changeItemPriceView.load();
            changeItemPriceController = changeItemPriceView.getController();
            changeItemPriceController.setWindowManager(this);

            //Display Transactions View Setup
            FXMLLoader displayTransactionsView = new FXMLLoader(getClass().getResource("pages/DisplayTransactionsView.fxml"));
            displayTransactionsLayout = displayTransactionsView.load();
            displayTransactionsController = displayTransactionsView.getController();
            displayTransactionsController.setWindowManager(this);

            //Stock Manager View Setup
            FXMLLoader stockManagerView = new FXMLLoader(getClass().getResource("pages/StockManagerView.fxml"));
            stockManagerLayout = stockManagerView.load();
            stockManagerController = stockManagerView.getController();
            stockManagerController.setWindowManager(this);
            stockManagerController.setProgramData(PROGRAM_DATA);
            stockManagerController.setStocks(new Stocks());

            //Stock Edit View Setup
            FXMLLoader stockEditView = new FXMLLoader(getClass().getResource("pages/StockEditView.fxml"));
            stockEditLayout = stockEditView.load();
            stockEditController = stockEditView.getController();
            stockEditController.setWindowManager(this);
            stockEditController.setProgramData(PROGRAM_DATA);

            //Stock Edit View Setup
            FXMLLoader itemBuyView = new FXMLLoader(getClass().getResource("pages/ItemBuyView.fxml"));
            itemBuyLayout = itemBuyView.load();
            itemBuyController = itemBuyView.getController();
            itemBuyController.setWindowManager(this);
            itemBuyController.setProgramData(PROGRAM_DATA);


            currentMntLayout = mntFeaturesLayout;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Scene createIphoneScene(Pane root) {
        // Layout IPhone 13 pro size relative to the resolution of the monitor
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double scaleFactor = Math.min(screenBounds.getWidth() / IPHONE13_PRO_WIDTH, screenBounds.getHeight() / IPHONE13_PRO_HEIGHT * 0.9);
        double screenWidth = IPHONE13_PRO_WIDTH * scaleFactor;
        double screenHeight = IPHONE13_PRO_HEIGHT * scaleFactor;
        return new Scene(root, screenWidth, screenHeight);
    }

    public void gotoHomeView() {
        window.setScene(homeScene);
    }


    public void gotoMntFeaturesView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(mntFeaturesLayout);
        currentMntLayout = mntFeaturesLayout;
        maintenanceService.setMaintenanceData(PROGRAM_DATA.getCurrentMaintenanceData());
        maintenanceService.updateView();
    }

    public void stock(ArrayList<ItemEnum<? extends Item>> itemEnums, ArrayList<StockEditInfo> stockEditInfos) {
        maintenanceService.stock(itemEnums, stockEditInfos);
    }

    public void collectMoney() {
        maintenanceService.collectMoney();
        maintenanceService.getSlidePopup().slideDownAnimation();
        maintenanceService.updateView();
    }

    public void replenishDenomination(Map<Integer, Integer> denomination) {
        maintenanceService.replenishDenomination(denomination);
        maintenanceService.getSlidePopup().slideDownAnimation();
    }

    public void gotoVndFeaturesView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(vndFeaturesLayout);
        vendingMachineController.setVendingMachine(PROGRAM_DATA.getCurrentVendingMachine());
        maintenanceService.setVendingMachine(PROGRAM_DATA.getCurrentVendingMachine());
        vendingMachineController.updateView();
    }

    public void gotoItemBuyView(int slotIndex) {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(itemBuyLayout);
        itemBuyController.updateView(slotIndex);
    }

    public void gotoStockView(int slotId, boolean isSpecialVendingMachine) {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(stockLayout);
        currentMntLayout = stockLayout;
        stockController.setSlotId(slotId);
        stockController.setView(isSpecialVendingMachine);
    }

    public void setStockManagerStock(ItemEnum<? extends Item> itemEnum, int index) {
        stockManagerController.setSlotItemEnum(itemEnum, index);
    }

    public void gotoRestockView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(restockLayout);
        currentMntLayout = restockLayout;
    }

    public void gotoChangeItemPriceView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(changeItemPriceLayout);
        currentMntLayout = changeItemPriceLayout;
    }

    public void gotoDisplayTransactionsView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(displayTransactionsLayout);
        currentMntLayout = displayTransactionsLayout;
    }

    public void gotoStockManagerView(boolean isResetStockEnum) {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(stockManagerLayout);
        currentMntLayout = stockManagerLayout;
        if(isResetStockEnum) {
            stockManagerController.resetStockEnums();
        }
        stockManagerController.updateView();
    }

    public void gotoStockEditView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(stockEditLayout);
        currentMntLayout = stockEditLayout;
        stockEditController.updateView();
    }

    public void gotoCurrentMntLayout() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(currentMntLayout);
        maintenanceService.setMaintenanceData(PROGRAM_DATA.getCurrentMaintenanceData());
        maintenanceService.updateView();
    }

    public VBox getCollectMoneyView(int money) {
        FXMLLoader collectMoneyView = new FXMLLoader(getClass().getResource("pages/CollectMoneyView.fxml"));
        VBox vBox;
        try {
            vBox = collectMoneyView.load();
            collectMoneyController = collectMoneyView.getController();
            collectMoneyController.updateView(money);
            collectMoneyController.setWindowManager(this);
            return vBox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getCollectDenomView() {
        FXMLLoader collectDenomView = new FXMLLoader(getClass().getResource("pages/CollectDenomView.fxml"));
        VBox vBox;
        try {
            vBox = collectDenomView.load();
            provideDenomController = collectDenomView.getController();
            provideDenomController.setWindowManager(this);
            return vBox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getPaymentView() {
        FXMLLoader collectDenomView = new FXMLLoader(getClass().getResource("pages/CollectDenomView.fxml"));
        VBox vBox;
        try {
            vBox = collectDenomView.load();
            return vBox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stage getWindow() {
        return window;
    }
}
