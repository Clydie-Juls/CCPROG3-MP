package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Represents a custom BorderPane component that provides a sliding popup effect.
 */
public class SlidePopup extends BorderPane {

    /**
     * Constructs a new SlidePopup component and sets its view.
     * The component starts as hidden and disabled.
     */
    public SlidePopup() {
        setView();
        Platform.runLater(() -> {
            translateYProperty().set(getHeight());
            getParent().setVisible(false);
            setDisable(true);
        });
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/SlidePopup.fxml"));
        view.setRoot(this);

        try {
            view.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Slides the popup up with a fade-in effect.
     * The component becomes visible and enabled.
     */
    public void slideUpAnimation() {
        ((BorderPane) getParent()).setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        getParent().setVisible(true);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> setDisable(false));
        timeline.play();
    }

    /**
     * Slides the popup down with a fade-out effect.
     * The component becomes hidden and disabled.
     */
    public void slideDownAnimation() {
        ((BorderPane) getParent()).setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.0), CornerRadii.EMPTY, Insets.EMPTY)));

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(translateYProperty(), getHeight(), Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            setDisable(true);
            getParent().setVisible(false);
        });
        timeline.play();
    }
}
