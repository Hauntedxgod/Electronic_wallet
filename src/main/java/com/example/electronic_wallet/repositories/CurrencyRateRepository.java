package com.example.electronic_wallet.repositories;

import com.example.electronic_wallet.models.Card;
import com.example.electronic_wallet.models.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository  extends JpaRepository<CurrencyRate , Long> {

    ArrayList<CurrencyRate> findByCurrency (String a);

}
