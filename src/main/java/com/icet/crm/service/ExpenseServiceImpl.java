package com.icet.crm.service;

import com.icet.crm.dto.ExpenseDTO;
import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.UserEntity;
import com.icet.crm.repository.ExpenseRepository;
import com.icet.crm.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Override
    public ExpenseEntity addExpense(ExpenseDTO expenseDTO) {

        UserEntity user = userRepository.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for ID: " + expenseDTO.getUserId()));

        ExpenseEntity expense = new ExpenseEntity();
        expense.setTitle(expenseDTO.getTitle());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setUser(user);
        return expenseRepository.save(expense);

    }

    @Override
    public List<ExpenseDTO> getExpensesByUserId(Long userId) {

        List<ExpenseEntity> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(expense -> mapper.map(expense, ExpenseDTO.class))
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

    public ExpenseEntity getExpenseById(Integer id) {

        Optional<ExpenseEntity> optionalExpenseEntity = expenseRepository.findById(id);
        if (optionalExpenseEntity.isPresent()) {
            return optionalExpenseEntity.get();
        } else {
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }
}

