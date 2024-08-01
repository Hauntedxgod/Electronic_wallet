package com.example.electronic_wallet.repositories;

import com.example.electronic_wallet.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Card , Long> {

    List<Card> findByThisPerson(String t);
    Optional<Card> findByNumberCard(String a);

}
