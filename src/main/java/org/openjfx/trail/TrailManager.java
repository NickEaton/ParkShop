package org.openjfx.trail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class TrailManager {

    // Fields
    public enum Rating {
        WHITE,
        GREEN,
        BLUE,
        BLACK,
        DOUBLE_BLACK,
        PRO,
        TRIPLE_BLACK,
        INSANITY
    }

    public enum FeatureType {
        JUMP_RACE,
        JUMP_LONG,
        JUMP_HIGH,
        JUMP_TRICK,
        ROCK_GARDEN,
        DROP,
        STEEP
    }

    private ArrayList<Trail> trailMap;

    public void addTrail(Trail trail) {
        trailMap.add(trail);
    }

    public TrailManager() {
        trailMap = new ArrayList<Trail>();
    }
}
