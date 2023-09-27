package com.person.persondesafio.core.requests;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
public class PersonRequest {
    private String name;
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate birthdate;
    private Set<Address> address = new LinkedHashSet<>();

    public Person toPerson() {
        Person person = new Person();
        BeanUtils.copyProperties(this, person);
        return person;
    }

}
