package app.java.entity;

import java.io.File;

public class Rider {

    // Fields
    private double fitness_XC;
    private double fitness_END;
    private double fitness_DH;

    private double preferenceRentBuy;
    private double financialIntensity;

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

    // Getters
    public double getFitness_XC() { return this.fitness_XC; }
    public double getFitness_END() { return this.fitness_END; }
    public double getFitness_DH() { return this.fitness_DH; }
}
