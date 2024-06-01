package com.example.electronic_wallet.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_Card")
    private String numberCard;

    @Column(name = "phone_Number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private double balance;

    @Column(name = "this_Person")
    private String thisPerson;

//    @Column()
//    private LocalDateTime createdAt;
//
//    @Column()
//    private LocalDateTime closedAt;
//
//    @ManyToOne
//    @JoinColumn()
//    private Person owner;
}
