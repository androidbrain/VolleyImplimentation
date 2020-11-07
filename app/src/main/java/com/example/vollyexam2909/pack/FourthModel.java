package com.example.vollyexam2909.pack;

public class FourthModel {

    private String id;
    private String mobileno;
    private String title;
    private String text;
    private String imageurl;

    public FourthModel(String id, String mobileno, String title, String text, String imageurl) {
        this.id = id;
        this.mobileno = mobileno;
        this.title = title;
        this.text = text;
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
