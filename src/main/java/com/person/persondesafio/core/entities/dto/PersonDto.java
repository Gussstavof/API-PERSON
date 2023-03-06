package com.person.persondesafio.core.entities.dto;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
        private Long id;
        private String name;
        @DateTimeFormat(pattern = "yyyy-dd-MM")
        private LocalDate birthdate;
        private Set<Address> address = new HashSet<>();

        public PersonDto(Person person) {
                this.id = person.getId();
                this.name = person.getName();
                this.birthdate = person.getBirthdate();
                this.address = person.getAddress();
        }

}
