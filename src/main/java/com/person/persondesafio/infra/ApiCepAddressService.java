package com.person.persondesafio.infra;

import com.person.persondesafio.core.entities.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCepAddressService extends AddressInfra{
    public Address restAddress(final String cep){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://cdn.apicep.com/file/apicep/"+cep+".json",
                Address.class);
    }
}
