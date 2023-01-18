package com.person.personapi.servicetest;

import com.person.personapi.dto.AddressDTO;
import com.person.personapi.mapper.AddressMapper;
import com.person.personapi.model.Address;
import com.person.personapi.model.Person;
import com.person.personapi.repository.AddressRepository;
import com.person.personapi.repository.PersonRepository;
import com.person.personapi.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressServiceTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressMapper addressMapper;

    @Test
    void findallAddressTest() {
        Person person = new Person();
        person.setName("Daniel");
        person.setBirth(LocalDate.of(2010, 10, 10));

        personRepository.save(person);

        Address address = new Address();
        address.setCity("SP");
        address.setStreet("rua 10");
        address.setNumber("SP");
        address.setPostalCode("12115-123");
        address.setPerson(person);

        addressRepository.save(address);

        Address address2 = new Address();
        address2.setCity("MA");
        address2.setStreet("Rua 23");
        address2.setNumber("10");
        address2.setPostalCode("12345-123");
        address2.setPerson(person);

        addressRepository.save(address2);

        List<Address> addressList = addressService.findAllAddress(person.getId());

        Assertions.assertTrue(addressList.size() > 1);
        Assertions.assertEquals(addressList.get(0).getPostalCode(), address.getPostalCode());
        Assertions.assertEquals(addressList.get(1).getPostalCode(), address2.getPostalCode());
    }

    @Test
    void findAddressByIdTest() {
        Person person = new Person();
        person.setName("Daniel");
        person.setBirth(LocalDate.of(2010, 10, 10));
        personRepository.save(person);

        Address address = new Address();
        address.setCity("SP");
        address.setStreet("rua 10");
        address.setNumber("11");
        address.setPostalCode("12115-123");

        AddressDTO addressDTO = addressMapper.addressToAddressDto(address);
        Address addressCreated = addressRepository.save(address);
        Address addressFinded = addressService.findByiD(addressCreated.getId());

        Assertions.assertEquals("rua 10", addressFinded.getStreet());
        Assertions.assertEquals("SP", addressFinded.getCity());
        Assertions.assertEquals("11", addressFinded.getNumber());
        Assertions.assertEquals("12115-123", addressFinded.getPostalCode());
    }

    @Test
    void addAddressToPersonTest() {
        Person person = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        person.setName("Daniel");
        person.setBirth(LocalDate.of(2010, 10, 10));
        Person personCreated = personRepository.save(person);

        Address address = new Address();
        address.setCity("SP");
        address.setStreet("rua 10");
        address.setNumber("11");
        address.setPostalCode("12115-123");

        AddressDTO addressDTO = addressMapper.addressToAddressDto(address);
        addressRepository.save(address);
        Address addressCreated = addressService.createAddress(addressDTO);
        addressService.addAddressToPerson(personCreated.getId(), addressCreated.getId());

        Assertions.assertEquals("Daniel", addressRepository.findById(addressCreated.getId()).get()
                .getPerson().getName());
        Assertions.assertEquals("10/10/2010", addressRepository.findById(addressCreated.getId()).get()
                .getPerson().getBirth()
                .format(dateTimeFormatter));
    }


    @Test
    void updateAddressTest() {
        Person person = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        person.setName("Daniel");
        person.setBirth(LocalDate.of(2010, 10, 10));
        Person personCreated = personRepository.save(person);

        Address address = new Address();
        address.setCity("SP");
        address.setStreet("rua 10");
        address.setNumber("11");
        address.setPostalCode("12115-123");

        AddressDTO addressDTO = addressMapper.addressToAddressDto(address);
        addressRepository.save(address);
        Address addressCreated = addressService.createAddress(addressDTO);
        addressService.addAddressToPerson(personCreated.getId(), addressCreated.getId());

        addressCreated.setNumber("23");
        addressCreated.setCity("RS");
        addressCreated.setPostalCode("67537-231");
        addressCreated.setStreet("Rua 20");
        AddressDTO addressToUpdateDto = addressMapper.addressToAddressDto(addressCreated);

        addressService.updateAddress(addressCreated.getId(), addressToUpdateDto);

        Assertions.assertEquals("Rua 20", addressRepository.findById(addressCreated.getId()).get().getStreet());
        Assertions.assertEquals("RS", addressRepository.findById(addressCreated.getId()).get().getCity());
        Assertions.assertEquals("23", addressRepository.findById(addressCreated.getId()).get().getNumber());
        Assertions.assertEquals("67537-231", addressRepository.findById(addressCreated.getId()).get().getPostalCode());
    }
}
