package com.example.covid.controller;

import com.example.covid.model.KeyValue;
import com.example.covid.service.CovidService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@AllArgsConstructor


@RestController
@RequestMapping("/api/v1")
public class CovidController {

    private final CovidService covidService;
    
    @PostMapping("/date")
    public String getQuarantineTime(@RequestBody KeyValue keyValue) {

        return covidService.getQuarantineTime(keyValue.getYear(), keyValue.getMonth(), keyValue.getDay());


    }


    @GetMapping("/time")
    public String getTime(){
        return CovidService.getTime();
    }


}
