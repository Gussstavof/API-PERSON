package com.person.persondesafio.core.requests;

import com.person.persondesafio.core.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
    private Long id;
    private String cep;
    private String uf;
    private String localidade;
    private String logradouro;
    private String bairro;
    private String numero;
    private boolean main;

    public Address toAddress(){
        Address address = new Address();
        BeanUtils.copyProperties(this, address);
        return address;
    }

}
