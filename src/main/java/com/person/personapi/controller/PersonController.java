package com.person.personapi.controller;

import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Person;
import com.person.personapi.personDTO.PersonCreateDTO;
import com.person.personapi.personDTO.PersonDTO;
import com.person.personapi.service.PersonService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping
public class PersonController {
    private final PersonMapper personMapper;
    private final PersonService personService;


    public PersonController(PersonMapper personMapper, PersonService personService) {
        this.personMapper = personMapper;
        this.personService = personService;
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> findAll(){
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> findById(Long id){
        Person person = personService.findById(id);
        PersonDTO personDTO = personMapper.personToPersonDto(person);
        return ResponseEntity.ok(personDTO);
    }

    @PostMapping("/person")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonCreateDTO personCreateDTO){
        Person person = personMapper.personCreateDtoToPerson(personCreateDTO);
        Person personCreated = personService.create(person);
        PersonDTO personDTO = personMapper.personToPersonDto(personCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
    }

    @PutMapping("person/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonCreateDTO personCreateDTO){
        Person person = personMapper.personCreateDtoToPerson(personCreateDTO);
        Person personUpdated = personService.update(id, person);
        PersonDTO personDTO = personMapper.personToPersonDto(personUpdated);
        return ResponseEntity.ok(personDTO);
    }

    @DeleteMapping("personq{id}")
    public ResponseEntity deletePerson(Long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
