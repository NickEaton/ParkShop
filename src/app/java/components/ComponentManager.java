package app.java.components;

import java.io.File;
import java.util.LinkedList;
import java.util.stream.Stream;

// This class will store, organize and manage the total list of components stored
public class ComponentManager {

    // There are 19 distinct parts required
    // Frame, Wheel-F, Wheel-R, Tire-F, Tire-R, Fork, Shock, Brakes, Rotor-F, Rotor-R, Shifters, Cranks, Pedals, Chainring, Chain, Cassette, Derailer, Handlebar, Grips

    // Fields
    private LinkedList<Component> componentList;

    // Constructors
    public ComponentManager(File filein) {
        //TODO: Files
    }

    public ComponentManager() {
        componentList = new LinkedList<Component>();
    }

    // public methods

    public Component extractComponentFromId(String componentId) {

        // Scan and search for component by ID, return it if found
        Component C = null;
        for(int i=0; i<componentList.size(); i++) {
            C = componentList.get(i);
            if(C.getCompID() == componentId) {
                componentList.remove(i);
                return C;
            }
        }

        // Otherwise return null if part not found in list
        return null;
    }

    public void addCatalogComponent(Component cmp) {
        componentList.add(cmp);
        /*while(componentList.get(j).getCompName().compareTo(cmp.getCompName()) < 0) {
            WIP - list ought be sorted
        }*/
    }
}
