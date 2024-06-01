package com.example.electronic_wallet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {



    @Min(value = 11, message = "phoneNumber not found")
    private String phoneNumber;

    @Size(max = 5 , message = "max 5 chars")
    private String password;


}
