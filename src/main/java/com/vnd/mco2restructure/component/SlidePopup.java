package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
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

public class SlidePopup extends BorderPane {

    public SlidePopup() {
        setView();
        Platform.runLater(() -> {
            translateYProperty().set(getHeight());
            getParent().setVisible(false);
            setDisable(true);
        });
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/SlidePopup.fxml"));
        view.setRoot(this);

        try {
            view.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void slideUpAnimation() {
        ((BorderPane) getParent()).setBackground(new Background(new BackgroundFill(
                Color.rgb(0,0,0,0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        getParent().setVisible(true);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> setDisable(false));
        timeline.play();
    }

    public void slideDownAnimation() {
        ((BorderPane) getParent()).setBackground(new Background(new BackgroundFill(
                Color.rgb(0,0,0,0.0), CornerRadii.EMPTY, Insets.EMPTY)));

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
