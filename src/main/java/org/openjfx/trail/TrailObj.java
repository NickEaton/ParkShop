package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.shape.Line;
import java.util.LinkedList;

public class TrailObj implements Feature {

    private TrailManager.FeatureType myType;
    private TrailManager.Rating ftLevel;
    private int intensity;
    private int length;

    public TrailObj(TrailManager.FeatureType type, TrailManager.Rating difficulty, int intensity, int length) {
        this.myType = type;
        this.ftLevel = difficulty;
        this.intensity = intensity;
        this.length = length;
    }

    @Override
    public int getIntensity() {
        return 0;
    }
}
