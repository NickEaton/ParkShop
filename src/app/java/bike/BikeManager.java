package app.java.bike;

import app.java.entity.Rider;
import app.java.util.Pair;

import java.io.File;
import java.util.ArrayList;

public class BikeManager {

    // This array maps Rider objects to a list of Bikes they are cataloged as owning
    private ArrayList<Pair<Rider, ArrayList<BikeObj>>> riderCatalog;

    public BikeManager(File filein) {
        //TODO: Files... yea yea yea
    }
}
