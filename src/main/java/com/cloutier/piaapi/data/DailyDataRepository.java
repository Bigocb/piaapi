package com.cloutier.piaapi.data;

import java.util.List;

import com.cloutier.piaapi.weather.OutboundWeatherResponse;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.max;

@Repository
public class DailyDataRepository {

    private final DSLContext jooq;

    @Autowired
    public DailyDataRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public OutboundWeatherResponse getLatestWeather() {
        Result<Record1<Integer>> time = jooq.select(max(field("time")).cast(Integer.class).as("time")).from("weather").fetch();

        Integer i = (Integer) time.getValue(0,field("time"));
        OutboundWeatherResponse dailyDataResponse =
        jooq.select(field("userId"),
                field("time"),
                field("summary"),
                field("temperature")
                )
        .from("weather")
                .where(field("userId").eq(1)).and(field("time").eq(i))
        .fetchOneInto(OutboundWeatherResponse.class);

        return dailyDataResponse;
    }

    
}