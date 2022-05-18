package com.getir.readingisgood.controller;


import com.getir.readingisgood.input.StatisticModel;
import com.getir.readingisgood.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
@Api(value = "Statistics Controller Documentation")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping(value = "/{customerId}", produces = "application/json")
    @ApiOperation(value = "Get Statistics Method")
    public ResponseEntity<Object> getStatistics(@PathVariable("email") String customerId) {

        final Collection<StatisticModel> statistics = statisticsService.getStatistics(customerId);

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

}
