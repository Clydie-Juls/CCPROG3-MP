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
 * This controller class handles the animation when the user bought an item
 */
public class BurgerLoadAnimationController {

    @FXML private Button exitButton;
    private WindowManager windowManager;
    @FXML private VBox body;

    /**
     * Starts the animation by showing a series of texts depending on the situation
     * @param stepIndex - current index of the string sequence
     * @param steps - string sequence of text to print
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
     * This method sets the window manager to transfer scenes.
     * @param windowManager - mediator of controllers and scene handler
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * exits the scene and go to the vnd features view
     */
    @FXML
    private void exit() {
        windowManager.gotoVndFeaturesView();
    }

    /**
     * This method returns the body of the view
     * @return body of the view
     */
    public VBox getBody() {
        return body;
    }
}
