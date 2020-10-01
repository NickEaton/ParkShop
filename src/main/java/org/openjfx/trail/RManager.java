package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class RManager implements Saveable {

    private LinkedList<Trail> myTracks;
    private ArrayList<Rider> riderArrayList;
    @FXML private Scene displayWindow;

    public RManager() {
        this.riderArrayList = new ArrayList<Rider>();
        this.myTracks = new LinkedList<Trail>();
    }

    public ArrayList<Rider> getRiderArrayList() { return this.riderArrayList; }

    public void addTrack(Trail track) { this.myTracks.add(track); }

    // Pre: myTrack has been set
    public LinkedList<Trail> getTrack() { return this.myTracks; }

    public void addCompetitor(Rider r) {
        riderArrayList.add(r);
    }

    // Pre: riderArrayList filled with all contestants
    public ArrayList<Rider> doSimpleRace() {
        ArrayList<Rider> results = new ArrayList<Rider>();
        boolean lastFlag = true;

        for(Rider r : this.riderArrayList) {
            double score = r.getTrackScore(myTracks.get(0));
            if(results.size() > 0) {
                for(int k=0; k<results.size(); k++) {
                    if(score > results.get(k).getLatestScore()) {
                        results.add(k, r);
                        lastFlag = false;
                        break;
                    }
                }
                if(lastFlag) {
                    results.add(r);
                }
            } else {
                results.add(r);
            }
        }

        return results;
    }

    @Override
    public void saveToFile() throws IOException {

    }
}
