package org.openjfx.entity;

import javafx.fxml.FXML;
import org.openjfx.ParkShopApp;
import org.openjfx.PrimaryController;
import org.openjfx.bike.BikeManager;
import org.openjfx.bike.BikeObj;
import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Player implements Saveable {

    // Enums
    public enum Rank {
        STAR_I,
        STAR_II,
        STAR_III,
        STAR_IV,
        STAR_V
    }

    // Fields
    private String name;
    private Rank playerRank;
    private double wallet;
    private ComponentManager componentManager;
    private BikeManager bikeManager;
    Random rand;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ArrayList<Component> listComponents;
    private ArrayList<BikeObj> listBikes;
    private ArrayList<Employee> listEmployees;

    private ArrayList<Component> ownedComponents;
    private ArrayList<BikeObj> ownedBikes;
    private ArrayList<Employee> hiredEmployees;

    // Default Constructor, there are some issues here
    public Player() {
        componentManager = ParkShopApp.cmpManager;

        rand = new Random();
        String ID = ComponentManager.Part.values()[rand.nextInt(19)].toString();

        Runnable getNewComp = () -> { componentManager.addShopComponent(componentManager.getNewRandComponent(ComponentManager.Part.values()[rand.nextInt(19)].toString().toLowerCase())); System.out.println(ComponentManager.Part.values()[rand.nextInt(19)].toString().toLowerCase());};
        Runnable test = () -> System.out.println("Test");
        ScheduledFuture<?> newCompHandle = scheduler.scheduleAtFixedRate(getNewComp, 5, 50, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(test, 5, 50, TimeUnit.SECONDS);
    }

    // File Constructor
    public Player(String fileName) throws IOException {

    }

    //-----------------------------------------------------------------------//
    // Public Methods
    //-----------------------------------------------------------------------//

    // Getters
    public String getName() { return this.name; }
    public Rank getPlayerRank() { return this.playerRank; }
    public double getWallet() { return this.wallet; }
    public ArrayList<Component> getOwnedComponents() { return this.ownedComponents; }
    public ArrayList<BikeObj> getOwnedBikes() { return this.ownedBikes; }
    public ArrayList<Employee> getHiredEmployees() { return this.hiredEmployees; }

    // Setters
    public void setName(String _name) { this.name = _name; }
    public void setPlayerRank(Rank _rank) { this.playerRank = _rank; }
    public void setWallet(double _wallet) { this.wallet = _wallet; }

    // Mutators
    public void addComponent(Component _comp) {
        if(ownedComponents.contains(_comp))
            return;
        ownedComponents.add(_comp);
    }

    public void addBike(BikeObj _bike) {
        if(ownedBikes.contains(_bike))
            return;
        ownedBikes.add(_bike);
    }

    public void addEmployee(Employee _emp) {
        if(hiredEmployees.contains(_emp))
            return;
        hiredEmployees.add(_emp);
    }

    public Component removeComponent(String _compName) {
        for(Component c : ownedComponents) {
            if(c.getCompName().equals(_compName)) {
                this.ownedComponents.remove(c);
                return c;
            }
        }
        return null;
    }

    public BikeObj removeBike(String _bikeName) {
        for(BikeObj b : ownedBikes) {
            if(b.getBikeName().equals(_bikeName)) {
                this.ownedBikes.remove(b);
                return b;
            }
        }
        return null;
    }

    public Employee removeEmployee(String _employeeName) {
        for(Employee e : hiredEmployees) {
            if(e.getName().equals(_employeeName)) {
                this.hiredEmployees.remove(e);
                return e;
            }
        }
        return null;
    }

    // Save method
    @Override
    public void saveToFile() throws IOException {

    }
}
