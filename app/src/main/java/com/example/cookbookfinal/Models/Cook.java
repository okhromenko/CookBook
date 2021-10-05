package com.example.cookbookfinal.Models;

public class Cook {
    private String name, shortDescription, description, image, time, level, color, category;
    private Boolean release;

    public Cook(){}

    public Cook(String name, String shortDescription, String description, String image, String time,
                String level, Boolean release, String color, String category) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.image = image;
        this.time = time;
        this.level = level;
        this.release = release;
        this.color = color;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getRelease() {
        return release;
    }

    public void setRelease(Boolean release) {
        this.release = release;
    }
}
