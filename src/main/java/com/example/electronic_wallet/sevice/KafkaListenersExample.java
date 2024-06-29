package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.dto.CurrencyDto;
import com.example.electronic_wallet.models.CurrencyRate;
import com.example.electronic_wallet.repositories.CurrencyRateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.hibernate.service.spi.InjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.xml.crypto.Data;
import java.io.DataInput;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class KafkaListenersExample {


    private final CurrencyRateRepository currencyRateRepository;
    private final ModelMapper modelMapper;


    private final ObjectMapper objectMapper;
    private final CurrencyService currencyService;

    public KafkaListenersExample(CurrencyRateRepository currencyRateRepository, ModelMapper modelMapper, ObjectMapper objectMapper, CurrencyService currencyService) {
        this.currencyRateRepository = currencyRateRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.currencyService = currencyService;
    }


    @KafkaListener(topics = "${stock.topic-name}" , groupId = "myGroup")
    public void listener(String currencyRate) throws IOException {
//
//        try {
//            CurrencyDto currencyRate1 = modelMapper.map(currencyRate , CurrencyDto.class);
//            currencyRateRepository.save(modelMapper.map(currencyRate1 , CurrencyRate.class));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        CurrencyDto currencyDto = objectMapper.readValue(currencyRate, CurrencyDto.class);
        currencyService.currencySave(modelMapper.map(currencyRate , CurrencyDto.class));
//        currencyRateRepository.save(modelMapper.map(currencyDto , CurrencyRate.class));
//            currencyService.currencySave(currencyRate1);

//        currencyService.currencySave(currencyRates);
    }

}
