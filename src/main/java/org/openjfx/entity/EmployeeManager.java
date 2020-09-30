package org.openjfx.entity;

import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;

// A class which will manage the employees
public class EmployeeManager implements Saveable {

    private ArrayList<Employee> staffList;
    private ArrayList<Employee> staffHireList;

    public EmployeeManager() {
        staffList = new ArrayList<Employee>();
        staffHireList = new ArrayList<Employee>();
    }

    public Employee genNewEmployee(String name, int level) {
        Employee temp = new Employee(name, level);
        this.staffHireList.add(temp);
        return temp;
    }

    public ArrayList<Employee> getStaffList() { return this.staffList; }
    public ArrayList<Employee> getStaffHireList() { return this.staffHireList; }

    @Override
    public void saveToFile() throws IOException {

    }
}
