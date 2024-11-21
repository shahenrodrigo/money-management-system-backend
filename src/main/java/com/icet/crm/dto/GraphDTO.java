package com.icet.crm.dto;

import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.IncomeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GraphDTO {

    private List<ExpenseEntity> expenseEntityList;
    private List<IncomeEntity> incomeEntityList;

}
