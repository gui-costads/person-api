package com.person.personapi.controller;

import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Person;
import com.person.personapi.dto.PersonCreateDTO;
import com.person.personapi.dto.PersonDTO;
import com.person.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<Person> personList = personService.findAll();
        List<PersonDTO> personDTOList = personMapper.personListToPersonDtoList(personList);
        return ResponseEntity.ok(personDTOList);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> findById(Long id) {
        Person person = personService.findById(id);
        PersonDTO personDTO = personMapper.personToPersonDto(person);
        return ResponseEntity.ok(personDTO);
    }

    @PostMapping("/person")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonCreateDTO personCreateDTO) {
        PersonDTO personDTO = personMapper.personCreateDtoToPersonDto(personCreateDTO);
        Person personCreated = personService.create(personDTO);
        PersonDTO personDtoCreated = personMapper.personToPersonDto(personCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDtoCreated);
    }

    @PutMapping("person/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id,
                                                  @RequestBody PersonCreateDTO personCreateDTO) {
        PersonDTO personDTO = personMapper.personCreateDtoToPersonDto(personCreateDTO);
        Person personUpdated = personService.update(id, personDTO);
        PersonDTO personUpdatedDto = personMapper.personToPersonDto(personUpdated);
        return ResponseEntity.ok(personUpdatedDto);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
