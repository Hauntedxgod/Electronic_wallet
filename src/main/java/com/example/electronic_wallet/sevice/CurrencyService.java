package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.dto.CurrencyDto;
import com.example.electronic_wallet.models.CurrencyRate;
import com.example.electronic_wallet.repositories.CurrencyRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrencyService {

    private final CurrencyRateRepository currencyRateRepository;

    private final ModelMapper modelMapper;
    public CurrencyService(CurrencyRateRepository currencyRateRepository, ModelMapper modelMapper) {
        this.currencyRateRepository = currencyRateRepository;
        this.modelMapper = modelMapper;
    }


    public void currencySave(CurrencyDto currencyDto){
        CurrencyRate currencyRate = modelMapper.map(currencyDto , CurrencyRate.class);
        CurrencyRate currencyRate1 = currencyRateRepository.save(currencyRate);
        log.info("Currency{}" , currencyRate1 );
    }
}
