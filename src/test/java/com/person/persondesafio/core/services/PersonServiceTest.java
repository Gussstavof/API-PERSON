package com.person.persondesafio.core.services;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import com.person.persondesafio.core.exceptions.NotFoundException;
import com.person.persondesafio.core.repositories.PersonRepository;
import com.person.persondesafio.core.requests.AddressRequest;
import com.person.persondesafio.core.requests.PersonRequest;
import com.person.persondesafio.core.responses.AddressResponse;
import com.person.persondesafio.core.responses.PersonResponse;
import com.person.persondesafio.infra.address.AddressInfra;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private PersonService personService;

    PersonRequest request;
    Person person;

    @BeforeEach
    void setup() {
        request = new PersonRequest();
        person = new Person();
    }


    @Test
    public void testSavePerson() {
        when(addressService.validationAddress(any()))
                .thenReturn(new Address());
        when(personRepository.save(any()))
                .thenReturn(person);

        PersonResponse result = personService.save(request);

        assertEquals(result, new PersonResponse(person));
    }

    @Test
    public void testUpdatePerson() {
        Long personId = 1L;
        Person existingPerson = new Person();

        when(personRepository.findById(personId))
                .thenReturn(Optional.of(existingPerson));
        when(addressService.validationAddress(any()))
                .thenReturn(new Address());
        when(personRepository.save(any()))
                .thenReturn(existingPerson);

        PersonResponse result = personService.update(personId, request);

        assertEquals(result, new PersonResponse(existingPerson));
    }

    @Test
    public void testUpdatePersonNotFound() {
        Long personId = 1L;

        when(personRepository.findById(personId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> personService.update(personId, request));
    }

    @Test
    public void testGetPersonById() {
        Long personId = 1L;

        when(personRepository.findById(personId))
                .thenReturn(Optional.of(person));

        PersonResponse result = personService.getPersonById(personId);

        assertEquals(result, new PersonResponse(person));
    }

    @Test
    public void testGetPersonByIdNotFound() {
        Long personId = 1L;

        when(personRepository.findById(personId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> personService.getPersonById(personId));
    }

    @Test
    public void testFindAllPersons() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person());

        when(personRepository.findAll())
                .thenReturn(personList);

        List<PersonResponse> responseList = personService.findAllPerson();

        assertEquals(personList.size(), responseList.size());
    }

    @Test
    public void testAddAddress() {
        Long personId = 1L;
        AddressRequest addressRequest = new AddressRequest();

        when(personRepository.findById(personId))
                .thenReturn(Optional.of(person));
        when(addressService.validationAddress(any()))
                .thenReturn(new Address());
        when(personRepository.save(any()))
                .thenReturn(person);

        PersonResponse result = personService.addAddress(personId, addressRequest);

        assertEquals(result, new PersonResponse(person));
    }

    @Test
    public void testDeleteAddress() {
        Long personId = 1L;
        Long addressId = 2L;
        Address address = new Address();
        address.setId(addressId);
        person.getAddress().add(address);

        when(personRepository.findById(personId))
                .thenReturn(Optional.of(person));
        when(personRepository.save(any()))
                .thenReturn(person);

        PersonResponse result = personService.deleteAddress(personId, addressId);

        assertEquals(result, new PersonResponse(person));
        assertEquals(0, person.getAddress().size());
    }

    @Test
    public void testDeleteAddressNotFound() {
        Long personId = 1L;
        Long addressId = 2L;

        when(personRepository.findById(personId))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> personService.deleteAddress(personId, addressId));
    }
}