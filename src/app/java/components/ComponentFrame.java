package app.java.components;

import java.io.File;

public class ComponentFrame extends Component {

    // - Constructors

    // File Constructor
    public ComponentFrame(File fileIn) {
        //TODO: Read from file constructor implementation
    }

    // Default Constuctor
    public ComponentFrame(double _wearPercent, double _fitness_XC, double _fitness_END, double _fitness_DH, String _compName, String _compID, double _timeModifier, double _costUSD, double _marginUSD) {
        this.wearPercent = _wearPercent;
        this.fitness_XC = _fitness_XC;
        this.fitness_END = _fitness_END;
        this.fitness_DH = _fitness_DH;
        this.compName = _compName;
        this.compID = _compID;
        this.timeModifier = _timeModifier;
        this.costUSD = _costUSD;
        this.marginUSD = _marginUSD;

        sectionId = 1;
    }

    @Override
    public double getWearPercent() {
        return this.wearPercent;
    }

    @Override
    public double getFitXC() {
        return this.fitness_XC;
    }

    @Override
    public double getFitDH() {
        return this.fitness_DH;
    }

    @Override
    public double getFitEND() {
        return this.fitness_END;
    }

    @Override
    public String getCompName() {
        return this.compName;
    }

    @Override
    public String getCompID() {
        return this.compID;
    }

    @Override
    public int getSectionId() {
        return this.sectionId;
    }

    @Override
    public double getTimeModifier() {
        return this.timeModifier;
    }

    @Override
    public double getCostUSD() {
        return this.costUSD;
    }

    @Override
    public double getMarginUSD() {
        return this.marginUSD;
    }
}
