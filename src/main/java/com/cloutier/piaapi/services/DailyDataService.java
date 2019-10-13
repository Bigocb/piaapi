package com.cloutier.piaapi.services;

import java.util.List;

import com.cloutier.piaapi.data.DailyDataRepository;
import com.cloutier.piaapi.data.DailyDataRequest;
import com.cloutier.piaapi.data.DailyDataResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyDataService {

    @Autowired
    private DailyDataRepository dailyDataRepository;

    public DailyDataService(DailyDataRepository dailyDataRepository) {
        this.dailyDataRepository = dailyDataRepository;
    }

    public DailyDataResponse getDailyData(DailyDataRequest dailyDataRequest) {
        if (dailyDataRequest.getEmail() != null) {

            DailyDataResponse dailyDataResponse = dailyDataRepository.getLatestWeather();

             return dailyDataResponse;
        } else {
            return null;
        }

    }

}
