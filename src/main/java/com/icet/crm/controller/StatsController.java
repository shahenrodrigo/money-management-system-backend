package com.icet.crm.controller;

import com.icet.crm.dto.GraphDTO;
import com.icet.crm.dto.StatsDTO;
import com.icet.crm.service.impl.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin

public class StatsController {

    private final StatsService statsService;

    @GetMapping("/chart/{userId}")
    public GraphDTO getChartData(@PathVariable Long userId) {
        return statsService.getChartData(userId); // Pass userId for filtering data
    }

    @GetMapping("/{userId}")
    public StatsDTO getStats(@PathVariable Long userId) {
        return statsService.getStats(userId); // Pass userId for filtering data
    }
}
