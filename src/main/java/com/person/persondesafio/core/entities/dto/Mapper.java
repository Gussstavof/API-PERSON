package com.person.persondesafio.core.entities.dto;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class Mapper {

    public Person toPerson(PersonDto personDto){
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .birthdate(personDto.getBirthdate())
                .address(personDto.getAddress())
                .build();
    }

    public PersonDto toPersonDto(Person person){
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .birthdate(person.getBirthdate())
                .address(person.getAddress())
                .build();
    }

    public List<PersonDto> toPersonDto(List<Person> people) {
        return people.stream().map(PersonDto::new).toList();
    }

    public AddressDto toAddressDto(Address address){
        return AddressDto.builder()
                .cep(address.getCep())
                .uf(address.getUf())
                .localidade(address.getLocalidade())
                .logradouro(address.getLogradouro())
                .bairro(address.getBairro())
                .numero(address.getNumero())
                .main(address.isMain())
                .build();
    }

    public Address toAddress(AddressDto addressDto){
        return Address.builder()
                .cep(addressDto.getCep())
                .uf(addressDto.getUf())
                .localidade(addressDto.getLocalidade())
                .logradouro(addressDto.getLogradouro())
                .bairro(addressDto.getBairro())
                .numero(addressDto.getNumero())
                .main(addressDto.isMain())
                .build();
    }
}
