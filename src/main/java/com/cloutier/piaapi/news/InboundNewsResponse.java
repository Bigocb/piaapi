package com.cloutier.piaapi.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InboundNewsResponse  {

    @JsonProperty("articles")
    private List<Articles> articles;

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
