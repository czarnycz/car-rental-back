package com.example.projektpraktyczny.model.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserDto {
    private Long id;
    private String username;
    private boolean admin;
}