package com.kelompok4.wecare.model;

public class HealthEducationModel {

    private String title;
    private String description;
    private String content;
    private String writer;
    private String imgUrl;

    public HealthEducationModel(String title, String description, String content, String writer, String imgUrl) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.writer = writer;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
