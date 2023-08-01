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

public class BurgerLoadAnimationController {

    @FXML private Button exitButton;
    private WindowManager windowManager;
    @FXML private VBox body;


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

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    @FXML
    private void exit() {
        windowManager.gotoVndFeaturesView();
    }

    public VBox getBody() {
        return body;
    }
}
