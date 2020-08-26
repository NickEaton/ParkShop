package app.java.components;


// Nick Eaton
// 8/24/2020
// A class representing the basic fields and methods all bike components will have

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.Files.newBufferedWriter;

public class Component {

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

    // Constructors
    public Component(File filein) {
        //TODO: Files
    }

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

    // Save this component to a file
    public void saveToFile() throws IOException {
        // String dir = Paths.get(".").toAbsolutePath().normalize().toString() + File.pathSeparator + "saves";
        Path p  = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString() + File.pathSeparator + "saves" + File.pathSeparator + this.compID);

        // Open the file to write to
        BufferedWriter writer = newBufferedWriter(p);

        // Write component data to this file
        writer.write(""+this.wearPercent);
        writer.newLine();
        writer.write(""+this.fitness_XC);
        writer.newLine();
        writer.write(""+this.fitness_END);
        writer.newLine();
        writer.write(""+this.fitness_DH);
        writer.newLine();
        writer.write(this.compName);
        writer.newLine();
        writer.write(this.compID);
        writer.newLine();
        writer.write(""+this.timeModifier);
        writer.newLine();
        writer.write(""+this.costUSD);
        writer.newLine();
        writer.write(""+this.marginUSD);
        writer.newLine();
        writer.write(""+this.material);
        writer.newLine();
        writer.write(""+this.part);
        writer.newLine();

        // Close file when we are done with it
        writer.close();
    }
}
