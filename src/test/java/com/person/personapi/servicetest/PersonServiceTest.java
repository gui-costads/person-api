package com.person.personapi.servicetest;

import com.person.personapi.dto.PersonDTO;
import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Person;
import com.person.personapi.repository.PersonRepository;
import com.person.personapi.service.PersonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;
    @Test
    void findAllTest(){
        PersonDTO personDTO = new PersonDTO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personDTO.setName("Daniel");
        personDTO.setBirth(LocalDate.of(2010,10,10));
        Person person = personService.create(personDTO);

        PersonDTO personDto2 = new PersonDTO();
        personDto2.setName("Kirk");
        personDto2.setBirth(LocalDate.of(2008,12,23));
        Person person2 = personService.create(personDto2);

        List<Person> personList = personService.findAll();

        Assertions.assertTrue(personList.size() > 1);
    }
    @Test
    void createPersonTest(){
        PersonDTO personDto = new PersonDTO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personDto.setName("Daniel");
        personDto.setBirth(LocalDate.of(2010,10,10));
        Person person = personService.create(personDto);

        PersonDTO personDto2 = new PersonDTO();
        personDto2.setName("Kirk");
        personDto2.setBirth(LocalDate.of(2008,12,23));
        Person person2 = personService.create(personDto2);

        Assertions.assertEquals("Daniel", person.getName());
        Assertions.assertEquals("10/10/2010", person.getBirth().format(dateTimeFormatter));
        Assertions.assertEquals("Kirk", person2.getName());
        Assertions.assertEquals("23/12/2008", person2.getBirth().format(dateTimeFormatter));
    }

    @Test
    void findByIdTest(){
        PersonDTO personDto = new PersonDTO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personDto.setName("Daniel");
        personDto.setBirth(LocalDate.of(2010,10,10));
        Person person = personService.create(personDto);
        Person personFinded = personService.findById(person.getId());

        Assertions.assertEquals(person.getId(), personFinded.getId());
        Assertions.assertEquals("Daniel", personFinded.getName());
        Assertions.assertEquals("10/10/2010", personFinded.getBirth().format(dateTimeFormatter));
    }
    @Test
    void updatePersonTest(){
        PersonDTO personDto = new PersonDTO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personDto.setName("Daniel");
        personDto.setBirth(LocalDate.of(2010,10,10));
        Person person = personService.create(personDto);

        personDto.setName("Justin");
        personDto.setBirth(LocalDate.of(2010, 10,11));

        Person personUpdated = personService.update(person.getId(),personDto);

        Assertions.assertEquals(person.getId(), personUpdated.getId());
        Assertions.assertEquals("Justin", personUpdated.getName());
        Assertions.assertEquals("11/10/2010", personUpdated.getBirth().format(dateTimeFormatter));
    }
}
