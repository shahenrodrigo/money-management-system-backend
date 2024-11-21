package com.icet.crm.service;

import com.icet.crm.dto.IncomeDTO;
import com.icet.crm.entity.IncomeEntity;

import java.util.List;

public interface IncomeService {

    IncomeEntity addIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getIncomeByUserId(Long userId);

    IncomeEntity updateIncome(Integer id, IncomeDTO incomeDTO);

    void deleteIncome(Integer id);

    IncomeEntity getIncomeById(Integer id);
}
