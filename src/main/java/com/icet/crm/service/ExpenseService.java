package com.icet.crm.service;





import com.icet.crm.dto.ExpenseDTO;
import com.icet.crm.entity.ExpenseEntity;

import java.util.List;

public interface ExpenseService {

    ExpenseEntity addExpense(ExpenseDTO expenseDTO);

    List<ExpenseEntity> getAllExpenses();

    ExpenseEntity updateExpense(Integer id, ExpenseDTO expenseDTO);

    void deleteExpense(Integer id);

    ExpenseEntity getExpenseById(Integer id);
}

