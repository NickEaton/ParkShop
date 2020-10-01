package org.openjfx.components;

import org.openjfx.ParkShopApp;
import org.openjfx.util.Saveable;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

// This class will store, organize and manage the total list of components stored
public class ComponentManager implements Saveable {

    // Scheduler
    // private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // There are 19 distinct parts required to compose a BikeObj
    // Frame, Wheel-F, Wheel-R, Tire-F, Tire-R, Fork, Shock, Brakes, Rotor-F, Rotor-R, Shifters, Cranks, Pedals, Chainring, Chain, Cassette, Derailer, Handlebar, Grips

    public static final String regex = "#";

    // Materials out of which components may be made of
    public enum Material {
        STEEL_I, STEEL_II,
        ALUMINUM,
        ALLOY_I, ALLOY_II,
        CARBON_I, CARBON_II, CARBON_III
    }

    // Which component in particular this is
    public enum Part {
        BRAKE_LEVER, //BRAKE_LEVER_R,
        WHEEL, //WHEEL_R,
        TIRE, //TIRE_R,
        ROTOR, //ROTOR_R,
        BRAKE, //BRAKE_R,
        FRAME,
        FORK,
        SHOCK,
        SEAT,
        SEATPOST,
        CHAINRING,
        CHAIN,
        CASSETTE,
        DERAILLEUR,
        CRANKS,
        PEDALS,
        HANDLEBAR,
        SHIFTER,
        GRIPS
    }

    // List of manufacturers, in no particular order
    public enum Company {
        Falcon_Dynamics,
        X_Ram,
        Arcon,
        Collossal,
        Reverb_Cycles,
        Cruzin_Cali,
        K2_Custom,
        Omni,
        Valeriant
    }

    public enum Model {
        Basic,
        Comp,
        Intermediate,
        Woodland,
        Comp_Plus,
        Advanced,
        Expert,
        Mountaineer,
        Pro,
        Master
    }

    private boolean isDouble(Part _p) {
        return (_p == Part.WHEEL || _p == Part.BRAKE ||
                _p == Part.TIRE || _p == Part.ROTOR);
    }

    // Fields
    private HashMap<Part, LinkedList<Component>> componentList;    // Player Component Inventory
    private HashMap<Part, LinkedList<Component>> shopList;         // Shop Component Inventory
    private static LinkedList<Component> tempLoadList;
    private static int curID;

    // File Constructor
    public ComponentManager(String fileID) throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                                 "\\src\\main\\resources\\org\\saves\\" + fileID + ".properties");

        try (InputStream input = new FileInputStream(p.toString())) {
            Properties managerProperty = new Properties();
            managerProperty.load(input);
            this.curID = Integer.parseInt(managerProperty.getProperty("CID"));
            String[] compToLoad = managerProperty.getProperty("CLIST").split(this.regex);

            // Use the file constructor for Component for each one itemized in CLIST
            // Note: Extra functionality will need to be added for  distinguishing BikeObj files and their owned components
            tempLoadList = new LinkedList<Component>();
            for (String componentID : compToLoad) {
                if (!componentID.isEmpty())
                    tempLoadList.push(new Component(componentID));
            }
            for (Component comp : tempLoadList) {
                addCatalogComponent(comp);
            }
            tempLoadList.clear();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Default Constructor on first time load
    public ComponentManager() {
        componentList = new HashMap<Part, LinkedList<Component>>();
        shopList = new HashMap<Part, LinkedList<Component>>();
        curID = 1;

        for(Part p : Part.values()) {
            componentList.put(p, new LinkedList<Component>());
            shopList.put(p, new LinkedList<Component>());
        }
    }

    // public methods

    // Remove a specific component from the list using its unique componentId
    public Component extractComponentFromId(Part type, String componentId) {

        // Scan and search for component by ID, return it if found
        LinkedList<Component> subList = componentList.get(type);

        Component C = null;
        for (int i = 0; i < subList.size(); i++) {
            C = subList.get(i);
            if (C.getCompID().equals(componentId)) {
                componentList.get(type).remove(C);
                return C;
            }
        }

        // Otherwise return null if part not found in list
        return null;
    }

    // Add a new component to the catalog
    // If the list for a given part does not exist yet, create it first
    // TODO: returning cmp for testing purposes
    public Component addCatalogComponent(Component cmp) {
        if (componentList.get(cmp.getPart()) == null)
            componentList.put(cmp.getPart(), new LinkedList<Component>());
        componentList.get(cmp.getPart()).add(cmp);
        return cmp;
    }

    // Add a new part to the shop
    public void addShopComponent(Component cmp) {
        if(shopList.get(cmp.getPart()) == null)
            shopList.put(cmp.getPart(), new LinkedList<Component>());
        shopList.get(cmp.getPart()).add(cmp);
        ParkShopApp.primaryLog.log(Level.INFO, "Added: "+cmp.toString());
    }

    // Better Component Generation Method
    public Component genNewComponent(String iD, int level) {
        Random rand = new Random();
        Component myComp = new Component(20*(level-1)+20*Math.random(), 20*(level-1)+20*Math.random(), 20*(level-1)+20*Math.random(), 20*(level-1)+20*Math.random(),
                                        iD, Company.values()[rand.nextInt(9)], level, iD, 20*(level-1)+20*Math.random(), 20*(level-1)+20*Math.random(), 20*(level-1)+20*Math.random(),
                                        Material.values()[rand.nextInt(8)], Part.values()[rand.nextInt(19)]);
        return myComp;
    }

    // Generate a new completely random component for testing
    public Component getNewRandComponent(String iD) {
        Random rand = new Random();
        return new Component(100 * Math.random(), 100 * Math.random(), 100 * Math.random(), 100 * Math.random(),
                             iD, Company.values()[rand.nextInt(9)], rand.nextInt(5)+1, iD, 100 * Math.random(), 100 * Math.random(), 100 * Math.random(),
                             Material.values()[rand.nextInt(8)], Part.values()[rand.nextInt(19)]);
    }

    // Generate a new random component of a particular Part type for testing
    public Component getNewRandComponent(String iD, ComponentManager.Part _part) {
        Random rand = new Random();
        return new Component(100 * Math.random(), 100 * Math.random(), 100 * Math.random(), 100 * Math.random(),
                iD, Company.values()[rand.nextInt(9)], rand.nextInt(5)+1, iD, 100 * Math.random(), 100 * Math.random(), 100 * Math.random(), Material.values()[rand.nextInt(8)], _part);
    }

    // Return all shop components in an ArrayList
    /*
    public LinkedList<Component> getShopList() {
        LinkedList<Component> fullList = new LinkedList<Component>();
        for(Part _part : this.shopList.keySet()) {
            if(shopList.get(_part) != null) {
                fullList.addAll(this.shopList.get(_part));
                System.out.println(shopList.get(_part)+" TEST\n");
            }
        }
        return fullList;
    }
    */

    // Generate a complete set of components, assign all in bkManager and enque to player inventory for quick testing
    public void genFullSet() {
        for(Part p : Part.values()) {
            Component c1 = getNewRandComponent(p.toString(), p);
            ParkShopApp.bkManager.addSwapComponent(c1, 0);
            componentList.get(p).add(c1);
            if(isDouble(p)) {
                Component c2 = getNewRandComponent(p.toString(), p);
                ParkShopApp.bkManager.addSwapComponent(c2, 1);
                componentList.get(p).add(c2);
            }
        }
    }

    // Generate components to build competitors for races
    public LinkedList<Component> genCompFullSet() {
        LinkedList<Component> temp = new LinkedList<Component>();
        for(Part p : Part.values()) {
            Component c1 = getNewRandComponent(p.toString(), p);
            temp.add(c1);
            if(isDouble(p)) {
                Component c2 = getNewRandComponent(p.toString(), p);
                temp.add(c2);
            }
        }
        return temp;
    }

    public HashMap<Part, LinkedList<Component>> getShopList() {
        return this.shopList;
    }

    public HashMap<Part, LinkedList<Component>> getPlayerList() {
        return this.componentList;
    }

    // Clear arbitrary list of components from the players inventory
    // Pre: Player owns all thrown components
    public void voidPlayerComponents(List<Component> cx) {
        for(Component c : cx) {
            if(!this.componentList.get(c.getPart()).contains(c)) {
                ParkShopApp.primaryLog.log(Level.SEVERE, "Error: bad component passed to void");
                return;
            }
            this.componentList.get(c.getPart()).remove(c);
        }
    }

    // Save all components to a file, then save an additional file listing all cataloged components, which will be separated via regex character
    @Override
    public void saveToFile() throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                                "\\src\\main\\resources\\org\\saves\\ComponentManager.properties");
        StringBuffer constructPList = new StringBuffer();
        try (OutputStream outfile = new FileOutputStream(p.toString())) {
            Properties manageProb = new Properties();

            LinkedList<Component> curBin;
            for (Part part : Part.values()) {
                curBin = componentList.get(part);
                if (curBin != null) {
                    for (Component comp : curBin) {
                        comp.saveToFile();
                        constructPList.append(comp.getCompID() + "#");
                    }
                }
            }

            manageProb.setProperty("CLIST", constructPList.toString());
            manageProb.setProperty("CID", "" + this.curID);
            manageProb.store(outfile, null);

        } catch (IOException exception) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error Saving ComponentManager");
            exception.printStackTrace();
        }
    }
}