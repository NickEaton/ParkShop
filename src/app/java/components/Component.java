package app.java.components;


// Nick Eaton
// 8/24/2020
// A class representing the basic fields and methods all bike components will have

import java.io.File;

public abstract class Component {

    // 0 - 100 rating of how much wear the component has left
    protected double wearPercent = 0;

    // 3-tuple 0-100 rating of <XC, END, DH> performance 'factor', these will be a constant read from file
    protected double fitness_XC = 0;
    protected double fitness_END = 0;
    protected double fitness_DH = 0;

    // Display name of the component
    protected String compName = "<Default>";

    // Internal ID of this particular component
    protected String compID = "<Default>";

    // What section a given component fits on the bike
    protected int sectionId = 0;

    // How much longer/shorter the build time
    protected double timeModifier = 1;

    // The default cost of the component in USD before modifiers
    protected double costUSD = 0;

    // Base sugested markup margin in USD for the player before any potential modifiers
    protected double marginUSD = 0;

    //------------------------------------------------------------------------------------------------------//

    // Getters
    public abstract double getWearPercent();
    public abstract double getFitXC();
    public abstract double getFitDH();
    public abstract double getFitEND();
    public abstract String getCompName();
    public abstract String getCompID();
    public abstract int getSectionId();
    public abstract double getTimeModifier();
    public abstract double getCostUSD();
    public abstract double getMarginUSD();
}
