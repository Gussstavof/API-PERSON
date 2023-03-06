package com.person.persondesafio.core.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.dto.AddressDto;
import com.person.persondesafio.core.entities.dto.PersonDto;
import com.person.persondesafio.core.services.PersonService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    PersonDto personDto;

    Address address;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(personController)
                .build();
        address = Address.builder()
                .id(4L)
                .cep("000000")
                .uf("sp")
                .bairro("sé")
                .localidade("São Paulo")
                .main(true)
                .logradouro("Rua da sé")
                .build();

        personDto = PersonDto.builder()
                .id(2L)
                .name("Gustavo")
                .birthdate(LocalDate.parse("2003-11-12"))
                .address(Collections.singleton(address))
                .build();
    }

    @Test
    void personDtoResponseEntityCreate() throws Exception {
        when(personService.savePerson(personDto))
                .thenReturn(personDto);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void personResponseEntityPut() throws Exception {
        when(personService.updatePerson(2L, personDto))
                .thenReturn(personDto);

        mockMvc.perform(put("/person/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void personResponseEntityGetById() throws Exception {
        when(personService.getPersonById(2L))
                .thenReturn(personDto);

        mockMvc.perform(get("/person/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void personResponseEntityGetAll() throws Exception {
        when(personService.findAllPerson())
                .thenReturn(Collections.singletonList(personDto));

        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void personDtoResponseEntityAddAddress() throws Exception {
        var newAddress = AddressDto.builder()
                .cep("111111")
                .uf("sp")
                .bairro("vila madalena")
                .localidade("São Paulo")
                .main(false)
                .logradouro("Rua da sé")
                .build();

        when(personService.addAddress(2L, newAddress))
                .thenReturn(personDto);

        mockMvc.perform(patch("/person/add/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void personDtoResponseEntityDeleteAddress() throws Exception {
        when(personService.deleteAddress(2L, 4L))
                .thenReturn(personDto);

        mockMvc.perform(delete("/person/delete?idPerson=1&idAddress=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}