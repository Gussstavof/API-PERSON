package com.person.persondesafio.infra.address;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.infra.address.AddressInfra;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class ViaCepAddressService extends AddressInfra {
    public Address restAddress(final String cep){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://viacep.com.br/ws/"+cep+"/json/",
                Address.class);
    }
}
