package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.dto.BankDto;
import com.example.electronic_wallet.dto.Trans;
import com.example.electronic_wallet.models.Card;
import com.example.electronic_wallet.repositories.CardsRepository;
import com.example.electronic_wallet.security.PersonDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardsRepository cardsRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public CardService(CardsRepository cardsRepository, ModelMapper modelMapper) {
        this.cardsRepository = cardsRepository;
        this.modelMapper = modelMapper;
    }

    public void thisPerson(Card card , String created){
        card.setThisPerson(created);
        save(card);
    }

    public Card findByPhone(String number){
        return cardsRepository.findByPhoneNumber(number).orElseThrow();
    }

    public Card findByNumberCard(String number){
        return cardsRepository.findByNumberCard(number).orElseThrow();
    }

    public void save(Card card){
        cardsRepository.save(card);
    }
    public List<BankDto> balance(PersonDetails personDetails){
        List<BankDto> cards = new ArrayList<>();
        List<Card> cards1 = cardsRepository.findByThisPerson(personDetails.getUsername());
        for (int i = 0; i < cards1.size() ; i++) {
            cards.add(modelMapper.map(cards1.get(i), BankDto.class));
        }
        return cards;
    }


    public void transferCardMoney(String numberToCard , String numberFromCard , Trans balance){
        Card sender = cardsRepository.findByNumberCard(numberFromCard).orElseThrow();

        Card rec = cardsRepository.findByNumberCard(numberToCard).orElseThrow();

        double senders = sender.getBalance() - balance.getValue();

        double recs = rec.getBalance() + balance.getValue();

        sender.setBalance(senders);
        rec.setBalance(recs);

        save(sender);
        save(rec);

    }

//    public void checkErrors(BindingResult result) {
//        if (result.hasErrors()) {
//
//            StringBuilder builder = new StringBuilder();
//
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            fieldErrors.forEach(error -> {
//                builder.append(error.getField());
//                builder.append("-");
//                builder.append(error.getDefaultMessage());
//            });
//            throw new CardNotFoundException(builder.toString());
//
//        }
//    }

}
