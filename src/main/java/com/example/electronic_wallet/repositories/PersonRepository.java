package com.example.electronic_wallet.repositories;

import com.example.electronic_wallet.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person , Long> {

   Optional<Person> findByName(String name);

}
