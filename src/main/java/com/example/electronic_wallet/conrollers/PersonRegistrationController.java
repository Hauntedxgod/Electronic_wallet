package com.example.electronic_wallet.conrollers;

import com.example.electronic_wallet.dto.LoginDto;
import com.example.electronic_wallet.dto.ShowMeDto;
import com.example.electronic_wallet.jwt.JWTUtils;
import com.example.electronic_wallet.models.Person;

import com.example.electronic_wallet.security.PersonDetails;
import com.example.electronic_wallet.sevice.PersonEncoderService;
import com.example.electronic_wallet.sevice.PersonService;
import com.example.electronic_wallet.dto.PersonDto;
import com.example.electronic_wallet.validation.PersonValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reg")
public class PersonRegistrationController {
    private final PersonService personService;
    private final PersonEncoderService personServiceEncoder;
    private final PersonValidator validation;
    private final JWTUtils utils;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonRegistrationController(PersonService personService, PersonEncoderService personEncoderService, PersonValidator validation, JWTUtils utils, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.personService = personService;
        this.personServiceEncoder = personEncoderService;
        this.validation = validation;
        this.utils = utils;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/registration")
    public Map<String , String> personRegistration(@RequestBody @Valid PersonDto personDto ,
                                                   BindingResult bindingResult){

        Person person = modelMapper.map(personDto , Person.class);

        validation.validate(person , bindingResult);

        if (bindingResult.hasErrors()){
            return Map.of("message" , "500");
        }

        personServiceEncoder.savePerson(person);


        String token = utils.generateToken(person.getName());

        return Map.of("jwt - token" , token);
    }


    @PostMapping("/login")
    public Map<String , Object> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername() , loginDto.getPassword());

        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (AuthenticationException e) {
            return Map.of("message" , "500");
        }

        Person personToken = personService.findByName(loginDto.getUsername());

        String token = utils.generateToken(loginDto.getUsername());

        return Map.of("jwt - token", token, "name", personToken.getName(), "age", personToken.getAge());
    }


    @GetMapping("/showAboutMe")
    public ShowMeDto getMyPerson(@AuthenticationPrincipal PersonDetails personDetails) {
       return modelMapper.map(personService.getMyPerson(personDetails) , ShowMeDto.class);
    }
}
