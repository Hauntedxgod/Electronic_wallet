package com.example.electronic_wallet.kafka;

import com.example.electronic_wallet.models.CurrencyRate;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boot;


    @Bean
    public ConsumerFactory<String, CurrencyRate> consumerFactory(){
        Map<String , Object> props = new HashMap<>();

        JsonDeserializer<CurrencyRate> deserialize = new JsonDeserializer<>(CurrencyRate.class);
        deserialize.setRemoveTypeHeaders(false);
        deserialize.addTrustedPackages("*");
        deserialize.setUseTypeMapperForKey(true);


        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , boot);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , deserialize);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG , 30000);


        return new DefaultKafkaConsumerFactory<>(props , new StringDeserializer() , deserialize);
    }

}
