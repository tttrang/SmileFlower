package com.example.tttra.smileflower.Model;

public class Flower {
    private String Name;
    private String Image;
    private String Price;
    private String Discount;
    private String FlowerId;
    private String Description;

    public Flower() {
    }

    public Flower(String name, String image, String price, String discount, String flowerId, String description) {
        Name = name;
        Image = image;
        Price = price;
        Discount = discount;
        FlowerId = flowerId;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getFlowerId() {
        return FlowerId;
    }

    public void setFlowerId(String flowerId) {
        FlowerId = flowerId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
