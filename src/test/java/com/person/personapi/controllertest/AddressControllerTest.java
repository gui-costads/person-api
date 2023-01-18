package com.person.personapi.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.personapi.model.Address;
import com.person.personapi.model.Person;
import com.person.personapi.repository.AddressRepository;
import com.person.personapi.repository.PersonRepository;
import com.person.personapi.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
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

        addressRepository.save(address);
        addressService.addAddressToPerson(person.getId(), address.getId());
    }

    @Test
    void findAllAddressStatusOK() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/person/1/address")
                                                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void findByIdStatusOk() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/person/1/address/1")
                                                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void createAddressStatusCreated() throws Exception {
        Person person = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person.setName("Iga");
        person.setBirth(LocalDate.of(2010, 10, 10));
        Person personCreated = personRepository.save(person);

        Address address = new Address();
        address.setCity("SP");
        address.setStreet("rua 10");
        address.setNumber("11");
        address.setPostalCode("12115-123");

        Address addressCreated = addressRepository.save(address);
        Address addAddressToPerson = addressService.addAddressToPerson(personCreated.getId(), addressCreated.getId());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/person/2/address")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(person))
                                                  .accept(MediaType.APPLICATION_JSON_VALUE)
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(addAddressToPerson))
                                                  .accept(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    void updateAddressStatusOK() throws Exception {
        Person person = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person.setName("Iga");
        person.setBirth(LocalDate.of(2010, 10, 10));
        Person personCreated = personRepository.save(person);

        Address address = new Address();
        address.setCity("SP");
        address.setStreet("rua 10");
        address.setNumber("11");
        address.setPostalCode("12115-123");

        Address addressCreated = addressRepository.save(address);
        Address addAddressToPerson = addressService.addAddressToPerson(personCreated.getId(), addressCreated.getId());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/person/1/address")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(person))
                                                  .accept(MediaType.APPLICATION_JSON_VALUE)
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(addAddressToPerson))
                                                  .accept(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());

        addAddressToPerson.setPostalCode("33333-333");

        MockHttpServletResponse responseUpdate = mockMvc.perform(MockMvcRequestBuilders.put("/person/1/address/1")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(person))
                                                        .accept(MediaType.APPLICATION_JSON_VALUE)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(addAddressToPerson))
                                                        .accept(MediaType.APPLICATION_JSON_VALUE))
                                                        .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());
        Assertions.assertEquals(responseUpdate.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void deletePersonStatusNoContent() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/person/1/address/1")
                                                  .content(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());
    }
}
