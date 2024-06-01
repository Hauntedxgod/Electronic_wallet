package com.example.electronic_wallet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private String numberFromCard;

    private String numberToCard;

    private Trans trans;
}

