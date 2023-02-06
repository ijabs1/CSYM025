package com.example.csym025;

public class propertySpecsDataClass {
    private String id;
    private String listed;
    private String bedroom;
    private String bathroom;
    private String rent;
    private String size;
    private String post;
    private String latitude;
    private String longitude;
    private String furnish;
    private String type;
    private String garden;
    private String available;

    public propertySpecsDataClass(String id, String listed, String bedroom, String bathroom, String rent, String size, String post, String latitude, String longitude, String furnish, String type, String garden, String available) {
        this.id = id;
        this.listed = listed;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.rent = rent;
        this.size = size;
        this.post = post;
        this.latitude = latitude;
        this.longitude = longitude;
        this.furnish = furnish;
        this.type = type;
        this.garden = garden;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getListed() {
        return listed;
    }

    public String getBedroom() {
        return bedroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public String getRent() {
        return rent;
    }

    public String getSize() {
        return size;
    }

    public String getPost() {
        return post;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getFurnish() {
        return furnish;
    }

    public String getType() {
        return type;
    }

    public String getGarden() {
        return garden;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
