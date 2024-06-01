package com.example.electronic_wallet.sevice;

import com.example.electronic_wallet.exceptions.PersonNotFoundException;
import com.example.electronic_wallet.models.Person;
import com.example.electronic_wallet.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonService personService;


    @Autowired
    public PersonDetailsService(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Person person = null;
        try {
            person = personService.findByName(name);
        } catch (PersonNotFoundException e) {
            throw new PersonNotFoundException();
        }

        return new PersonDetails(person);
    }
}
