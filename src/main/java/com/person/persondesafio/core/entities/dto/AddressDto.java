package com.person.persondesafio.core.entities.dto;

import com.person.persondesafio.core.entities.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String uf;
    private String localidade;
    private String logradouro;
    private String bairro;
    private String numero;
    private boolean main;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.uf = address.getUf();
        this.localidade = address.getLocalidade();
        this.logradouro = address.getLogradouro();
        this.bairro = address.getBairro();
        this.numero = address.getNumero();
        this.main = address.isMain();
    }
}
