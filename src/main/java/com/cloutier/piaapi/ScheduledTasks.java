package com.cloutier.piaapi;

import com.cloutier.piaapi.news.NewsService;
import com.cloutier.piaapi.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private NewsService newsService;

    public ScheduledTasks(WeatherService weatherService, NewsService newsService) {
        this.weatherService = weatherService;
        this.newsService = newsService;
    }

    @Scheduled(fixedRate = 600000)
    public void scheduleTaskWithFixedRate() {
        weatherService.getDarkSky();
    }

    @Scheduled(fixedRate = 600000)
    public void getNewsTask() {
        newsService.getNews();
    }

}
