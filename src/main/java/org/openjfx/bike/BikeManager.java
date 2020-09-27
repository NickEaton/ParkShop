package org.openjfx.bike;

import org.openjfx.ParkShopApp;
import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;
import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;

public class BikeManager implements Saveable {

    // This hashmap maps Rider objects to a list of Bikes they are cataloged as owning
    private ArrayList<Rider> riderCatalog;
    public ArrayList<BikeObj> BKList;

    // While the user is selecting parts, the data will be stored here
    // Note, this is empty on load
    // public HashMap<ComponentManager.Part, Component> activePartList;
    public Component activeFrame;
    public Component activeShock;
    public Component activeFork;
    public Component activeWheelF;
    public Component activeWheelR;
    public Component activeTireF;
    public Component activeTireR;
    public Component activeBrakeF;
    public Component activeBrakeR;
    public Component activeRotorF;
    public Component activeRotorR;
    public Component activeChain;
    public Component activeChainring;
    public Component activeCassette;
    public Component activeDerailleur;
    public Component activeCranks;
    public Component activePedals;
    public Component activeHandlebar;
    public Component activeGrips;
    public Component activeShifter;
    public Component activeBrakeLever;
    public Component activeSeat;
    public Component activeSeatpost;

    private static int curID;

    // Regex for file I/O
    private static final String regex = "#";

    // package active components for build and erase
    public LinkedList<Component> compressActive() {
        LinkedList<Component> temp = new LinkedList<Component>();
        temp.addAll(Arrays.asList(activeShock, activeFork, activeWheelF, activeWheelR, activeTireR, activeTireF,
                    activeBrakeR, activeBrakeF, activeRotorF, activeRotorR, activeChain, activeChainring, activeCassette,
                    activeDerailleur, activeCranks, activePedals, activeHandlebar, activeGrips, activeShifter,
                    activeBrakeLever, activeSeat, activeSeatpost));
        return temp;
    }

    // File Constructor
    public BikeManager(String fileID) throws IOException {

        // Initialize maps for this manager
        riderCatalog = new ArrayList<Rider>();
        //activePartList = new HashMap<ComponentManager.Part, Component>();

        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                                 "\\src\\main\\resources\\org\\saves\\" + fileID + ".properties");

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
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error Loading BikeManager");
            exception.printStackTrace();
        }
    }

    // Primary constructor for first time launch
    public BikeManager() {
        riderCatalog = new ArrayList<Rider>();
        BKList = new ArrayList<BikeObj>();
        riderCatalog.add(new Rider("Player", true));
        curID = 1;
    }

    // This section deals with 'assembling' bikes

    // While the bike is being built up, use this method to add parts to the active list
    // Not ideal, possibly refactor at some point to remove fluff
    public void addSwapComponent(Component comp, int frontBackDelin) {
        switch(comp.getPart()) {
            case FORK:
                activeFork = comp;
                break;
            case SEAT:
                activeSeat = comp;
                break;
            case TIRE:
                switch (frontBackDelin) {
                    case 0 -> activeTireF = comp;
                    case 1 -> activeTireR = comp;
                    default -> ParkShopApp.primaryLog.log(Level.WARNING, "Err invalid delin value");
                }
                break;
            case BRAKE:
                switch (frontBackDelin) {
                    case 0 -> activeBrakeF = comp;
                    case 1 -> activeBrakeR = comp;
                    default -> ParkShopApp.primaryLog.log(Level.WARNING, "Err invalid delin value");
                }
                break;
            case CHAIN:
                activeChain = comp;
                break;
            case FRAME:
                activeFrame = comp;
                break;
            case GRIPS:
                activeGrips = comp;
                break;
            case ROTOR:
                switch (frontBackDelin) {
                    case 0 -> activeRotorF = comp;
                    case 1 -> activeRotorR = comp;
                    default -> ParkShopApp.primaryLog.log(Level.WARNING, "Err invalid delin value");
                }
                break;
            case SHOCK:
                activeShock = comp;
                break;
            case WHEEL:
                switch (frontBackDelin) {
                    case 0 -> activeWheelF = comp;
                    case 1 -> activeWheelR = comp;
                    default -> ParkShopApp.primaryLog.log(Level.WARNING, "Err invalid delin value");
                }
                break;
            case CRANKS:
                activeCranks = comp;
                break;
            case PEDALS:
                activePedals = comp;
                break;
            case SHIFTER:
                activeShifter = comp;
                break;
            case CASSETTE:
                activeCassette = comp;
                break;
            case SEATPOST:
                activeSeatpost = comp;
                break;
            case CHAINRING:
                activeChainring = comp;
                break;
            case HANDLEBAR:
                activeHandlebar = comp;
                break;
            case DERAILLEUR:
                activeDerailleur = comp;
                break;
            case BRAKE_LEVER:
                activeBrakeLever = comp;
                break;
            default:
                ParkShopApp.primaryLog.log(Level.WARNING, "Err invalid component");
                break;
        }
    }

    // Rider lookup by name
    public Rider lookupByName(String rName) {
        for(Rider r : this.riderCatalog) {
            if(r.getRiderID().equals(rName))
                return r;
        }
        ParkShopApp.primaryLog.log(Level.SEVERE, "Error on Rider Lookup");
        return null;
    }

    // Verify that the partslist is correct, i.e. there should be precisely one of each type of part
    // PRE: parts should never have a repeated component
    public void constructBike(Rider owner, Map<ComponentManager.Part, Component> parts) {

        // Construct a new bike with the tmp parts list loaded, with a Rider owner, and a new unique ID associated with the rider & total bikes
        BikeObj newBike = new BikeObj((""+owner.getRiderID()+(curID++)), owner, new LinkedList<Component>(parts.values()));
        owner.getOwnedBikes().add(newBike);
        if(!riderCatalog.contains(owner))
            riderCatalog.add(owner);
    }

    // Do local construction, delete & null components
    // PRE: all active components are defined
    // TODO
    public BikeObj doLocalConstruct(String bkName, Rider owner) {
        BikeObj local = new BikeObj(bkName, owner, compressActive());
        this.BKList.add(local);
        ParkShopApp.primaryLog.log(Level.FINER, local.toString());
        return local;
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
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                                "\\src\\main\\resources\\org\\saves\\BikeManager.properties");

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
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error saving BikeManager");
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
