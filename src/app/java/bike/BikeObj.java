package app.java.bike;

import app.java.components.Component;
import app.java.components.ComponentFrame;

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

    private LinkedList<Component> partList;

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

    public BikeObj(File readfile) {
        //TODO: Implement File reads and writes for Bike objects
    }

    //TODO: Update constructor as components are finalized
    public BikeObj(ComponentFrame _frame) {
        this.partList.add(_frame);

        baseCost = _frame.getCostUSD();
        price = baseCost;                                    // May be modified by user after object creation
    }
}
