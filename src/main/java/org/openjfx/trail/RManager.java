package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;

public class RManager implements Saveable {

    private Trail myTrack;
    private ArrayList<Rider> riderArrayList;
    @FXML private Scene displayWindow;

    public RManager() {
        this.riderArrayList = new ArrayList<Rider>();
    }

    public ArrayList<Rider> getRiderArrayList() { return this.riderArrayList; }

    public void setTrack(Trail track) { this.myTrack = track; }

    // Pre: riderArrayList filled with all contestants
    public ArrayList<Rider> doSimpleRace() {
        return null;
    }

    @Override
    public void saveToFile() throws IOException {

    }
}
