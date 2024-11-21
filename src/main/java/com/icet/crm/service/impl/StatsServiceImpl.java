package com.icet.crm.service.impl;

import com.icet.crm.dto.GraphDTO;
import com.icet.crm.dto.StatsDTO;
import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.IncomeEntity;
import com.icet.crm.repository.ExpenseRepository;
import com.icet.crm.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public GraphDTO getChartData(Long userId) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseEntityList(expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate));
        graphDTO.setIncomeEntityList(incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate));

        return graphDTO;
    }

    @Override
    public StatsDTO getStats(Long userId) {

        // Get total income and expense
        Double totalIncome = incomeRepository.sumAllAmountByUserId(userId);
        Double totalExpense = expenseRepository.sumAllAmountByUserId(userId);

        // Initialize StatsDTO
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setIncome(totalIncome != null ? totalIncome : 0);
        statsDTO.setExpense(totalExpense != null ? totalExpense : 0);
        statsDTO.setBalance((totalIncome != null ? totalIncome : 0) - (totalExpense != null ? totalExpense : 0));

        // Get latest income and expense
        statsDTO.setLatestIncome(incomeRepository.findFirstByUserIdOrderByDateDesc(userId).orElse(null));
        statsDTO.setLatestExpense(expenseRepository.findFirstByUserIdOrderByDateDesc(userId).orElse(null));

        // Get all income and expense data for min/max calculations
        List<IncomeEntity> incomeList = incomeRepository.findByUserId(userId);
        List<ExpenseEntity> expenseList = expenseRepository.findByUserId(userId);

        // Calculate min and max income
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(IncomeEntity::getAmount).max();
        OptionalDouble minIncome = incomeList.stream().mapToDouble(IncomeEntity::getAmount).min();

        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : 0);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : 0);

        // Calculate min and max expense
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(ExpenseEntity::getAmount).max();
        OptionalDouble minExpense = expenseList.stream().mapToDouble(ExpenseEntity::getAmount).min();

        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : 0);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : 0);

        return statsDTO;
    }
}
