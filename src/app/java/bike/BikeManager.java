package app.java.bike;

import app.java.components.Component;
import app.java.components.ComponentManager;
import app.java.entity.Rider;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BikeManager {

    // This hashmap maps Rider objects to a list of Bikes they are cataloged as owning
    private HashMap<Rider, ArrayList<BikeObj>> riderCatalog;

    // While the user is selecting parts, the data will be stored here
    private Map<ComponentManager.Part, Component> activePartList;

    // Constructors
    public BikeManager(File filein) {
        //TODO: Files... yea yea yea
    }

    public BikeManager() {
        riderCatalog = new HashMap<Rider, ArrayList<BikeObj>>();
    }

    // This section deals with 'assembling' bikes

    // While the bike is being built up, use this method to add parts
    public void setComponent(ComponentManager.Part partiD, Component comp) {
        if(!activePartList.containsKey(partiD)) {
            activePartList.put(partiD, comp);
        } else {
            activePartList.replace(partiD, comp);
        }
    }

    // Verify that the partslist is correct, i.e. there should be precisely one of each type of part
    // PRE: parts should never have a repeated component
    public BikeObj constructBike(Rider owner, Map<ComponentManager.Part, Component> parts) {
        BikeObj newBike = new BikeObj(owner, new LinkedList<Component>(parts.values()));
        if(!riderCatalog.containsKey(owner))
            riderCatalog.put(owner, new ArrayList<BikeObj>());

        riderCatalog.get(owner).add(newBike);

        return newBike;
    }
}
