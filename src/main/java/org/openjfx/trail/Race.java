package org.openjfx.trail;

import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;

public class Race extends Thread implements Saveable {

    private Trail course;
    private ArrayList<Rider> racers;
    private ArrayList<Rider> finalPlacing;

    public Race(ArrayList<Rider> racers) {
        this.racers = racers;
        finalPlacing = new ArrayList<Rider>();
    }

    @Override
    public void run() {
        for(Rider r : this.racers) {
            // Compute scores, then do a display
        }
    }

    @Override
    public void saveToFile() throws IOException {

    }
}
