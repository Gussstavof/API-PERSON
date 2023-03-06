package com.person.persondesafio.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate birthdate;

    @NotBlank
    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private Set<Address> address = new HashSet<>();

}
