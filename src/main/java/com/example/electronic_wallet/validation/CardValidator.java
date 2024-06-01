//package com.example.electronic_wallet.validation;
//
//import com.example.electronic_wallet.dto.CardDto;
//import com.example.electronic_wallet.exceptions.CardNotFoundException;
//import com.example.electronic_wallet.models.Card;
//import com.example.electronic_wallet.sevice.CardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Component
//public class CardValidator  {
//
//    private final CardService service;
//
//    @Autowired
//    public CardValidator(CardService service) {
//        this.service = service;
//    }
//
//
//
//    public void validate(Object target) {
//        CardDto a = (CardDto) target;
//
//        try {
//            service.findByPhone(a.getPhoneNumber());
//        } catch (CardNotFoundException e) {
//            throw new CardNotFoundException();
//        }
//    }
//}
