package com.person.personapi.service;

import com.person.personapi.dto.PersonDTO;
import com.person.personapi.exception.PersonNotFoundException;
import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Person;
import com.person.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Transactional
    public Person create(PersonDTO personDTO) {
        Person person = personMapper.personDtoToPerson(personDTO);
        return personRepository.save(person);
    }

    @Transactional
    public Person update(Long id, PersonDTO personDTO) {

        Person personId = findById(id);
        personId.setName(personDTO.getName());
        personId.setBirth(personDTO.getBirth());
        personId.setId(id);
        return personRepository.save(personId);
    }

    @Transactional
    public void deletePerson(Long id) {
        findById(id);
        personRepository.deleteById(id);
    }
}
