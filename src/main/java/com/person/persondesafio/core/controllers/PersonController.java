package com.person.persondesafio.core.controllers;

import com.person.persondesafio.core.requests.AddressRequest;
import com.person.persondesafio.core.requests.PersonRequest;
import com.person.persondesafio.core.responses.PersonResponse;
import com.person.persondesafio.core.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> personDtoResponseEntityCreate(
            @Valid @RequestBody PersonRequest request,
            URI location
    ) {
        return ResponseEntity.created(location).body(personService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> personResponseEntityPut(
            @Valid @PathVariable Long id,
            @RequestBody PersonRequest request
    ) {
        return ResponseEntity.ok(personService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> personResponseEntityGetById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> personResponseEntityGetAll() {
        return ResponseEntity.ok(personService.findAllPerson());
    }

    @PatchMapping("/add/{id}")
    public ResponseEntity<PersonResponse> personDtoResponseEntityAddAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request
    ) {
        return ResponseEntity.ok(personService.addAddress(id, request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PersonResponse> personDtoResponseEntityDeleteAddress(
            @RequestParam("idPerson") Long idPerson,
            @RequestParam("idAddress") Long idAddress
    ) {
        return ResponseEntity.ok(personService.deleteAddress(idPerson, idAddress));
    }
}
