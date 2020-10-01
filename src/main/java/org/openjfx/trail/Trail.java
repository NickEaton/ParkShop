package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.LinkedList;

public class Trail {

    @FXML private LinkedList<TrailVectorNode> trailPath;
    private TrailManager.Rating rating;
    private ImageView rImage;
    private String name;
    private int length;
    private int segCount;

    // Randomness factor for the track
    private double variance;

    // These 3 var should always add to 100
    private double XC_lvl;
    private double END_lvl;
    private double DH_lvl;

    private int getLength(int startx, int starty, int endx, int endy) {
        return (int)Math.sqrt(Math.pow(Math.abs(startx-endx), 2) + Math.pow(Math.abs(starty-endy), 2));
    }

    // TODO
    public Trail() {
        trailPath = new LinkedList<TrailVectorNode>();
        rImage = new ImageView();
    }

    public String getLength() {
        length = 0;
        for(TrailVectorNode tvn : trailPath) {
            if(tvn.getLink() != null) {
                length += getLength((int)tvn.getLink().getStartX(), (int)tvn.getLink().getStartY(),
                                    (int)tvn.getLink().getEndX(), (int)tvn.getLink().getEndY());
            }
        }
        return ""+length;
    }

    public double getVariance() { return this.variance; }
    public double getXC_lvl() { return this.XC_lvl; }
    public double getEND_lvl() { return this.END_lvl; }
    public double getDH_lvl() { return this.DH_lvl; }
    public int getTotalExecutableSegments() { return this.segCount; }
    public TrailManager.Rating getRating() { return this.rating; }
    public void setRating(TrailManager.Rating r) { this.rating = r; }
    public void setImage(ImageView target) { this.rImage.setImage(target.getImage()); }
    public ImageView getImage() { return this.rImage; }
    public String getName() { return this.name; }

    public void computeRating() {

    }

    public void setName(String name) { this.name = name; }

    public LinkedList<TrailVectorNode> getTrailPath() { return this.trailPath; }
}
