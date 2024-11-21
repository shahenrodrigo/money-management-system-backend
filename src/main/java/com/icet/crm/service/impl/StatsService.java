package com.icet.crm.service.impl;

import com.icet.crm.dto.GraphDTO;
import com.icet.crm.dto.StatsDTO;

public interface StatsService {
    GraphDTO getChartData(Long userId); // Fetch user-specific chart data
    StatsDTO getStats(Long userId);     // Fetch user-specific stats
}

