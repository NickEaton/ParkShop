package app.java.components;

import app.java.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

// This class will store, organize and manage the total list of components stored
public class ComponentManager implements Saveable {

    // There are 19 distinct parts required to compose a BikeObj
    // Frame, Wheel-F, Wheel-R, Tire-F, Tire-R, Fork, Shock, Brakes, Rotor-F, Rotor-R, Shifters, Cranks, Pedals, Chainring, Chain, Cassette, Derailer, Handlebar, Grips

    public static final String regex = "#";

    // Materials out of which components may be made of
    public enum Material {
        STEEL_I, STEEL_II, ALUMINUM, ALLOY_I, ALLOY_II, CARBON_I, CARBON_II, CARBON_III
    }

    // Which component in particular this is
    public enum Part {
        WHEEL_F, WHEEL_R, TIRE_F, TIRE_R, ROTOR_F, ROTOR_R, BRAKE_F, BRAKE_R, FRAME, FORK, SHOCK, SEAT, SEAT_POST, CHAIN_RING, CHAIN, CASSETTE, DERAILLEUR, CRANKS, PEDALS, HANDLEBAR, BRAKE_LEVER_F, BRAKE_LEVER_R, SHIFTER, GRIPS
    }

    // Fields
    private HashMap<Part, LinkedList<Component>> componentList;
    private HashMap<Part, LinkedList<Component>> shopList;         // Will be implemented later
    private static LinkedList<Component> tempLoadList;
    private static int curID;

    // Constructors
    public ComponentManager(String fileID) throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +"src" + File.pathSeparator + "app" + File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + fileID + ".properties");

        try (InputStream input = new FileInputStream(p.toString())) {
            Properties managerProperty = new Properties();
            managerProperty.load(input);
            this.curID = Integer.parseInt(managerProperty.getProperty("CID"));
            String[] compToLoad = managerProperty.getProperty("CLIST").split(this.regex);

            // Use the file constructor for Component for each one itemized in CLIST
            // Note: Extra functionality will need to be added for  distinguishing BikeObj files and their owned components
            tempLoadList = new LinkedList<Component>();
            for (String componentID : compToLoad) {
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

    public ComponentManager() {
        componentList = new HashMap<Part, LinkedList<Component>>();
        curID = 1;
    }

    // public methods

    // Remove a specific component from the list using its unique componentId
    public Component extractComponentFromId(Part type, String componentId) {

        // Scan and search for component by ID, return it if found
        LinkedList<Component> subList = componentList.get(type);

        Component C = null;
        for(int i=0; i<subList.size(); i++) {
            C = subList.get(i);
            if(C.getCompID() == componentId) {
                componentList.get(type).remove(C);
                return C;
            }
        }

        // Otherwise return null if part not found in list
        return null;
    }

    // Add a new component to the catalog
    // If the list for a given part does not exist yet, create it first
    public void addCatalogComponent(Component cmp) {
        if(componentList.get(cmp.getPart()) == null)
            componentList.put(cmp.getPart(), new LinkedList<Component>());
        componentList.get(cmp.getPart()).add(cmp);
    }

    // Save all components to a file, then save an additional file listing all cataloged components, which will be seperated with a '#' characetr
    @Override
    public void saveToFile() throws IOException {
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + File.pathSeparator + "src" + File.pathSeparator + "app" +
                  File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + "ComponentManager.properties");
        StringBuffer constructPList = new StringBuffer();
        try (OutputStream outfile = new FileOutputStream(p.toString())){
            Properties manageProb = new Properties();

            LinkedList<Component> curBin;
            for (Part part : Part.values()) {
                curBin = componentList.get(part);
                for (Component comp : curBin) {
                    comp.saveToFile();
                    constructPList.append(comp.getCompID() + "#");
                }
            }

            manageProb.setProperty("CLIST", constructPList.toString());
            manageProb.setProperty("CID", ""+this.curID);
            manageProb.store(outfile, null);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}