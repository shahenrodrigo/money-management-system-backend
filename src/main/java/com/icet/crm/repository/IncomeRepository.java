package com.icet.crm.repository;

import com.icet.crm.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface IncomeRepository extends JpaRepository<IncomeEntity, Integer> {

    List<IncomeEntity> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(i.amount) FROM IncomeEntity i WHERE i.user.id = :userId")
    Double sumAllAmountByUserId(@Param("userId") Long userId);

    Optional<IncomeEntity> findFirstByUserIdOrderByDateDesc(Long userId);

    List<IncomeEntity> findByUserId(Long userId);

}

