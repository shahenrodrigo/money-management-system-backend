package com.icet.crm.service;


import com.icet.crm.dto.ExpenseDTO;
import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseEntity addExpense(ExpenseDTO expenseDTO) {

        ExpenseEntity expense = new ExpenseEntity();
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        return expenseRepository.save(expense);

    }

    @Override
    public List<ExpenseEntity> getAllExpenses() {

//        return expenseRepository.findAll();
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(ExpenseEntity::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseEntity updateExpense(Integer id, ExpenseDTO expenseDTO) {
        ExpenseEntity expense = expenseRepository.findById(id).orElseThrow();
        expense.setTitle(expenseDTO.getTitle());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Integer id) {

        expenseRepository.deleteById(id);

    }

    public ExpenseEntity getExpenseById(Integer id){

        Optional<ExpenseEntity> optionalExpenseEntity = expenseRepository.findById(id);
        if (optionalExpenseEntity.isPresent()) {
            return optionalExpenseEntity.get();
        }else {
            throw new EntityNotFoundException("Expense is not present with id "+ id);
        }

    }
}

