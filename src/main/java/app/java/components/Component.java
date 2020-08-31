package app.java.components;


// Nick Eaton
// 8/24/2020
// A class representing the basic fields and methods all bike components will have

import app.java.util.Saveable;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

import static java.nio.file.Files.newBufferedWriter;

public class Component implements Saveable {

    // Fields

    // 0 - 100 rating of how much wear the component has left
    private double wearPercent;

    // 3-tuple 0-100 rating of <XC, END, DH> performance 'factor', these will be a constant read from file
    private double fitness_XC;
    private double fitness_END;
    private double fitness_DH;

    // Display name of the component
    private String compName;

    // Internal ID of this particular component
    private String compID;

    // How much longer/shorter the build time
    private double timeModifier;

    // The default cost of the component in USD before modifiers
    private double costUSD;

    // Base sugested markup margin in USD for the player before any potential modifiers
    private double marginUSD;

    // What material the part is made of
    private ComponentManager.Material material;

    // What particular part this is
    private ComponentManager.Part part;

    //------------------------------------------------------------------------------------------------------//

    // File Constructor
    public Component(String fileID) throws IOException {
        Path p = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src" + "\\app" + "\\resources" + "\\saves" + "\\" + fileID + ".properties");

        try (InputStream input = new FileInputStream(p.toString())) {
            Properties property = new Properties();
            property.load(input);

            this.wearPercent = Double.parseDouble(property.getProperty("WP"));
            this.fitness_XC = Double.parseDouble(property.getProperty("FXC"));
            this.fitness_END = Double.parseDouble(property.getProperty("FEND"));
            this.fitness_DH = Double.parseDouble(property.getProperty("FDH"));
            this.compName = property.getProperty("CNAME");
            this.compID = property.getProperty("CID");
            this.timeModifier = Double.parseDouble(property.getProperty("TMOD"));
            this.costUSD = Double.parseDouble(property.getProperty("CUSD"));
            this.marginUSD = Double.parseDouble(property.getProperty("MUSD"));
            this.material = ComponentManager.Material.valueOf(property.getProperty("MAT"));
            this.part = ComponentManager.Part.valueOf(property.getProperty("PART"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Primary Constructor
    public Component(double _wearPercent, double _fitness_XC, double _fitness_END, double _fitness_DH, String _compName, String _compID, double _timeModifier, double _costUSD, double _marginUSD, ComponentManager.Material _material, ComponentManager.Part _part) {
        this.wearPercent = _wearPercent;
        this.fitness_XC = _fitness_XC;
        this.fitness_END = _fitness_END;
        this.fitness_DH = _fitness_DH;
        this.compName = _compName;
        this.compID = _compID;
        this.timeModifier = _timeModifier;
        this.costUSD = _costUSD;
        this.marginUSD = _marginUSD;
        this.material = _material;
        this.part = _part;
    }

    // Getters
    public double getWearPercent() { return this.wearPercent; }
    public double getFitXC() { return this.fitness_XC; }
    public double getFitDH() { return this.fitness_DH; }
    public double getFitEND() { return this.fitness_END; }
    public String getCompName() { return this.compName; }
    public String getCompID() { return this.compID; }
    public double getTimeModifier() { return this.timeModifier; }
    public double getCostUSD() { return this.costUSD; }
    public double getMarginUSD() { return this.marginUSD; }
    public ComponentManager.Material getMaterial() { return this.material; }
    public ComponentManager.Part getPart() { return this.part; }

    // Public Methods

    // Save this component to a properties file, since we are only storing field data
    @Override
    public void saveToFile() throws IOException {
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src" + "\\app" + "\\resources" + "\\saves" + "\\" + this.compID + ".properties");

        try (OutputStream output = new FileOutputStream(p.toString())) {
            Properties property = new Properties();

            property.setProperty("WP", ""+this.wearPercent);
            property.setProperty("FXC", ""+this.fitness_XC);
            property.setProperty("FEND", ""+this.fitness_END);
            property.setProperty("FDH", ""+this.fitness_DH);
            property.setProperty("CNAME", this.compName);
            property.setProperty("CID", this.compID);
            property.setProperty("TMOD", ""+this.timeModifier);
            property.setProperty("CUSD", ""+this.costUSD);
            property.setProperty("MUSD", ""+this.marginUSD);
            property.setProperty("MAT", ""+this.material);
            property.setProperty("PART", ""+this.part);

            property.store(output, null);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Override Object toString method
    @Override
    public String toString() {
        return "( WP: "+wearPercent+", FXC: "+fitness_XC+", FEND: "+fitness_END+", FDH: "+fitness_DH+", NAME: "+compName+", CID: "+compID+", TMOD: "+timeModifier+", CUSD: "+costUSD+", MUSD: "+marginUSD+", MAT: "+material+", PART: "+part+")\n";
    }
}
