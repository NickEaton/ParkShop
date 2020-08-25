package app.java.bike;

import app.java.components.Component;
import app.java.entity.Rider;
import app.java.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BikeManager {

    // This array maps Rider objects to a list of Bikes they are cataloged as owning
    private ArrayList<Pair<Rider, ArrayList<BikeObj>>> riderCatalog;

    public BikeManager(File filein) {
        //TODO: Files... yea yea yea
    }

    // This section deals with 'assembling' bikes

    // While the bike
    public void setComponent(BikeObj bike, Component comp) {

    }

    // Verify that the partslist is correct, i.e. there should be precisely one of each type of part
    public BikeObj constructBike(Rider owner, List<Component> parts) {
        return null;
    }
}
