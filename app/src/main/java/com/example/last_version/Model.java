package com.example.last_version;

public class Model {

String title;
    String img;
    String desc;
    String time;
    String date;

    public String getLocation() {
        return location;
    }

    String location;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;
    String by;

    int color;


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Model(){this.color=color;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
