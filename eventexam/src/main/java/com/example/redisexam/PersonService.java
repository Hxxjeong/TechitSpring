package com.example.redisexam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public void delete(String id) {
        personRepository.deleteById(id);
    }
}
