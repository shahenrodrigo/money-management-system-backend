package com.icet.crm.service.stats;

import com.icet.crm.dto.GraphDTO;
import com.icet.crm.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();

    StatsDTO getStats();
}
