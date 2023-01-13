package com.person.personapi.service;

import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Address;
import com.person.personapi.model.Person;
import com.person.personapi.dto.PersonDTO;
import com.person.personapi.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonService personService;
    private final PersonMapper personMapper;

    public AddressService(AddressRepository addressRepository, PersonService personService, PersonMapper personMapper) {
        this.addressRepository = addressRepository;
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public List<Address> findAllAddress(Long id){
        Person person = personService.findById(id);
        List<Address> addressList = person.getAddress();
        List<Long> listId = addressList.stream().map(x -> x.getId()).collect(Collectors.toList());
        return addressRepository.findAllById(listId);
    }

    public Address findByiD(PersonDTO personDTO, Long id){
        Person person = personMapper.personDtoToPerson(personDTO);
        Address address =  person.getAddress().get(id.intValue());
        return addressRepository.findById(address.getId()).get();
    }

    public Address createAddress(PersonDTO personDTO, Address address){
        Person person = personMapper.personDtoToPerson(personDTO);
        person.getAddress().set(0,address);
        return addressRepository.save(address);
    }

    public void deleteAddress(PersonDTO personDTO, Long id){
        Person person = personMapper.personDtoToPerson(personDTO);
        person.getAddress().remove(findByiD(personDTO,id));
        addressRepository.deleteById(id);
    }
}
