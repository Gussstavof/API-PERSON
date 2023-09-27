package com.person.persondesafio.core.services;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import com.person.persondesafio.core.exceptions.NotFoundException;
import com.person.persondesafio.core.repositories.PersonRepository;
import com.person.persondesafio.core.requests.AddressRequest;
import com.person.persondesafio.core.requests.PersonRequest;
import com.person.persondesafio.core.responses.PersonResponse;
import com.person.persondesafio.infra.address.AddressInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressService addressService;

    public PersonResponse save(PersonRequest request) {
        Person person = personFactory(request);

        return new PersonResponse(personRepository.save(person));
    }

    public PersonResponse update(Long id, PersonRequest request) {
        Person person = personFactory(request);

        return personRepository.findById(id).map(personUpdate -> {
            personUpdate.setName(person.getName());
            personUpdate.setBirthdate(person.getBirthdate());
            Person newPerson = personRepository.save(personUpdate);
            return new PersonResponse(newPerson);
        }).orElseThrow(() -> new NotFoundException("Person not found"));
    }

    public PersonResponse getPersonById(Long id) {
        return new PersonResponse(searchPerson(id));
    }

    public List<PersonResponse> findAllPerson() {
        return personRepository.findAll()
                .stream()
                .map(PersonResponse::new)
                .toList();
    }

    public PersonResponse addAddress(Long id, AddressRequest request) {
        Person person = searchPerson(id);

        person.getAddress()
                .add(verifyAddress(
                        request.toAddress()
                )
        );

        return new PersonResponse(
                personRepository.save(person)
        );
    }

    public PersonResponse deleteAddress(Long idPerson, Long idAddress) {
        Person person = searchPerson(idPerson);

        person.getAddress().removeIf(address ->
                Objects.equals(address.getId(), idAddress)
        );

        return new PersonResponse(
                personRepository.save(person)
        );
    }

    private Person searchPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found"));
    }

    private LinkedHashSet<Address> verifyAddress(Set<Address> addresses) {
        return new LinkedHashSet<>(
                addresses.stream()
                        .map(address -> addressService.validationAddress(address))
                        .toList()
        );
    }

    private Address verifyAddress(Address address) {
        return addressService.validationAddress(address);
    }

    private Person personFactory(PersonRequest request) {
        LinkedHashSet<Address> address = verifyAddress(request.getAddress());
        Person person = request.toPerson();
        person.setAddress(address);
        return person;
    }
}
