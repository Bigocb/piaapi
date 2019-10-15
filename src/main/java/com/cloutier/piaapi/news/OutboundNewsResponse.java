package com.cloutier.piaapi.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OutboundNewsResponse {

    private UUID feed;
    private String source;
    private String author;
    private String title;
    private String publishat;
    private String content;

    public UUID getFeed() {
        return feed;
    }

    public void setFeed(UUID feed) {
        this.feed = feed;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

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
        return publishat;
    }

    public void setPublishat(String publishat) {
        this.publishat = publishat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
