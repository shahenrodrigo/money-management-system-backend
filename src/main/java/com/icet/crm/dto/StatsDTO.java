package com.icet.crm.dto;

import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.IncomeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StatsDTO {

    private Double income;
    private Double expense;

    private IncomeEntity latestIncome;
    private ExpenseEntity latestExpense;

    private Double balance;
    private Double minExpense;
    private Double maxExpense;
    private Double minIncome;
    private Double maxIncome;

}
