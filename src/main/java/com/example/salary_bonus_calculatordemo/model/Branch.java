package com.example.salary_bonus_calculatordemo.model;

public class Branch {

    private int id;
    private String branchName;

    // Constructor
    public Branch(int id, String branchName) {
        this.id = id;
        this.branchName = branchName;
    }

    // Default constructor
    public Branch() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", branchName='" + branchName + '\'' +
                '}';
    }

}
