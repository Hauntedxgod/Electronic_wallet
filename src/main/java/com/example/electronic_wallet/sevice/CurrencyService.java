package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.dto.CurrencyDto;
import com.example.electronic_wallet.models.CurrencyRate;
import com.example.electronic_wallet.models.Transaction;
import com.example.electronic_wallet.repositories.CurrencyRateRepository;
import com.example.electronic_wallet.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
public class CurrencyService {

    private final CurrencyRateRepository currencyRateRepository;
    private final ModelMapper modelMapper;
    public CurrencyService(CurrencyRateRepository currencyRateRepository,ModelMapper modelMapper) {
        this.currencyRateRepository = currencyRateRepository;
        this.modelMapper = modelMapper;
    }


    public void currencySave(ArrayList<CurrencyDto> currencyDto){
        for (int i = 0; i  < currencyDto.size() ; i++) {
            CurrencyRate currencyRate = modelMapper.map(currencyDto.get(i), CurrencyRate.class);
            currencyRate.setLocalDateTime(LocalDateTime.now());
            CurrencyRate currencyRate1 = currencyRateRepository.save(currencyRate);
            log.info("Currency{}" , currencyRate1 );
        }
    }


}
