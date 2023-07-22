package com.vnd.mco2restructure;

import com.vnd.mco2restructure.controller.HomeController;
import com.vnd.mco2restructure.controller.MainMenuController;
import com.vnd.mco2restructure.controller.MaintenanceService;
import com.vnd.mco2restructure.controller.StockController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {
    private final int IPHONE13_PRO_WIDTH = 1170;
    private final int IPHONE13_PRO_HEIGHT = 2532;
    private final Stage window;
    private final ProgramData PROGRAM_DATA;
    private HomeController homeController;
    private MainMenuController mainMenuController;
    private MaintenanceService maintenanceService;

    private StockController stockController;
    private Scene homeScene;
    private Scene mainMenuScene;
    private ScrollPane vndFeaturesLayout;
    private StackPane mntFeaturesLayout;

    private BorderPane stockLayout;

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

    public void gotoMainMenuView() {
        window.setScene(mainMenuScene);
    }

    public void gotoMntFeaturesView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(mntFeaturesLayout);
    }

    public void gotoVndFeaturesView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(vndFeaturesLayout);
    }

    public void gotoStockView() {
        if (window.getScene() != mainMenuScene) {
            window.setScene(mainMenuScene);
        }

        mainMenuController.getMainContent().setCenter(stockLayout);
    }

    public VBox getCollectMoneyView() {
        FXMLLoader collectMoneyView = new FXMLLoader(getClass().getResource("pages/CollectMoneyView.fxml"));
        try {
            return collectMoneyView.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getCollectDenomView() {
        FXMLLoader collectMoneyView = new FXMLLoader(getClass().getResource("pages/CollectDenomView.fxml"));
        try {
            return collectMoneyView.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
