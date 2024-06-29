package com.example.electronic_wallet.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@Value
public class CurrencyDto {

    String currency;

    BigDecimal rate;
}
