package com.example.electronic_wallet.conrollers;

import com.example.electronic_wallet.dto.*;
import com.example.electronic_wallet.jwt.JWTUtils;
import com.example.electronic_wallet.models.Card;
import com.example.electronic_wallet.security.PersonDetails;
import com.example.electronic_wallet.sevice.CardEncoderService;
import com.example.electronic_wallet.sevice.CardService;
import com.example.electronic_wallet.sevice.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardRegistrationController {

    private final CardService cardService;
    private final CardEncoderService encoderService;

    private final CurrencyService currencyService;


    private final JWTUtils utils;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;



    @Autowired
    public CardRegistrationController(CardService cardService, CardEncoderService encoderService, CurrencyService currencyService, JWTUtils utils, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.cardService = cardService;
        this.encoderService = encoderService;
        this.currencyService = currencyService;
        this.utils = utils;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;

    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> CardRegistration(@RequestBody CardDto cardDto, @AuthenticationPrincipal
    UserDetails userDetails, BindingResult bindingResult) {

        Card card = modelMapper.map(cardDto, Card.class);


        cardService.thisPerson(card, userDetails.getUsername());


        encoderService.cardSave(card);


        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/balance")
    public List<BankDto> balanceCard(@AuthenticationPrincipal PersonDetails personDetails) {
        return cardService.balance(personDetails);
    }


    @PostMapping("/transfer")
    @ResponseBody
    public ResponseEntity<HttpStatus> transfer(@RequestBody TransferDto transferDto) {
        cardService.transferCardMoney(transferDto.getNumberToCard(),
                transferDto.getNumberFromCard(),
                new Trans(transferDto.getTrans().getValue()),
                transferDto.getCurrency());
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


    @PostMapping("/deposit")
    public ResponseEntity<HttpStatus> depositCard(@RequestBody DepositDto card, Double da) {

        Card card1 = cardService.findByNumberCard(card.getNumberCard());

        card1.setBalance(card1.getBalance() + da);

        cardService.save(card1);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
