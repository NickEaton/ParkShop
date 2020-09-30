package org.openjfx.entity;

import org.openjfx.util.Saveable;

import java.io.IOException;

public class Employee implements Saveable {

    // Fields
    private double skillConstruct;
    private double skillRepair;
    private double skillTrail;
    private double affinity;
    private double attraction;
    private String unlocalizedAssignment;
    private int employeeId;

    private String name;
    private int eLevel;

    //Constructors
    public Employee(String fileID) throws IOException {
        //TODO: Files here
    }

    // May be implemented later
    public Employee(double _skillConstruct, double _skillRepair, double _skillTrail, double _affinity, double _attraction, String _unlocalizedAssignment, int _employeeId, String _name) {
        this.skillConstruct = _skillConstruct;
        this.skillRepair = _skillRepair;
        this.skillTrail = _skillTrail;
        this.affinity = _affinity;
        this.attraction = _attraction;
        this.unlocalizedAssignment = _unlocalizedAssignment;
        this.employeeId = _employeeId;
        this.name = _name;
    }

    public Employee(String name, int level) {
        this.name = name;
        this.eLevel = level;

        this.skillConstruct = 0;
        this.skillRepair = 0;
        this.skillTrail = 0;
        this.affinity = 0;
        this.attraction = 0;
        this.unlocalizedAssignment = "";
        this.employeeId = 0;
    }

    // Getters
    public double getSkillConstruct() { return this.skillConstruct; }
    public double getSkillRepair() { return this.skillRepair; }
    public double getSkillTrail() { return this.skillTrail; }
    public double getAffinity() { return this.affinity; }
    public double getAttraction() { return this.attraction; }
    public String getUnlocalizedAssignment() { return this.unlocalizedAssignment; }
    public int getEmployeeId() { return this.employeeId; }
    public String getName() { return this.name; }
    public int getLevel() { return this.eLevel; }

    // Primary Methods

    @Override
    public void saveToFile() throws IOException {
        // TODO: Files once the employee system is reached
    }
}
