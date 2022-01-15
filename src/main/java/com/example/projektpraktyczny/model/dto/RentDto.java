package com.example.projektpraktyczny.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private LocalDate dateOfRent;
    private String comments;
    private String worker;
}
