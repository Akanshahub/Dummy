package com.example.sqldemo3;

public class CustomerModel {
    private int id;
    private String Name;
    private int Age;
    private boolean isActive;

// constructor

    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        Name = name;
        Age = age;
        this.isActive = isActive;


    }
    public CustomerModel(){

    }

    //toString is necessary for printing the content of the class object

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", isActive=" + isActive +
                '}';
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
