package com.person.personapi.mapper;

import com.person.personapi.model.Person;
import com.person.personapi.dto.PersonCreateDTO;
import com.person.personapi.dto.PersonDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Person personDtoToPerson(PersonDTO personDto) {
        return MODEL_MAPPER.map(personDto, Person.class);
    }

    public PersonDTO personCreateDtoToPersonDto(PersonCreateDTO personCreateDTO) {
        return MODEL_MAPPER.map(personCreateDTO, PersonDTO.class);
    }

    public PersonDTO personToPersonDto(Person person) {
        return MODEL_MAPPER.map(person, PersonDTO.class);
    }

    public List<PersonDTO> personListToPersonDtoList(List<Person> personList) {
        return personList.stream().map(this::personToPersonDto).collect(Collectors.toList());
    }
}
