package com.person.persondesafio.core.controllers;

import com.person.persondesafio.core.entities.dto.AddressDto;
import com.person.persondesafio.core.entities.dto.PersonDto;
import com.person.persondesafio.core.repositories.PersonRepository;
import com.person.persondesafio.core.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> personDtoResponseEntityCreate(@Valid @RequestBody PersonDto personDto, URI location){
        return ResponseEntity.created(location).body(personService.savePerson(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> personResponseEntityPut(@Valid @PathVariable Long id, @RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.updatePerson(id, personDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> personResponseEntityGetById(@PathVariable Long id){
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping
    public  ResponseEntity<List<PersonDto>> personResponseEntityGetAll(){
        return ResponseEntity.ok(personService.findAllPerson());
    }

    @PatchMapping("/add/{id}")
    public ResponseEntity<PersonDto> personDtoResponseEntityAddAddress(@PathVariable Long id,
                                                                       @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(personService.addAddress(id, addressDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PersonDto> personDtoResponseEntityDeleteAddress(
            @RequestParam("idPerson") Long idPerson,
            @RequestParam("idAddress") Long idAddress){
        return ResponseEntity.ok(personService.deleteAddress(idPerson, idAddress));
    }
}
