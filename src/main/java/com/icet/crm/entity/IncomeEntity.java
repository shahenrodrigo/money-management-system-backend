package com.icet.crm.entity;

import com.icet.crm.dto.IncomeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class IncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String category;
    private String description;
    private double amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public IncomeDTO getIncomeDto(){

        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(id);
        incomeDTO.setTitle(title);
        incomeDTO.setAmount(amount);
        incomeDTO.setCategory(category);
        incomeDTO.setDescription(description);
        incomeDTO.setDate(date);

        return incomeDTO;
    }
}
