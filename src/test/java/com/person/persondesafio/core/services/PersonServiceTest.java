package com.person.persondesafio.core.services;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import com.person.persondesafio.core.entities.dto.AddressDto;
import com.person.persondesafio.core.entities.dto.Mapper;
import com.person.persondesafio.core.entities.dto.PersonDto;
import com.person.persondesafio.core.repositories.PersonRepository;
import com.person.persondesafio.infra.AddressInfra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    AddressInfra addressInfra;

    @Mock
    PersonRepository personRepository;

    @Mock
    Mapper mapper;

    PersonDto personDto;

    Person person;

    AddressDto addressDto;

    Address address;

    @BeforeEach
    public void setUp() {
        addressDto = AddressDto.builder()
                .id(4L)
                .cep("000000")
                .uf("sp")
                .bairro("sé")
                .localidade("São Paulo")
                .main(true)
                .logradouro("Rua da sé")
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
        person = Person.builder()
                .id(2L)
                .name("Gustavo")
                .birthdate(LocalDate.parse("2003-11-12"))
                .address(Collections.singleton(address))
                .build();

        when(mapper.toPersonDto(person))
                .thenReturn(personDto);
        when(mapper.toPerson(personDto))
                .thenReturn(person);
        when(mapper.toPersonDto(Collections.singletonList(person)))
                .thenReturn(Collections.singletonList(personDto));
        when(mapper.toAddressDto(address))
                .thenReturn(addressDto);
        when(mapper.toAddress(addressDto))
                .thenReturn(address);
        when(addressInfra.validationAddress(address))
                .thenReturn(address);
    }

    @Test
    void savePerson() {
        when(personRepository.save(person))
                .thenReturn(person);

        var result = personService.savePerson(personDto);

        assertSame(personDto, result);
    }

    @Test
    void updatePerson() {
        when(personRepository.findById(2L))
                .thenReturn(Optional.ofNullable(person));
        when(personRepository.save(person))
                .thenReturn(person);

        var result = personService.updatePerson(2L, personDto);

        assertEquals(personDto, result);
    }

    @Test
    void getPersonById() {
        when(personRepository.findById(2L))
                .thenReturn(Optional.ofNullable(person));

        var result = personService.getPersonById(2L);

        assertEquals(personDto, result);
    }

    @Test
    void findAllPerson() {
        when(personRepository.findAll())
                .thenReturn(Collections.singletonList(person));

        var result = personService.findAllPerson();

        assertEquals(Collections.singletonList(personDto), result);
    }


    //UnsupportedOperationException
    @Test
    void addAddress() {

    }

    //UnsupportedOperationException
    @Test
    void deleteAddress() {
    }
}