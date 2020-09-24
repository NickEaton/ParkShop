package org.openjfx.bike;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import org.openjfx.components.Component;
import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Properties;

public class BikeObj implements Saveable {

    //Fields

    // Regex character to separate strings for File I/O
    private static final String regex = "#";

    // Overall stats of the bike
    private double bike_fitness_XC;
    private double bike_fitness_END;
    private double bike_fitness_DH;

    // Unique ID of this bike used for stores, loads and access
    private String bikeID;

    // Player given name of this bike
    private String bikeName;

    // Base price of components
    private double baseCost;

    // baseCost + markup
    private double price;

    // Every bike will have an owner, even if it is the Shop itself
    private Rider owner;

    // List of all components in this bike, constructors verify that there will be precisely one of each component type
    private LinkedList<Component> partList;

    // Associated image
    @FXML
    public Image bikePic;

    // Private method to calculate overall fitness of the bike based on every component
    private void compAggrFit() {
        ListIterator<Component> listIterator = partList.listIterator();
        Component cur = null;
        while(listIterator.hasNext()) {
            cur = listIterator.next();
            bike_fitness_DH += (cur.getFitDH());
            bike_fitness_END += (cur.getFitEND());
            bike_fitness_XC += (cur.getFitXC());
        }
    }

    // File Constructor
    public BikeObj(Rider ownerID, String fileID) throws IOException {
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                                "\\src\\main\\resources\\org\\saves\\" + fileID + ".properties");

        String[] tmpComps;
        partList = new LinkedList<Component>();
        try(InputStream input = new FileInputStream(p.toString())) {
            Properties bikeProp = new Properties();
            bikeProp.load(input);

            tmpComps = bikeProp.getProperty("CLIST").split(this.regex);
            this.bikeID = bikeProp.getProperty("BIKID");
            this.bikeName = bikeProp.getProperty("BIKNAME");

            //TODO: this method in RiderManager needs to be implemented
            this.owner = ownerID;

            // Load all components for this bike
            for(String partName : tmpComps) {
                partList.push(new Component(partName));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Primary constructor for new Bikes
    public BikeObj(String _bikeID, Rider _owner, LinkedList<Component> parts) {
        this.bikeID = _bikeID;
        this.owner = _owner;
        this.partList = new LinkedList<Component>();

        for(Component k : parts) {
            partList.add(k);
        }

        //baseCost = _frame.getCostUSD();
        //price = baseCost;                                    // May be modified by user after object creation
    }

    //-----------------------------------------------------------------------//
    // Public Methods
    //-----------------------------------------------------------------------//

    // Getters
    public double getBike_fitness_XC() { return this.bike_fitness_XC; }
    public double getBike_fitness_END() { return this.bike_fitness_END; }
    public double getBike_fitness_DH() { return this.bike_fitness_DH; }
    public double getPrice() { return this.price; }
    public double getBaseCost() { return this.baseCost; }
    public String getBikeID() { return this.bikeID; }
    public String getBikeName() { return this.bikeName; }

    // Setters
    public void setBike_fitness_XC(double _BXC) { this.bike_fitness_XC = _BXC; }
    public void setBike_fitness_END(double _BEND) { this.bike_fitness_END = _BEND; }
    public void setBike_fitness_DH(double _BDH) { this.bike_fitness_DH = _BDH; }
    public void setPrice(double _price) { this.price = _price; }
    public void setBaseCost(double _baseCost) { this.baseCost = _baseCost; }
    public void setBikeName(String _name) { this.bikeName = _name; }
    public void setBikeID(String _ID) { this.bikeID = _ID; }

    // Save this Bike Object and its components to an appropriate file
    @Override
    public void saveToFile() throws IOException {
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                         "\\src\\main\\resources\\org\\saves\\" + this.bikeID + ".properties");
        try(OutputStream outfile = new FileOutputStream(p.toString())) {
            Properties bikeProp = new Properties();

            // Save the Component List for this BikeObj
            StringBuffer compList = new StringBuffer();
            for(Component comp : partList) {
                comp.saveToFile();
                compList.append(comp.getCompID()+this.regex);
            }
            bikeProp.setProperty("CLIST", compList.toString());
            bikeProp.setProperty("BIKID", this.bikeID);
            bikeProp.setProperty("BIKNAME", this.bikeName);
            bikeProp.setProperty("OWNER", ""+this.owner);

            bikeProp.store(outfile, null);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Override Object toString method
    @Override
    public String toString() {
        StringBuffer s2 = new StringBuffer("[ BXC: " + bike_fitness_XC + ", BEND: " + bike_fitness_END + ", BDH: " + bike_fitness_DH +
                                        ", BID: " + bikeID + ", BC: " + baseCost + ", PRI: " + price + ", NAME: " + bikeName + "]\n");
        for(Component C : this.partList) {
            s2.append(C.toString());
        }
        return s2.toString();
    }
}
