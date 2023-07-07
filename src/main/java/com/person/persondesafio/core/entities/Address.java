package com.person.persondesafio.core.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias(value = "code")
    private String cep;

    @JsonAlias(value = "state")
    private String uf;

    @JsonAlias(value = "city")
    private String localidade;

    @JsonAlias(value = "address")
    private String logradouro;

    @JsonAlias(value = "district")
    private String bairro;
    private String numero;
    private boolean main;
}
