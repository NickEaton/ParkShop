package app.java.bike;

import app.java.components.Component;
import app.java.entity.Rider;

import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;

public class BikeObj {

    private double bike_fitness_XC;
    private double bike_fitness_END;
    private double bike_fitness_DH;

    private int numParts = 1;

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
            bike_fitness_DH += (cur.getFitDH()) / numParts;
            bike_fitness_END += (cur.getFitEND()) / numParts;
            bike_fitness_XC += (cur.getFitXC()) / numParts;
        }
    }

    // Constructors

    public BikeObj(File readfile) {
        //TODO: Implement File reads and writes for Bike objects
    }

    //TODO: Update constructor as components are finalized
    public BikeObj(Rider _owner, LinkedList<Component> parts) {
        this.owner = _owner;
        for(int k=0; k<parts.size(); k++) {
            partList.add(parts.get(k));
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
}
