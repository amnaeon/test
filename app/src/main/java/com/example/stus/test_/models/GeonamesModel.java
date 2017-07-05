package com.example.stus.test_.models;

public class GeonamesModel {
    private String summary ="";
    private String thumbnailImg ="";
    private String wikipediaUrl = "";


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public String getSummary() {
        return summary;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }
}
