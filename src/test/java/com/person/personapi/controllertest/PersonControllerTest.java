package com.person.personapi.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.personapi.model.Person;
import com.person.personapi.repository.PersonRepository;
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
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ObjectMapper objectMapper;
    @BeforeEach
    void setup() {
        Person person = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person.setName("Daniel");
        person.setBirth(LocalDate.of(2010, 10, 10));
        personRepository.save(person);

        Person person2 = new Person();
        person2.setName("Kirk");
        person2.setBirth(LocalDate.of(2008, 12, 23));
        personRepository.save(person2);
    }
    @Test
    void findAllStatusOK() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/person")
                                                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
    @Test
    void findByIdStatusOk() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/person/2")
                                                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void createPersonStatusCreated() throws Exception {
        Person person3 = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person3.setName("Tom");
        person3.setBirth(LocalDate.of(2010, 10, 10));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/person")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .accept(MediaType.APPLICATION_JSON_VALUE)
                                                  .content(objectMapper.writeValueAsString(person3)))
                                                  .andReturn().getResponse();
        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }
    @Test
    void updatePersonStatusOk() throws Exception {
        Person person3 = new Person();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person3.setId(3L);
        person3.setName("Tom");
        person3.setBirth(LocalDate.of(2010, 10, 10));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/person")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .accept(MediaType.APPLICATION_JSON_VALUE)
                                                  .content(objectMapper.writeValueAsString(person3)))
                                                  .andReturn().getResponse();
        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());

        person3.setName("Dak");

        MockHttpServletResponse responseUpdate = mockMvc.perform(MockMvcRequestBuilders.put("/person/3")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .accept(MediaType.APPLICATION_JSON_VALUE)
                                                        .content(objectMapper.writeValueAsString(person3)))
                                                        .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());
        Assertions.assertEquals(responseUpdate.getStatus(), HttpStatus.OK.value());
    }
    @Test
    void deletePersonStatusNoContent() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/person/1")
                                                  .content(MediaType.APPLICATION_JSON_VALUE))
                                                  .andReturn().getResponse();

        Assertions.assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());
    }
}
