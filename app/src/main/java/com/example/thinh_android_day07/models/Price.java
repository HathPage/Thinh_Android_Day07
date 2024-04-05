package com.example.thinh_android_day07.models;

public class Price {
    int id;
    private double onion, lemon, potato, cucumber;

    public Price(int id, double onion, double lemon, double potato, double cucumber) {
        this.id = id;
        this.onion = onion;
        this.lemon = lemon;
        this.potato = potato;
        this.cucumber = cucumber;
    }
    public Price(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOnion() {
        return onion;
    }

    public void setOnion(double onion) {
        this.onion = onion;
    }

    public double getLemon() {
        return lemon;
    }

    public void setLemon(double lemon) {
        this.lemon = lemon;
    }

    public double getPotato() {
        return potato;
    }

    public void setPotato(double potato) {
        this.potato = potato;
    }

    public double getCucumber() {
        return cucumber;
    }

    public void setCucumber(double cucumber) {
        this.cucumber = cucumber;
    }
}
