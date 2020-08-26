package app.java.bike;

import app.java.components.Component;
import app.java.entity.Rider;
import app.java.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Properties;

public class BikeObj implements Saveable {

    private static final String regex = "#";

    private double bike_fitness_XC;
    private double bike_fitness_END;
    private double bike_fitness_DH;

    private String bikeID;

    private double baseCost;
    private double price;

    private Rider owner;
    private LinkedList<Component> partList;

    // Private Methods

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

    // Constructors

    public BikeObj(Rider ownerID, String fileID) throws IOException {
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + File.pathSeparator + "src" + File.pathSeparator + "app" + File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + fileID + ".properties");

        String[] tmpComps;
        partList = new LinkedList<Component>();
        try(InputStream input = new FileInputStream(p.toString())) {
            Properties bikeProp = new Properties();
            bikeProp.load(input);

            tmpComps = bikeProp.getProperty("CLIST").split(this.regex);
            this.bikeID = bikeProp.getProperty("BIKID");

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

    public BikeObj(String _bikeID, Rider _owner, LinkedList<Component> parts) {
        this.bikeID = _bikeID;
        this.owner = _owner;
        for(Component k : parts) {
            partList.add(k);
        }

        //baseCost = _frame.getCostUSD();
        //price = baseCost;                                    // May be modified by user after object creation
    }

    // Getters
    public double getBike_fitness_XC() { return this.bike_fitness_XC; }
    public double getBike_fitness_END() { return this.bike_fitness_END; }
    public double getBike_fitness_DH() { return this.bike_fitness_DH; }
    public double getPrice() { return this.price; }
    public double getBaseCost() { return this.baseCost; }
    public String getBikeID() { return this.bikeID; }

    // Public Methods

    // Save this Bike Object and its components to an appropriate file
    @Override
    public void saveToFile() throws IOException {
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + File.pathSeparator + "src" + File.pathSeparator + "app" + File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + this.bikeID + ".properties");
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
            bikeProp.setProperty("OWNER", ""+this.owner);

            bikeProp.store(outfile, null);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
