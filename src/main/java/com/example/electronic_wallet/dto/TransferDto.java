package com.example.electronic_wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private String numberFromCard;

    private String numberToCard;

    private Trans trans;

    private String currency;
}

