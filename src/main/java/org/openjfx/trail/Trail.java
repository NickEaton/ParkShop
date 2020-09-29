package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.shape.Line;

import java.util.LinkedList;

public class Trail {

    @FXML private LinkedList<Line> trailPath;
    private LinkedList<TrailObj> featureList;
    private TrailManager.Rating rating;
    private String name;

    public Trail(String name, LinkedList<Line> path, LinkedList<TrailObj> features, TrailManager.Rating rating) {
        this.rating = rating;
        this.name = name;
        this.trailPath = path;
        this.featureList = features;
    }

}
