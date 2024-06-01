package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.models.Person;
import com.example.electronic_wallet.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonEncoderService {
    private final PersonRepository personRepository;

    private final PersonService service;


    private final PasswordEncoder passwordEncoder;



    @Autowired
    public PersonEncoderService(PersonRepository personRepository, PersonService service, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    public String savePassword(String input){
        return passwordEncoder.encode(input);
    }

    public void savePerson(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }
}
