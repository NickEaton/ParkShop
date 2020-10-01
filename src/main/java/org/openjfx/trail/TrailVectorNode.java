package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.openjfx.util.Saveable;

import java.io.IOException;

public class TrailVectorNode extends Circle implements Saveable {
    public TrailVectorNode next;
    private Feature feature;
    @FXML private Line link;
    private double baseTimeSeconds;

    public TrailVectorNode(double xpos, double ypos, int rad) {
        super(xpos, ypos, rad);
        this.setOnMouseClicked(event -> {
           System.out.println("Clicked on node");
        });
        next = null;
    }

    public String getFeatureInfo() {
        return null; //TODO
    }

    public double getBaseTimeSeconds() { return this.baseTimeSeconds; }
    public Feature getFeature() { return this.feature; }
    public Line getLink() { return this.link; }
    public void setFeature(Feature feature) { this.feature = feature; }
    public void setLink(Line link) { this.link = link; }

    @Override
    public void saveToFile() throws IOException {

    }
}
