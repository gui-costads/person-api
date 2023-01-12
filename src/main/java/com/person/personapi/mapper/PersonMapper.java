package com.person.personapi.mapper;

import com.person.personapi.model.Person;
import com.person.personapi.personDTO.PersonCreateDTO;
import com.person.personapi.personDTO.PersonDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Person personDtoToPerson(PersonDTO personDto){
        return MODEL_MAPPER.map(personDto, Person.class);
    }

    public Person personCreateDtoToPerson(PersonCreateDTO personCreateDTO){
        return  MODEL_MAPPER.map(personCreateDTO, Person.class);
    }

    public PersonDTO personToPersonDto(Person person) {
        return MODEL_MAPPER.map(person, PersonDTO.class);
    }
}
