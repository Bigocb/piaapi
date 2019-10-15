package com.cloutier.piaapi.weather;

import com.cloutier.piaapi.data.DailyDataRepository;
import com.cloutier.piaapi.data.DailyDataResponse;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Repository
public class WeatherRepository {

    private final DSLContext jooq;

    @Autowired
    private DailyDataRepository dailyDataRepository;

    @Autowired
    public WeatherRepository(DSLContext jooq, DailyDataRepository dailyDataRepository) {
        this.jooq = jooq;
        this.dailyDataRepository = dailyDataRepository;
    }

    public UserWeatherPrefs getUserWeather(int userId) {
        UserWeatherPrefs weatherResponse =
                jooq.select().from("userprefs")
                .fetchOneInto(UserWeatherPrefs.class);
        return weatherResponse;
    }

    public OutboundWeatherResponse addWeather(InboundWeatherResponse weatherResponse) {
        jooq.insertInto(
                table("weather"),
                field("userId"),
                field("time"),
                field("summary"),
                field("temperature")
        ).values(
                1,
                weatherResponse.getCurrently().getTime(),
                weatherResponse.getCurrently().getSummary(),
                weatherResponse.getCurrently().getTemperature()
        ).execute();

        OutboundWeatherResponse outboundWeatherResponse = dailyDataRepository.getLatestWeather();

        return outboundWeatherResponse;

    }
}
