package com.cloutier.piaapi.controllers;

import com.cloutier.piaapi.data.DailyDataRequest;
import com.cloutier.piaapi.data.DailyDataResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutboundController {

    @Autowired
    private DailyDataService dailyDataService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OutboundWeatherResponse fetchDailyData(@RequestBody DailyDataRequest dailyDataRequest) {
        if (dailyDataRequest.getEmail() != null) {
             return dailyDataService.getDailyData(dailyDataRequest);
    } else { return null;}
    }

    @SubscribeMapping("/topic/news")
    public OutboundNewsResponse getAllNewsStories() {
        List<OutboundNewsResponse> newsResponse = newsService.getAllNews();
        for (int i = 0; i < newsResponse.size(); i++) {
            this.simpMessagingTemplate.convertAndSend("/topic/news", newsResponse.get(i));
        }
        return null;
    }

    @SubscribeMapping("/topic/greetings")
    public OutboundWeatherResponse getWeather() {
        OutboundWeatherResponse weatherResponse = weatherService.getLastWeather();
        return weatherResponse;
    }
}
