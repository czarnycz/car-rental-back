package com.example.projektpraktyczny.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterApplicationUserDto {
    private String username;
    private String password;
    private boolean admin;
}