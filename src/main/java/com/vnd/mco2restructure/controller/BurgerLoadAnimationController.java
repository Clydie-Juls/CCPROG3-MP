package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The BurgerLoadAnimationController class handles the burger loading animation in the vending machine simulator application.
 * It displays a step-by-step animation of the burger preparation process and allows the user to exit the animation.
 */
public class BurgerLoadAnimationController {

    @FXML private Button exitButton;
    private WindowManager windowManager;
    @FXML private VBox body;

    /**
     * Starts the burger loading animation with the provided steps array.
     * Each step in the array is displayed sequentially as part of the animation.
     *
     * @param stepIndex The index of the current step in the steps array.
     * @param steps     The array containing the steps of the animation.
     */
    public void startAnimation(int stepIndex, String[] steps) {
        if (stepIndex < steps.length) {
            exitButton.setDisable(true);
            exitButton.setVisible(false);
            String currentStep = steps[stepIndex];
            Label stepLabel = new Label(currentStep);

            body.getChildren().add(stepLabel);

            Duration stepDuration = Duration.seconds(1);

            KeyFrame keyFrame = new KeyFrame(stepDuration, new KeyValue(stepLabel.textProperty(), stepLabel.getText()));
            Timeline timeline = new Timeline(keyFrame);
            timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("HELLO");
                if(newValue == Animation.Status.STOPPED) {
                    startAnimation(stepIndex + 1, steps);
                }
            });

            timeline.play();
        } else {
            exitButton.setDisable(false);
            exitButton.setVisible(true);
        }
    }

    /**
     * Sets the WindowManager instance to handle navigation between views.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Handles the action when the exit button is clicked.
     * Navigates to the Vending Features view when the exit button is clicked.
     */
    @FXML
    private void exit() {
        windowManager.gotoVndFeaturesView();
    }
}
