package org.openjfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// I exist to be destroyed
public class BKConCompleteController {

    @FXML public ImageView bkImage;
    @FXML public Text bkName;
    @FXML public HBox starBox;

    // Pre: bkImage, bkName and starBox have been drawn appropriately
    @FXML public void fadeAway(Stage mine, Scene me) {
        Timeline t = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(3000), new KeyValue(me.getRoot().opacityProperty(), 0));
        t.getKeyFrames().add(frame);
        t.setOnFinished(event1 -> mine.close());
        t.play();
    }
}
