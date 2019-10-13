package com.cloutier.piaapi.data;

public class DailyDataResponse {

    private int userId;
    private int time;
    private String summary;
    private int temperature;

    public int getUserId() {
        return userId;
    }

    public void setEmail(int userId) {
        this.userId = userId;
    }

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
