package com.icet.crm.service;

import com.icet.crm.dto.IncomeDTO;
import com.icet.crm.entity.IncomeEntity;
import com.icet.crm.entity.UserEntity;
import com.icet.crm.repository.IncomeRepository;
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

public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Override
    public IncomeEntity addIncome(IncomeDTO incomeDTO) {

        UserEntity user = userRepository.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for ID: " + incomeDTO.getUserId()));

        // Map IncomeDTO to IncomeEntity
        IncomeEntity income = new IncomeEntity();
        income.setTitle(incomeDTO.getTitle());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        income.setUser(user);
        return incomeRepository.save(income);


    }

    @Override
    public List<IncomeDTO> getIncomeByUserId(Long userId) {
        List<IncomeEntity> incomes = incomeRepository.findByUserId(userId);
        return incomes.stream()
                .map(income -> mapper.map(income, IncomeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public IncomeEntity updateIncome(Integer id, IncomeDTO incomeDTO) {

        IncomeEntity income = incomeRepository.findById(id).orElseThrow();
        income.setTitle(incomeDTO.getTitle());
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

