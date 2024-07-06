package com.example.electronic_wallet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_card_from")
    private String numberCardFrom;

    @Column(name = "number_card_to")
    private String numberCardTo;

    @Column(name = "person_name_from")
    private String personFromName;

    @Column(name = "person_name_to")
    private String personToName;

    @Column(name = "value")
    private double value;

    @Column(name = "transaction_time")
    private LocalDateTime createdAt;

    @Column(name = "currency")
    private String currency;

    @Column(name = "course")
    private BigDecimal course;


}
