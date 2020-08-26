package app.java.entity;

import app.java.bike.BikeObj;
import app.java.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class Rider implements Saveable {

    // Fields
    private double fitness_XC;
    private double fitness_END;
    private double fitness_DH;

    private double preferenceRentBuy;
    private double financialIntensity;

    private String riderID;

    private ArrayList<BikeObj> bikes;

    private static final String regex = "#";

    private static int curID;

    // Constructors
    public Rider(String fileID) throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +"src" + File.pathSeparator + "app" + File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + fileID + ".properties");

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

    public Rider(double _fitnessXC, double _fitnessEND, double _fitnessDH, double _preferenceRentBuy, double _financialIntesity) {
        this.fitness_XC = _fitnessXC;
        this.fitness_END = _fitnessEND;
        this.fitness_DH = _fitnessDH;
        this.preferenceRentBuy = _preferenceRentBuy;
        this.financialIntensity = _financialIntesity;
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

    @Override
    public void saveToFile() throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() +"src" + File.pathSeparator + "app" + File.pathSeparator + "resources" + File.pathSeparator + "saves" + File.pathSeparator + this.riderID + ".properties");

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
}
