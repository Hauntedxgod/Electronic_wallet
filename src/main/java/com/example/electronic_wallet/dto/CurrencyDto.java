package com.example.electronic_wallet.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CurrencyDto {

    public String currency;

    public BigDecimal rate;
}
