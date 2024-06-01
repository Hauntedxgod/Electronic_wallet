package com.example.electronic_wallet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowMeDto {

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    private String email;

    @Min(value = 1 , message = "Age should be more than 1")
    private Integer age;
}
