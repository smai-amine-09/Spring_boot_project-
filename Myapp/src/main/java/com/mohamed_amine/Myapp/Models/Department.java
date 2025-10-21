package com.mohamed_amine.Myapp.Models;

public class Department {
    private String departmentID;
    private String name;
    private String branch;
    private Position Positoin;

    public String getDepartmentID() {
        return departmentID;
    }

    public Department(String departmentID, String name, String branch, Position positoin) {
        this.departmentID = departmentID;
        this.name = name;
        this.branch = branch;
        Positoin = positoin;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Position getPositoin() {
        return Positoin;
    }

    public void setPositoin(Position positoin) {
        Positoin = positoin;
    }
}
