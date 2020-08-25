package app.java.entity;

import java.io.File;

public class Employee {

    // Fields
    private double skillConstruct;
    private double skillRepair;
    private double skillTrail;
    private double affinity;
    private double attraction;
    private String unlocalizedAssignment;
    private int employeeId;

    //Constructors
    public Employee(File readfile) {
        //TODO: File build sys
    }

    public Employee(double _skillConstruct, double _skillRepair, double _skillTrail, double _affinity, double _attraction, String _unlocalizedAssignment, int _employeeId) {
        this.skillConstruct = _skillConstruct;
        this.skillRepair = _skillRepair;
        this.skillTrail = _skillTrail;
        this.affinity = _affinity;
        this.attraction = _attraction;
        this.unlocalizedAssignment = _unlocalizedAssignment;
        this.employeeId = _employeeId;
    }

    // Primary Methods
    public void saveState() {
        //TODO: File output
    }

    // Getters
    public double getSkillConstruct() { return this.skillConstruct; }
    public double getSkillRepair() { return this.skillRepair; }
    public double getSkillTrail() { return this.skillTrail; }
    public double getAffinity() { return this.affinity; }
    public double getAttraction() { return this.attraction; }
    public String getUnlocalizedAssignment() { return this.unlocalizedAssignment; }
    public int getEmployeeId() { return this.employeeId; }
}
