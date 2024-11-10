package com.icet.crm.repository;

import com.icet.crm.entity.ExpenseEntity;
import com.icet.crm.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {

    List<ExpenseEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e")
    Double sumAllAmount();

    Optional<ExpenseEntity> findFirstByOrderByDateDesc();
}
