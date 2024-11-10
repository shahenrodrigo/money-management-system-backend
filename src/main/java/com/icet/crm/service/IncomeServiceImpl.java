package com.icet.crm.service;

import com.icet.crm.dto.IncomeDTO;
import com.icet.crm.dto.StatsDTO;
import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.IncomeEntity;
import com.icet.crm.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public IncomeEntity addIncome(IncomeDTO incomeDTO) {

        IncomeEntity income = new IncomeEntity();
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        return incomeRepository.save(income);
    }

    @Override
    public List<IncomeEntity> getAllIncomes() {

        return incomeRepository.findAll();
    }

    @Override
    public IncomeEntity updateIncome(Integer id, IncomeDTO incomeDTO) {

        IncomeEntity income = incomeRepository.findById(id).orElseThrow();
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        return incomeRepository.save(income);
    }

    @Override
    public void deleteIncome(Integer id) {

        incomeRepository.deleteById(id);
    }

    @Override
    public IncomeEntity getIncomeById(Integer id) {

        Optional<IncomeEntity> optionalIncomeEntity = incomeRepository.findById(id);
        if (optionalIncomeEntity.isPresent()) {
            return optionalIncomeEntity.get();
        } else {
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }

}

