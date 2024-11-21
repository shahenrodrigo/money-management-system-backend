package com.icet.crm.controller;

import com.icet.crm.dto.IncomeDTO;
import com.icet.crm.entity.IncomeEntity;
import com.icet.crm.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
@CrossOrigin

public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
//    public IncomeEntity addIncome(@RequestBody IncomeDTO incomeDTO) {
//        return incomeService.addIncome(incomeDTO);
//    }
    public ResponseEntity<IncomeEntity> addIncome(@RequestBody IncomeDTO incomeDTO) {
        try {
            IncomeEntity savedIncome = incomeService.addIncome(incomeDTO);
            return ResponseEntity.ok(savedIncome); // Ensure a 200 response with data
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 on error
        }
    }

    @GetMapping("/get-all/{userId}")
    public List<IncomeDTO> getIncomesByUserId(@PathVariable Long userId) {
        return incomeService.getIncomeByUserId(userId);
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

