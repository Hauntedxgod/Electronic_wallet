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
import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaListenersExample {

    private final ObjectMapper objectMapper;
    private final CurrencyService currencyService;

    public KafkaListenersExample(ObjectMapper objectMapper, CurrencyService currencyService) {
        this.objectMapper = objectMapper;
        this.currencyService = currencyService;
    }


    @KafkaListener(topics = "${stock.topic-name}", groupId = "myGroup")
    public void listener(String currencyRate) throws IOException {
        ArrayList<CurrencyDto> currencyDto = objectMapper.readValue(currencyRate, ArrayList.class);
        currencyService.currencySave(currencyDto);
    }
}
