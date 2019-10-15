package com.cloutier.piaapi.news;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Articles {

    private String feed;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    private String author;
    private String title;
    private String publishedAt;
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishat() {
        return publishedAt;
    }

    public void setPublishat(String publishat) {
        this.publishedAt = publishat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
