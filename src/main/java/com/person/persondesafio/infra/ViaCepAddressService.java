package com.person.persondesafio.infra;

import com.person.persondesafio.core.entities.Address;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class ViaCepAddressService implements AddressInfra {
    public Address validationAddress(Address address){
        String number = address.getNumero();
        boolean main = address.isMain();
        address = restAddress(address.getCep());
        if (address.getLogradouro() == null){
            return null;
        }
        address.setNumero(number);
        address.setMain(main);
        address.setCep(address.getCep().replace("-", ""));
        return address;
    }

    private Address restAddress(final String cep){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://viacep.com.br/ws/"+cep+"/json/",
                Address.class);
    }
}
