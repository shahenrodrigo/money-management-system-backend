package com.icet.crm.controller;

import com.icet.crm.dto.ExpenseDTO;
import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@CrossOrigin

public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseEntity addExpense(@RequestBody ExpenseDTO expenseDTO) {
        return expenseService.addExpense(expenseDTO);
    }

    @GetMapping("/get-all/{userId}")
    public List<ExpenseDTO> getExpensesByUserId(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    @GetMapping("/{id}")
    public ExpenseEntity getExpenseById(@PathVariable Integer id) {
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public ExpenseEntity updateExpense(@PathVariable Integer id, @RequestBody ExpenseDTO expenseDTO) {
        return expenseService.updateExpense(id, expenseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
    }
}

