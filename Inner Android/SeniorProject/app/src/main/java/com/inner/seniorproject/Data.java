package com.inner.seniorproject;

public class Data {

    private String name;
    private  String company;
    private String imagePath;
    private String address;

    public Data(String imagePath, String name, String company, String address) {
        this.imagePath = imagePath;
        this.name = name;
        this.company = company;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {return company; }

    public String getImagePath() {
        return imagePath;
    }

    public String getAddress() { return address; }
}
