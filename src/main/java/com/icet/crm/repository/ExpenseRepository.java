package com.icet.crm.repository;

import com.icet.crm.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {

    List<ExpenseEntity> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e WHERE e.user.id = :userId")
    Double sumAllAmountByUserId(@Param("userId") Long userId);

    Optional<ExpenseEntity> findFirstByUserIdOrderByDateDesc(Long userId);

    List<ExpenseEntity> findByUserId(Long userId);

}


