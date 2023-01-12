package com.person.personapi.service;

import com.person.personapi.exception.PersonNotFoundException;
import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Person;
import com.person.personapi.personDTO.PersonDTO;
import com.person.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    public PersonService(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findById(Long id){
        return personRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException(id)
        );
    }

    public Person create(PersonDTO personDto){
        Person person = personMapper.personDtoToPerson(personDto);
        return personRepository.save(person);
    }

    public Person update(Long id, PersonDTO personDTO){
        Person person = personMapper.personDtoToPerson(personDTO);
        Person personIdtoUpdate = findById(person.getId());
        personIdtoUpdate.setName(person.getName());
        personIdtoUpdate.setBirth(person.getBirth());
        return personRepository.save(personIdtoUpdate);
    }

    public void deletePerson(Long id){
        findById(id);
        personRepository.deleteById(id);
    }
}
