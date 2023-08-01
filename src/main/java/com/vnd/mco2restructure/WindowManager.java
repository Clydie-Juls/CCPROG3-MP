package com.vnd.mco2restructure;

import com.vnd.mco2restructure.controller.*;
import com.vnd.mco2restructure.menu.ItemEnum;
import com.vnd.mco2restructure.model.StockData;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.Stocks;
import com.vnd.mco2restructure.model.items.CustomizableItem;
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

/**
 * The WindowManager class manages the different views and scenes in the vending machine simulator application.
 * It handles navigation between different screens and provides methods for displaying various views.
 */
public class WindowManager {
    private final int IPHONE13_PRO_WIDTH = 1170;
    private final int IPHONE13_PRO_HEIGHT = 2532;
    private final Stage window;
    private final ProgramData PROGRAM_DATA;

    // Controller instances for different views
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
    private ProvideDenomController paymentController;
    private ItemBuyController itemBuyController;
    private BurgerLoadAnimationController burgerLoadAnimationController;

    // Scenes for different views
    private Scene homeScene;
    private Scene mainMenuScene;
    private Scene animationScene;

    // Layouts for different views
    private StackPane vndFeaturesLayout;
    private StackPane mntFeaturesLayout;
    private BorderPane stockLayout;
    private BorderPane changeItemPriceLayout;
    private StackPane displayTransactionsLayout;
    private BorderPane stockManagerLayout;
    private BorderPane stockEditLayout;
    private StackPane itemBuyLayout;
    private Pane currentMntLayout;

    /**
     * Creates a new WindowManager with the main application stage.
     * Initializes the ProgramData instance and sets up the views and controllers.
     *
     * @param window The main stage of the application.
     */
    public WindowManager(Stage window) {
        this.window = window;
        PROGRAM_DATA = new ProgramData();
        setup();
    }

    /**
     * Sets up the different views and controllers of the application.
     * Loads FXML files for each view and initializes the corresponding controller instances.
     */
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
            BorderPane restockLayout = restockView.load();
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

            //Stock Edit View Setup
            FXMLLoader burgerLoadAnimView = new FXMLLoader(getClass().getResource("pages/BurgerLoadAnimationView.fxml"));
            BorderPane burgerLoadingAnimationLayout = burgerLoadAnimView.load();
            burgerLoadAnimationController = burgerLoadAnimView.getController();
            burgerLoadAnimationController.setWindowManager(this);
            animationScene = createIphoneScene(burgerLoadingAnimationLayout);

            currentMntLayout = mntFeaturesLayout;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates an iPhone-like scene with the provided root pane and scales it according to the screen resolution.
     *
     * @param root The root pane of the scene.
     * @return The scaled Scene object.
     */
    private Scene createIphoneScene(Pane root) {
        // Layout IPhone 13 pro size relative to the resolution of the monitor
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double scaleFactor = Math.min(screenBounds.getWidth() / IPHONE13_PRO_WIDTH, screenBounds.getHeight() / IPHONE13_PRO_HEIGHT * 0.9);
        double screenWidth = IPHONE13_PRO_WIDTH * scaleFactor;
        double screenHeight = IPHONE13_PRO_HEIGHT * scaleFactor;
        return new Scene(root, screenWidth, screenHeight);
    }

    /**
     * Navigates to the Home view.
     * Sets the main stage's scene to the Home scene.
     */
    public void gotoHomeView() {
        window.setScene(homeScene);
    }

    /**
     * Navigates to the Maintenance Features view.
     * Sets the main stage's scene to the Maintenance Features scene.
     * Updates the MaintenanceService view with the current maintenance data.
     */
    public void gotoMntFeaturesView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(mntFeaturesLayout);
        currentMntLayout = mntFeaturesLayout;
        maintenanceService.setMaintenanceData(PROGRAM_DATA.getCurrentMaintenanceData());
        maintenanceService.updateView();
        System.out.println("HEEEELLLOOOOOO");
        System.out.println("HIIIII");
    }

    /**
     * Stocks items in the vending machine with the provided item enums and stock edit information.
     *
     * @param itemEnums      The list of item enums to be stocked.
     * @param stockEditInfos The list of stock edit information for each item.
     */
    public void stock(ArrayList<ItemEnum<? extends Item>> itemEnums, ArrayList<StockEditInfo> stockEditInfos) {
        maintenanceService.stock(itemEnums, stockEditInfos);
    }

    /**
     * Collects money from the vending machine.
     * Initiates the collection animation and updates the maintenance service view.
     */
    public void collectMoney() {
        maintenanceService.collectMoney();
        maintenanceService.getSlidePopup().slideDownAnimation();
        maintenanceService.updateView();
    }

    /**
     * Navigates to the Vending Features view.
     * Sets the main stage's scene to the Vending Features scene.
     * Updates the VendingMachineController and MaintenanceService view with the current vending machine data.
     */
    public void gotoVndFeaturesView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(vndFeaturesLayout);
        vendingMachineController.setVendingMachine(PROGRAM_DATA.getCurrentVendingMachine());
        maintenanceService.setVendingMachine(PROGRAM_DATA.getCurrentVendingMachine());
        vendingMachineController.updateView();
        System.out.println("EEGEGGEGE");
        displayTransactionsController.setTransactions(PROGRAM_DATA.getCurrentVendingMachine().getTransactions());
    }

    /**
     * Navigates to the animation scene and starts the specified animation steps.
     *
     * @param steps The steps of the animation to be displayed.
     */
    public void gotoAnimationScene(String[] steps) {
        window.setScene(animationScene);
        burgerLoadAnimationController.getBody().getChildren().clear();
        burgerLoadAnimationController.startAnimation(0, steps);
    }

    /**
     * Navigates to the Item Buy view for the specified slot index.
     * Sets the main stage's scene to the Item Buy scene and updates the ItemBuyController view.
     *
     * @param slotIndex The index of the slot for the item to be bought.
     */
    public void gotoItemBuyView(int slotIndex) {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(itemBuyLayout);
        itemBuyController.updateView(slotIndex);
    }


    /**
     * Navigates to the Stock view for the specified slot ID and vending machine type.
     * Sets the main stage's scene to the Stock scene and updates the StockController view.
     *
     * @param slotId                  The ID of the slot to be displayed.
     * @param isSpecialVendingMachine Flag indicating whether the vending machine is a special type.
     */
  public void gotoStockView(int slotId) {

        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(stockLayout);
        currentMntLayout = stockLayout;
        stockController.setSlotId(slotId);
    }

    public void setStockView(boolean isSpecialVendingMachine) {
        stockController.setView(isSpecialVendingMachine);
        changeItemPriceController.updateView(isSpecialVendingMachine);
    }

    /**
     * Sets the stock manager's stock for the specified item enum at the given index.
     *
     * @param itemEnum The item enum to set for the stock manager.
     * @param index    The index of the slot to set the item enum.
     */
    public void setStockManagerStock(ItemEnum<? extends Item> itemEnum, int index) {
        stockManagerController.setSlotItemEnum(itemEnum, index);
    }

    /**
     * Navigates to the Change Item Price view.
     * Sets the main stage's scene to the Change Item Price scene.
     */
    public void gotoChangeItemPriceView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(changeItemPriceLayout);
        currentMntLayout = changeItemPriceLayout;
    }

    /**
     * Navigates to the Display Transactions view.
     * Sets the main stage's scene to the Display Transactions scene.
     */
    public void gotoDisplayTransactionsView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(displayTransactionsLayout);
        currentMntLayout = displayTransactionsLayout;
        displayTransactionsController.updateView();
    }

    /**
     * Navigates to the Stock Manager view.
     * Sets the main stage's scene to the Stock Manager scene.
     * Updates the StockManagerController view and optionally resets the stock enums.
     *
     * @param isResetStockEnum Flag indicating whether to reset the stock enums.
     */
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

    /**
     * Navigates to the Stock Edit view.
     * Sets the main stage's scene to the Stock Edit scene.
     * Updates the StockEditController view.
     */
    public void gotoStockEditView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(stockEditLayout);
        currentMntLayout = stockEditLayout;
        stockEditController.updateView();
    }

    /**
     * Navigates to the currently active maintenance layout.
     * Sets the main stage's scene to the current maintenance layout.
     * Updates the MaintenanceService view with the current maintenance data.
     */
    public void gotoCurrentMntLayout() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(currentMntLayout);
        maintenanceService.setMaintenanceData(PROGRAM_DATA.getCurrentMaintenanceData());
        maintenanceService.updateView();
    }

    /**
     * Creates and returns a VBox containing the Collect Money view for the specified amount of money.
     *
     * @param money The amount of money to be collected.
     * @return The VBox containing the Collect Money view.
     */
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

    /**
     * Creates and returns a VBox containing the Collect Denomination view for replenishing denominations.
     *
     * @return The VBox containing the Collect Denomination view.
     */
    public VBox getCollectDenomView() {
        FXMLLoader collectDenomView = new FXMLLoader(getClass().getResource("pages/CollectDenomView.fxml"));
        VBox vBox;
        try {
            vBox = collectDenomView.load();
            provideDenomController = collectDenomView.getController();
            provideDenomController.updateView(1);
            provideDenomController.setDenomCallback(denom -> {
                maintenanceService.replenishDenomination(denom);
                maintenanceService.getSlidePopup().slideDownAnimation();
            });
            return vBox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates and returns a VBox containing the Payment view for the specified slot index and item price.
     *
     * @param slotIndex The index of the slot for the item to be bought.
     * @param itemPrice The price of the item to be bought.
     * @return The VBox containing the Payment view.
     */
    public VBox getPaymentView(int slotIndex, int itemPrice) {
        FXMLLoader collectDenomView = new FXMLLoader(getClass().getResource("pages/CollectDenomView.fxml"));
        VBox vBox;
        try {
            vBox = collectDenomView.load();
            paymentController = collectDenomView.getController();
            paymentController.updateView(itemPrice);
            paymentController.setDenomCallback(denom -> {
                Item item = vendingMachineController.buy(denom, slotIndex);
               if(item != null) {
                   if(item instanceof CustomizableItem) {
                       String[] burgerSteps = {
                               "Preparing Patty",
                               "Bringing in the buns",
                               "Adding Sauce/Condiments",
                               "Assembling Burger",
                               item.getName() + " is Ready!",
                               denom.toString()
                       };
                       gotoAnimationScene(burgerSteps);
                   } else {
                       String[] burgerSteps = {
                               "Preparing " + item.getName(),
                               item.getName() + " is Ready!",
                               denom.toString()
                       };
                       gotoAnimationScene(burgerSteps);
                   }
               } else {
                   String[] burgerSteps = {
                           "Vending Machine doesn't have enough denomination",
                           denom.toString()
                   };
                   gotoAnimationScene(burgerSteps);
               }

                itemBuyController.getSlidePopup().slideDownAnimation();
                vendingMachineController.updateView();
            });
            return vBox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void resetCurrentFeaturesView() {
        currentMntLayout = mntFeaturesLayout;
    }

    /**
     * Returns the main application stage.
     *
     * @return The main application stage.
     */

    public Stage getWindow() {
        return window;
    }
}
