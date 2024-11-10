package com.icet.crm.controller;



//import com.example.expensetracker.dto.IncomeDTO;
//import com.example.expensetracker.entity.Income;
//import com.example.expensetracker.service.IncomeService;
import com.icet.crm.dto.ExpenseDTO;
import com.icet.crm.dto.IncomeDTO;
import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.IncomeEntity;
import com.icet.crm.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
@CrossOrigin

public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public IncomeEntity addIncome(@RequestBody IncomeDTO incomeDTO) {
        return incomeService.addIncome(incomeDTO);
    }

    @GetMapping("/all")
    public List<IncomeEntity> getAllIncome() {
        return incomeService.getAllIncomes();
    }

    @GetMapping("/{id}")
    public IncomeEntity getIncomeById(@PathVariable Integer id) {
        return incomeService.getIncomeById(id);
    }

    @PutMapping("/{id}")
    public IncomeEntity updateIncome(@PathVariable Integer id, @RequestBody IncomeDTO incomeDTO) {
        return incomeService.updateIncome(id, incomeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Integer id) {
        incomeService.deleteIncome(id);
    }
}

