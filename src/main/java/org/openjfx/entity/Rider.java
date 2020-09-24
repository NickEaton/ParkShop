package org.openjfx.entity;

import org.openjfx.bike.BikeObj;
import org.openjfx.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class Rider implements Saveable {

    // Fields

    // Particular rider fitness for various trail types
    private double fitness_XC;
    private double fitness_END;
    private double fitness_DH;

    // Will be used in negotiation and sell algorithms later
    private double preferenceRentBuy;
    private double financialIntensity;

    // unique ID of this rider, which is used to identify, save and load their properties
    private String riderID;

    // List of this riders owned bikes (The shop itself may be considered an "owner")
    private ArrayList<BikeObj> bikes;

    // Regex character used for data loads and stores
    private static final String regex = "#";

    // Private ID for indexing this users bikes
    private static int curID;

    // File Constructor
    public Rider(String fileID) throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                                    "\\src\\main\\resources\\saves\\" + fileID + ".properties");

        bikes = new ArrayList<BikeObj>();
        try(InputStream input = new FileInputStream(p.toString())) {
            Properties prop = new Properties();
            prop.load(input);

            this.fitness_XC = Double.parseDouble(prop.getProperty("FXC"));
            this.fitness_END = Double.parseDouble(prop.getProperty("FEND"));
            this.fitness_DH = Double.parseDouble(prop.getProperty("FDH"));
            this.preferenceRentBuy = Double.parseDouble(prop.getProperty("PRB"));
            this.financialIntensity = Double.parseDouble(prop.getProperty("FI"));
            this.riderID = prop.getProperty("RID");

            String[] BKS = prop.getProperty("BIKES").split(this.regex);
            for (String B : BKS) {
                bikes.add(new BikeObj(this, B));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Primary Constructor
    public Rider(double _fitnessXC, double _fitnessEND, double _fitnessDH, String _RID, double _preferenceRentBuy, double _financialIntesity) {
        this.fitness_XC = _fitnessXC;
        this.fitness_END = _fitnessEND;
        this.fitness_DH = _fitnessDH;
        this.preferenceRentBuy = _preferenceRentBuy;
        this.financialIntensity = _financialIntesity;
        this.riderID = _RID;
        this.curID = 1;
        bikes = new ArrayList<BikeObj>();
    }

    // Default, player constructor
    public Rider(String name, boolean def) {
        this.fitness_XC = 0;
        this.fitness_END = 0;
        this.fitness_DH = 0;
        this.preferenceRentBuy = 0;
        this.financialIntensity = 0;
        this.riderID = name;
        this.curID = 1;
        bikes = new ArrayList<BikeObj>();
    }

    // Getters
    public double getFitness_XC() { return this.fitness_XC; }
    public double getFitness_END() { return this.fitness_END; }
    public double getFitness_DH() { return this.fitness_DH; }
    public String getRiderID() { return this.riderID; }
    public double getPreferenceRentBuy() { return this.preferenceRentBuy; }
    public double getFinancialIntensity() { return this.financialIntensity; }

    // Public Methods
    public ArrayList<BikeObj> getOwnedBikes() {
        return this.bikes;
    }

    // Save rider data to a file which can be recursively loaded later
    @Override
    public void saveToFile() throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +
                            "\\src\\main\\resources\\saves\\" + this.riderID + ".properties");

        StringBuffer saveBikes = new StringBuffer();
        try (OutputStream output = new FileOutputStream(p.toString())) {
            Properties prop = new Properties();

            prop.setProperty("FXC", ""+this.fitness_XC);
            prop.setProperty("FEND", ""+this.fitness_END);
            prop.setProperty("FDH", ""+this.fitness_DH);
            prop.setProperty("PRB", ""+this.preferenceRentBuy);
            prop.setProperty("FI", ""+this.financialIntensity);
            prop.setProperty("RID", this.riderID);
            prop.setProperty("CID", ""+this.curID);

            for(BikeObj bike : bikes) {
                saveBikes.append(bike.getBikeID() + this.regex);
                bike.saveToFile();
            }
            prop.setProperty("BIKES", saveBikes.toString());

            prop.store(output, null);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Override Object toString method
    @Override
    public String toString() {
        StringBuffer s1 = new StringBuffer("{ FXC: " +fitness_XC + ", FEND: " + fitness_END + ", FDH: " + fitness_DH +
                            ", PRB: " +preferenceRentBuy + ", FI: " + financialIntensity + ", RID: " + riderID +"}\n");
        for(BikeObj B : this.bikes) {
            s1.append(B.toString());
        }
        return s1.toString();
    }
}
