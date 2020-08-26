package app.java.components;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

// This class will store, organize and manage the total list of components stored
public class ComponentManager {

    // There are 19 distinct parts required
    // Frame, Wheel-F, Wheel-R, Tire-F, Tire-R, Fork, Shock, Brakes, Rotor-F, Rotor-R, Shifters, Cranks, Pedals, Chainring, Chain, Cassette, Derailer, Handlebar, Grips

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

    // Constructors
    public ComponentManager(File filein) {
        //TODO: Files
    }

    public ComponentManager() {
        componentList = new HashMap<Part, LinkedList<Component>>();
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


}
