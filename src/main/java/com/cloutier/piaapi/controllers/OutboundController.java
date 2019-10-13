package com.cloutier.piaapi.controllers;

import java.util.List;

import com.cloutier.piaapi.data.DailyDataRequest;
import com.cloutier.piaapi.data.DailyDataResponse;
import com.cloutier.piaapi.services.DailyDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OutboundController {

    @Autowired
    private DailyDataService dailyDataService;

    @PostMapping(path = "/daily")
    public List<DailyDataResponse> fetchDailyData(@RequestBody DailyDataRequest dailyDataRequest) {
        if (dailyDataRequest.getEmail() != null) {
            List<DailyDataResponse> dailyDataResponse = dailyDataService.getDailyData(dailyDataRequest);
        return dailyDataResponse;    
    } else { return null;}
    }

    @GetMapping(path = "/daily")
    public String getString (){
        return "gettest";
    }

}
