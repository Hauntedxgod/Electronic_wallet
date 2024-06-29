package com.example.electronic_wallet.repositories;

import com.example.electronic_wallet.models.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository  extends JpaRepository<CurrencyRate , Long> {
}
