package com.person.personapi.service;

import com.person.personapi.dto.AddressDTO;
import com.person.personapi.exception.AddressNotFoundException;
import com.person.personapi.mapper.AddressMapper;
import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Address;
import com.person.personapi.model.Person;
import com.person.personapi.repository.AddressRepository;
import com.person.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonService personService;
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, PersonService personService, PersonMapper personMapper,
                          PersonRepository personRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.personService = personService;
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.addressMapper = addressMapper;
    }

    @Transactional(readOnly = true)
    public List<Address> findAllAddress(Long id) {
        Person person = personService.findById(id);
        List<Long> addressId = person.getAddress().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Address> addressList = addressRepository.findAllById(addressId);
        return addressList;
    }

    @Transactional(readOnly = true)
    public Address findByiD(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException(id)
        );
    }

    @Transactional
    public Address createAddress(AddressDTO addressDTO) {
        Address address = addressMapper.addressDtoToAddress(addressDTO);
        return addressRepository.save(address);
    }

    @Transactional
    public Address addAddressToPerson(Long person_id, Long address_id) {
        Address address = findByiD(address_id);
        Person person = personService.findById(person_id);
        address.setPerson(person);
        return address;
    }

    @Transactional
    public Address updateAddress(Long address_id, AddressDTO addressDTO) {
        Address address = addressMapper.addressDtoToAddress(addressDTO);
        Address addressId = findByiD(address_id);
        addressId.setId(address_id);
        addressId.setStreet(addressDTO.getStreet());
        addressId.setNumber(addressDTO.getNumber());
        addressId.setCity(addressDTO.getCity());
        addressId.setPostalCode(addressDTO.getPostalCode());
        addressRepository.save(addressId);
        return address;
    }

    @Transactional
    public void deleteAddress(Long address_id) {
        addressRepository.deleteById(address_id);
    }
}
