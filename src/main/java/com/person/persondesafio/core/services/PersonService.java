package com.person.persondesafio.core.services;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import com.person.persondesafio.core.entities.dto.AddressDto;
import com.person.persondesafio.core.entities.dto.Mapper;
import com.person.persondesafio.core.entities.dto.PersonDto;
import com.person.persondesafio.core.exceptions.NotFoundException;
import com.person.persondesafio.core.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressInfra addressInfra;

    @Autowired
    Mapper mapper;

    public PersonDto savePerson(PersonDto personDto){
        var result = verifyAddress(
                mapper.toPerson(personDto)
        );
       return mapper.toPersonDto(
               personRepository.save(result)
       );
    }

    public PersonDto updatePerson(Long id, PersonDto personDto) {
        var result = verifyAddress(mapper.toPerson(personDto));
        return personRepository.findById(id).map(personUpdate -> {
            personUpdate.setName(result.getName());
            personUpdate.setBirthdate(result.getBirthdate());
            return mapper.toPersonDto(
                    personRepository.save(personUpdate)
            );
        }).orElseThrow(() -> new NotFoundException("Id not found"));
    }

    public PersonDto getPersonById(Long id){
        return mapper.toPersonDto(
               searchPerson(id)
        );
    }

    public List<PersonDto> findAllPerson() {
        return mapper.toPersonDto(
                personRepository.findAll()
        );
    }

    public PersonDto addAddress(Long id, AddressDto addressDto) {
        Person person = searchPerson(id);
        person.getAddress().add(mapper.toAddress(addressDto));
        return mapper.toPersonDto(
                personRepository.save(verifyAddress(person))
        );
    }

    public PersonDto deleteAddress(Long idPerson, Long idAddress){
        Person person = searchPerson(idPerson);

        person.getAddress()
                .removeIf(address -> Objects.equals(address.getId(), idAddress));

        return mapper.toPersonDto(
                personRepository.save(person)
        );
    }

    private Person searchPerson(Long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id not found"));
    }
    private Person verifyAddress(Person person){
        Set<Address> cep = person.getAddress();
        Set<Address> verifiedAddress = new HashSet<>();

        for (Address a: cep) {
            verifiedAddress.add(addressInfra.validationAddress(a));
        }

        return Person.builder()
                .id(person.getId())
                .address(verifiedAddress)
                .name(person.getName())
                .birthdate(person.getBirthdate())
                .build();
    }
}
