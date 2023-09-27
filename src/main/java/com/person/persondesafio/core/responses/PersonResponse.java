package com.person.persondesafio.core.responses;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate birthdate;
    private LinkedHashSet<AddressResponse> address;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.birthdate = person.getBirthdate();
        this.address = new LinkedHashSet<>(person.getAddress()
                .stream()
                .map(AddressResponse::new)
                .toList()
        );
    }
}
