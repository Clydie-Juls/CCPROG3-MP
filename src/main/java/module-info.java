/**
 * Represents the module of the program
 */
module com.vnd.mco2restructure {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vnd.mco2restructure to javafx.fxml;
    exports com.vnd.mco2restructure;
    exports com.vnd.mco2restructure.controller;
    opens com.vnd.mco2restructure.controller to javafx.fxml;
    exports com.vnd.mco2restructure.component;
    opens com.vnd.mco2restructure.component to javafx.fxml;
    exports com.vnd.mco2restructure.model;
    exports com.vnd.mco2restructure.model.vendingmachine;
    exports com.vnd.mco2restructure.model.items;
    exports com.vnd.mco2restructure.model.slots;
    exports com.vnd.mco2restructure.menu;
    exports com.vnd.mco2restructure.callbacks;
    opens com.vnd.mco2restructure.model to javafx.fxml;
}