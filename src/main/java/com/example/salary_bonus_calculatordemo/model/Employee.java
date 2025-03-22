package com.example.salary_bonus_calculatordemo.model;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Employee {
    private int id;
    private String userName;
    private String position;
    private int branchId; // Added branchId field
    private BooleanProperty attendance;
    private DoubleProperty bonus = new SimpleDoubleProperty();

    public double getBonus() {
        return bonus.get();
    }

    public void setBonus(double bonus) {
        this.bonus.set(bonus);
    }

    public DoubleProperty bonusProperty() {
        return bonus;
    }




    public Employee(int id, String userName, String position, int branchId, boolean attendance) {
        this.id = id;
        this.userName = userName;
        this.position = position;
        this.branchId = branchId; // Initialize branchId
        this.attendance = new SimpleBooleanProperty(attendance);
    }

    public Employee(int id, int branchId, String userName, String position) {
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPosition() {
        return position;
    }

    public int getBranchId() { // Fixed method
        return branchId;
    }

    public void setBranchId(int branchId) { // Added setter
        this.branchId = branchId;
    }

    public BooleanProperty attendanceProperty() {
        return attendance;
    }

    public boolean isAttendance() {
        return attendance.get();
    }

    public void setAttendance(boolean attendance) {
        this.attendance.set(attendance);
    }


    public void setId(int id) {
        this.id = id;
    }



    public void setPosition(String position) {
        this.position = position;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
