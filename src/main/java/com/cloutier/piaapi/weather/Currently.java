package com.cloutier.piaapi.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currently {
    @JsonProperty("time")
    private int time;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("temperature")
    private int temperature;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}
