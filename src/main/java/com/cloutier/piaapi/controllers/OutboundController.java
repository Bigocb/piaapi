package com.cloutier.piaapi.controllers;

import com.cloutier.piaapi.data.DailyDataRequest;
import com.cloutier.piaapi.news.NewsService;
import com.cloutier.piaapi.news.OutboundNewsResponse;
import com.cloutier.piaapi.services.DailyDataService;

import com.cloutier.piaapi.weather.OutboundWeatherResponse;
import com.cloutier.piaapi.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutboundController {

    private final DailyDataService dailyDataService;
    private final NewsService newsService;
    private final WeatherService weatherService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public OutboundController(DailyDataService dailyDataService, NewsService newsService, WeatherService weatherService, SimpMessagingTemplate simpMessagingTemplate) {
        this.dailyDataService = dailyDataService;
        this.newsService = newsService;
        this.weatherService = weatherService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OutboundWeatherResponse fetchDailyData(@RequestBody DailyDataRequest dailyDataRequest) {
        if (dailyDataRequest.getEmail() != null) {
             return dailyDataService.getDailyData(dailyDataRequest);
    } else { return null;}
    }

    @SubscribeMapping("/topic/news")
    @GetMapping("todos")
    public OutboundNewsResponse getAllNewsStories() {
        List<OutboundNewsResponse> newsResponse = newsService.getAllNews();
        for (int i = 0; i < newsResponse.size(); i++) {
            this.simpMessagingTemplate.convertAndSend("/topic/news", newsResponse.get(i));
        }
        return null;
    }

    @SubscribeMapping("/topic/weather")
    public OutboundWeatherResponse getWeather() {
        OutboundWeatherResponse weatherResponse = weatherService.getLastWeather();
        return weatherResponse;
    }
}
