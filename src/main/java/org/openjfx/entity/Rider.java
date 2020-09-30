package org.openjfx.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import org.openjfx.bike.BikeObj;
import org.openjfx.trail.Trail;
import org.openjfx.trail.TrailVectorNode;
import org.openjfx.util.Saveable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

    // Speed throughout the race, which will be a multiplier to time
    private double rSpeed;

    // Regex character used for data loads and stores
    private static final String regex = "#";

    // Private ID for indexing this users bikes
    private static int curID;



    // Active bike during a race
    private BikeObj activeBike;

    // RNG factor for a race
    private int RNGLevel;

    // Crashes during a race
    private int segCrashes;
    private int featureCrashes;
    private double finalTime;


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
    public double getFinalTime() { return this.finalTime; }

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

    // Compute a race level score for this rider on a particular trail from 0-100
    // TODO: Better
    public double getTrackScore(Trail trail) {
        double rMod = this.RNGLevel * (1/trail.getVariance());                    // "Lucky" rider should offset "Unlucky" track
        double proficiency = (this.fitness_XC/2 + this.activeBike.getBike_fitness_XC()/2) / trail.getXC_lvl() +
                             (this.fitness_END/2 + this.activeBike.getBike_fitness_END()/2) / trail.getXC_lvl() +
                             (this.fitness_DH/2 + this.activeBike.getBike_fitness_DH()/2) / trail.getDH_lvl();          // How well this rider + bike ought to do on this track

        return (rMod+3)*proficiency;
    }

    /*
    // TODO: this sucks
    public Timeline start(Trail trail) {
        final LinkedList<TrailVectorNode> obList = trail.getTrailPath();
        final int[] obIndex = {0};
        double chanceAccident = trail.getVariance()/this.RNGLevel*100;
        final double[] totalTime = {0};
        final double[] segTime = {0};

        ScheduledExecutorService scheduleRace = Executors.newScheduledThreadPool(1);
        final Runnable executeRace = new Runnable() {
            Runnable manageRace;
            public void defineManager(Runnable x) { manageRace = x; }

            @Override
            public void run() {
                if(obIndex[0] < obList.size()) {
                    totalTime[0] += segTime[0];
                    segTime[0] = obList.get(obIndex[0]).getBaseTimeSeconds()*rSpeed;
                    obIndex[0]++;
                    scheduleRace.execute(manageRace);
                }
            }
        };

        final Runnable manageRace = new Runnable() {
            @Override
            public void run() {
                if(obIndex[0] < obList.size()) {
                    totalTime[0] += segTime[0];
                    segTime[0] = obList.get(obIndex[0]).getBaseTimeSeconds()*rSpeed;
                    obIndex[0]++;
                    scheduleRace.schedule(executeRace, (long)segTime[0], TimeUnit.SECONDS);
                }
            }
        };

        ScheduledFuture<?> raceHandle = scheduleRace.schedule(executeRace, (long)obList.get(0).getBaseTimeSeconds(), TimeUnit.SECONDS);

        return null;
    }
    */

    // compute timing, score, etc for line segment
    public double runSegment(Trail owner, TrailVectorNode line) {
        double crashChance = owner.getVariance()/this.RNGLevel * 100;
        Random r = new Random();
        double deductions = 1;

        if(Math.round(crashChance) <= r.nextInt(100)) {
            // The rider crashes on the segment, the faster they were going the greater the deduction
            deductions += 1/this.rSpeed;
        }

        if(line.getFeature() != null) {
            crashChance = owner.getVariance() / this.RNGLevel * 100 * line.getFeature().getIntensity();
            if (Math.round(crashChance) <= r.nextInt(100)) {
                // The rider crashes on the feature
                deductions += 1/this.rSpeed + 1;
            }
        }

        return line.getBaseTimeSeconds() * (1/rSpeed) * deductions;
    }
}
