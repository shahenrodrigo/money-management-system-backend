package com.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IncomeDTO {

    private Integer id;
    private String title;
    private String category;
    private String description;
    private double amount;
    private LocalDate date;
    private Long userId;
}


