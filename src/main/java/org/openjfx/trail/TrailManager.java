package org.openjfx.trail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class TrailManager {

    // Fields
    public enum Rating {
        GREEN, BLUE, BLACK, DOUBLE_BLACK, PRO
    }

    private ArrayList<Trail> trailMap;

    @FXML
    public void drawSegment(ActionEvent event) {

    }
}
