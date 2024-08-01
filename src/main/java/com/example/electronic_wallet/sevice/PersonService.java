package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.exceptions.PersonNotFoundException;
import com.example.electronic_wallet.models.Person;
import com.example.electronic_wallet.repositories.PersonRepository;
import com.example.electronic_wallet.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person findByName(String name){
        return personRepository.findByName(name).orElseThrow(PersonNotFoundException::new);
    }


    public Person getMyPerson(PersonDetails personDetails) {
        return personRepository.findByName(personDetails.getUsername()).orElseThrow(PersonNotFoundException::new);
    }



}
