package com.example.thinh_android_day07.models;

public class Item {
    int img;
    private String name;
    private double quantity, price;

    public Item(int img, String name, double quantity, double price) {
        this.img = img;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Item() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
