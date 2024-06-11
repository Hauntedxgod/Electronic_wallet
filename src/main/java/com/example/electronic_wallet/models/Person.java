package com.example.electronic_wallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name", unique = true)
    private String name;

    @Min(value = 14 , message = "Age should be more than 1")
    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Size(min = 8 , message = "Min 8 chars , max 15 chars")
    @Column(name = "password")
    private String password;


//
//    @OneToMany()
//    private List<Card> cards;
}
