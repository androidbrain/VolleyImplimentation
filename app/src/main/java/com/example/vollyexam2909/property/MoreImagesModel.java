package com.example.vollyexam2909.property;

public class MoreImagesModel {
    private String ImgUrl;
    private String title;

    public MoreImagesModel(String imgUrl, String title) {
        ImgUrl = imgUrl;
        this.title = title;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
