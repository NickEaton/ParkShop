package app.java.entity;

import app.java.bike.BikeObj;

import java.io.File;
import java.util.ArrayList;

public class Rider {

    // Fields
    private double fitness_XC;
    private double fitness_END;
    private double fitness_DH;

    private double preferenceRentBuy;
    private double financialIntensity;

    private ArrayList<BikeObj> bikes;

    // Constructors
    public Rider(File readfile) {
        // TODO: File implementation constructor
    }

    public Rider(double _fitnessXC, double _fitnessEND, double _fitnessDH, double _preferenceRentBuy, double _financialIntesity) {
        this.fitness_XC = _fitnessXC;
        this.fitness_END = _fitnessEND;
        this.fitness_DH = _fitnessDH;
        this.preferenceRentBuy = _preferenceRentBuy;
        this.financialIntensity = _financialIntesity;

    }

    // Primary Methods
    public void saveState() {
        //TODO: File output
    }

    // Getters
    public double getFitness_XC() { return this.fitness_XC; }
    public double getFitness_END() { return this.fitness_END; }
    public double getFitness_DH() { return this.fitness_DH; }
}
