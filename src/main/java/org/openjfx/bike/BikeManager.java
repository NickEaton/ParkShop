package org.openjfx.bike;

import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;
import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BikeManager implements Saveable {

    // This hashmap maps Rider objects to a list of Bikes they are cataloged as owning
    private ArrayList<Rider> riderCatalog;

    // While the user is selecting parts, the data will be stored here
    // Note, this is empty on load
    public Map<ComponentManager.Part, Component> activePartList;

    // This variable assists in assigning a unique ID to each bike created
    private static int curID;

    // Regex for file I/O
    private static final String regex = "#";

    // File Constructor
    public BikeManager(String fileID) throws IOException {

        // Initialize maps for this manager
        riderCatalog = new ArrayList<Rider>();
        activePartList = new HashMap<ComponentManager.Part, Component>();

        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src" + "\\main" + "\\resources" + "\\saves" + "\\" + fileID + ".properties");

        // Load riders, then do a recursive construction for their bikes
        try (InputStream input = new FileInputStream(p.toString())) {
            Properties prop = new Properties();
            prop.load(input);

            this.curID = Integer.parseInt(prop.getProperty("CID"));
            String[] RList = prop.getProperty("RIDERS").split(this.regex);
            Rider X;
            for(String Ri : RList) {
                X = new Rider(Ri);
                riderCatalog.add(X);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Primary constructor for first time launch
    public BikeManager() {
        riderCatalog = new ArrayList<Rider>();
        activePartList = new HashMap<ComponentManager.Part, Component>();
        curID = 1;
    }

    // This section deals with 'assembling' bikes

    // While the bike is being built up, can use this method to add parts
    public void setComponent(ComponentManager.Part partiD, Component comp) {
        if(!activePartList.containsKey(partiD)) {
            activePartList.put(partiD, comp);
        } else {
            activePartList.replace(partiD, comp);
        }
    }

    // Verify that the partslist is correct, i.e. there should be precisely one of each type of part
    // PRE: parts should never have a repeated component
    public void constructBike(Rider owner, Map<ComponentManager.Part, Component> parts) {

        // Construct a new bike with the tmp parts list loaded, with a Rider owner, and a new unique ID associated with the rider & total bikes
        BikeObj newBike = new BikeObj((""+owner.getRiderID()+(curID++)), owner, new LinkedList<Component>(parts.values()));
        owner.getOwnedBikes().add(newBike);
        if(!riderCatalog.contains(owner))
            riderCatalog.add(owner);

        //return newBike;
    }

    // Manually add a bike to a rider list
    public void addBikeToList(Rider owner, BikeObj bikeObj) {
        if(!riderCatalog.contains(owner))
            riderCatalog.add(owner);
        owner.getOwnedBikes().add(bikeObj);
    }

    // Top level call to save all Riders, Bikes and Components associated with this instance
    @Override
    public void saveToFile() throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src" + "\\main" + "\\resources" + "\\saves" + "\\" + "BikeManager.properties");

        // Save the Riders, which then recur to save their own BikeObj's
        StringBuffer tempRiderCatalog = new StringBuffer();
        try (OutputStream output = new FileOutputStream(p.toString())) {
            Properties prop = new Properties();

            prop.setProperty("CID", ""+curID);
            for(Rider r : this.riderCatalog) {
                tempRiderCatalog.append(r.getRiderID()+this.regex);
                r.saveToFile();
            }
            prop.setProperty("RIDERS", tempRiderCatalog.toString());
            prop.store(output, null);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Overrides Object toString method
    @Override
    public String toString() {
        String S = new String();
        for(Rider rider : this.riderCatalog) {
            S += rider.toString();
        }
        return S;
    }
}
