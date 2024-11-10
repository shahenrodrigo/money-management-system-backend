package com.icet.crm.service.stats;

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
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseEntityList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDTO.setIncomeEntityList(incomeRepository.findByDateBetween(startDate,endDate));

        return graphDTO;
    }

    public StatsDTO getStats(){

        Double totalIncome = incomeRepository.sumAllAmount();
        Double totalExpense = expenseRepository.sumAllAmount();

        Optional<ExpenseEntity> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();
        Optional<IncomeEntity> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome-totalExpense);

        List<IncomeEntity> incomeList = incomeRepository.findAll();
        List<ExpenseEntity> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(IncomeEntity::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(IncomeEntity::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(ExpenseEntity::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(ExpenseEntity::getAmount).max();

        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);

        return statsDTO;
    }
}
