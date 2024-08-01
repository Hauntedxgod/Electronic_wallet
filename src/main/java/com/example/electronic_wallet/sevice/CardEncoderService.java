package com.example.electronic_wallet.sevice;


import com.example.electronic_wallet.dto.DepositDto;
import com.example.electronic_wallet.models.Card;
import com.example.electronic_wallet.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CardEncoderService {

    private final CardsRepository cardsRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public CardEncoderService(CardsRepository cardsRepository, PasswordEncoder passwordEncoder) {
        this.cardsRepository = cardsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void cardSave(Card card){
        card.setNumberCard(generatedCardNumber());
        card.setPassword(passwordEncoder.encode(card.getPassword()));
        card.setBalance(0);
        cardsRepository.save(card);
    }


    public String generatedCardNumber(){
        Random random = new Random();
        int count = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (count <= 16){
            int generated = random.nextInt(9);
            stringBuffer.append(generated);
            count++;
        }
        return stringBuffer.toString();
    }


}
