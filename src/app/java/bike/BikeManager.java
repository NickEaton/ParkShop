package app.java.bike;

import app.java.components.Component;
import app.java.components.ComponentManager;
import app.java.entity.Rider;
import app.java.util.Saveable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BikeManager implements Saveable {

    // This hashmap maps Rider objects to a list of Bikes they are cataloged as owning
    private HashMap<Rider, ArrayList<BikeObj>> riderCatalog;

    // While the user is selecting parts, the data will be stored here
    // Note, this is empty on load
    private Map<ComponentManager.Part, Component> activePartList;

    // This variable assists in assigning a unique ID to each bike created
    private static int curID;

    // Constructors
    public BikeManager(String fileID) throws IOException {

        // Initialize maps for this manager
        riderCatalog = new HashMap<Rider, ArrayList<BikeObj>>();
        activePartList = new HashMap<ComponentManager.Part, Component>();

        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +"src" + File.pathSeparator + "app" + File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + fileID + ".properties");

        try (InputStream input = new FileInputStream(p.toString())) {

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public BikeManager() {
        riderCatalog = new HashMap<Rider, ArrayList<BikeObj>>();
        activePartList = new HashMap<ComponentManager.Part, Component>();
        curID = 1;
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

        // Construct a new bike with the tmp parts list loaded, with a Rider owner, and a new unique ID associated with the rider & total bikes
        BikeObj newBike = new BikeObj((""+owner.getRiderID()+(curID++)), owner, new LinkedList<Component>(parts.values()));
        if(!riderCatalog.containsKey(owner))
            riderCatalog.put(owner, new ArrayList<BikeObj>());

        riderCatalog.get(owner).add(newBike);

        return newBike;
    }

    @Override
    public void saveToFile() throws IOException {

    }
}
