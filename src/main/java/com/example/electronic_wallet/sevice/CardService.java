package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.dto.BankDto;
import com.example.electronic_wallet.dto.CurrencyDto;
import com.example.electronic_wallet.dto.Trans;
import com.example.electronic_wallet.models.Card;
import com.example.electronic_wallet.models.CurrencyRate;
import com.example.electronic_wallet.models.Transaction;
import com.example.electronic_wallet.repositories.CardsRepository;
import com.example.electronic_wallet.repositories.CurrencyRateRepository;
import com.example.electronic_wallet.repositories.TransactionRepository;
import com.example.electronic_wallet.security.PersonDetails;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CardService {

    private final CardsRepository cardsRepository;

    private final ModelMapper modelMapper;

    private final TransactionRepository transactionRepository;

    private final CurrencyService currencyService;

    private final CurrencyRateRepository currencyRateRepository;


    @Autowired
    public CardService(CardsRepository cardsRepository, ModelMapper modelMapper, TransactionRepository transactionRepository, CurrencyService currencyService, CurrencyRateRepository currencyRateRepository) {
        this.cardsRepository = cardsRepository;
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
        this.currencyService = currencyService;
        this.currencyRateRepository = currencyRateRepository;
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


    public void transferCardMoney(String numberToCard, String numberFromCard, Trans balance, String nameCurrency) {

        Transaction transaction = new Transaction();
        Random random = new Random();

        Card sender = cardsRepository.findByNumberCard(numberFromCard).orElseThrow();
        Card rec = cardsRepository.findByNumberCard(numberToCard).orElseThrow();

        double senders = sender.getBalance() - balance.getValue();

        double recs = rec.getBalance() + balance.getValue();

        transaction.setValue(balance.getValue());
        transaction.setNumberCardFrom(sender.getNumberCard());
        transaction.setPersonFromName(sender.getThisPerson());
        transaction.setNumberCardTo(rec.getNumberCard());
        transaction.setPersonToName(rec.getThisPerson());
        transaction.setCreatedAt(LocalDateTime.now());

        for (int i = random.nextInt(2000); i < currencyRateRepository.findByCurrency(nameCurrency).size(); i++) {
            CurrencyRate currencyRates = modelMapper.map(currencyRateRepository.findByCurrency(nameCurrency).get(i), CurrencyRate.class);
            transaction.setCurrency(currencyRates.getCurrency());
            transaction.setCourse(currencyRates.getRate());
            break;
        }
        transactionRepository.save(transaction);


        sender.setBalance(senders);
        rec.setBalance(recs);

        save(sender);
        save(rec);

    }

}
