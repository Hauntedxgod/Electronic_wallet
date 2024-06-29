//package com.example.electronic_wallet.conrollers;
//
//import com.example.electronic_wallet.models.Currency;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/c")
//public class CurrencyController {
//    private final Producer producer;
//
//    public CurrencyController(Producer producer) {
//        this.producer = producer;
//    }
//
//    public ResponseEntity<HttpStatus> sendCurrency(@RequestBody Currency currency){
//        producer.sendCurrencyEvent(currency);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//
//}
