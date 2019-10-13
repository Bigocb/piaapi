package com.cloutier.piaapi.data;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DailyDataRepository {

    private final DSLContext jooq;

    @Autowired
    public DailyDataRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public DailyDataResponse getLatestWeather() {
        DailyDataResponse dailyDataResponse = 
        jooq.select()
        .from("weather")
        .fetchOneInto(DailyDataResponse.class);

        return dailyDataResponse;
    }

    
}