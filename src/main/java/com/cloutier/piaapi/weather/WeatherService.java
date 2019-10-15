package com.cloutier.piaapi.weather;

import com.cloutier.piaapi.data.DailyDataRepository;
import com.cloutier.piaapi.data.DailyDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private DailyDataRepository dailyDataRepository;

    public WeatherService(WeatherRepository weatherRepository, SimpMessagingTemplate simpMessagingTemplate, DailyDataRepository dailyDataRepository) {
        this.weatherRepository = weatherRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.dailyDataRepository = dailyDataRepository;
    }

    public int getDarkSky()
    {
        UserWeatherPrefs userWeatherPrefs = weatherRepository.getUserWeather(1);

        final String uri = "https://api.darksky.net/forecast/52e988771e6bb4e58ac3cabcedabb213/"
                + userWeatherPrefs.getLat() + "," + userWeatherPrefs.getLongitude();

        RestTemplate restTemplate = new RestTemplate();
        InboundWeatherResponse result = restTemplate.getForObject(uri, InboundWeatherResponse.class);

        OutboundWeatherResponse dailyDataResponse = weatherRepository.addWeather(result);
        this.simpMessagingTemplate.convertAndSend("/topic/greetings", dailyDataResponse);

        System.out.println(result);
        return 1;
    }

    public OutboundWeatherResponse getLastWeather()
    {
      OutboundWeatherResponse weatherResponse =  dailyDataRepository.getLatestWeather();
      return weatherResponse;
    }
}
