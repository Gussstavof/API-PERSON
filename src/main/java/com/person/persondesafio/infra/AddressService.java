package com.person.persondesafio.infra;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.services.AddressInfra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService implements AddressInfra {

    public Address validationAddress(Address address){
        String number = address.getNumero();
        boolean main = address.isMain();
        address = restEndereco(address.getCep());
        if (address.getLogradouro() == null){
            return null;
        }
        address.setNumero(number);
        address.setMain(main);
        address.setCep(address.getCep().replace("-", ""));
        return address;
    }


    private Address restEndereco(final String cep){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://viacep.com.br/ws/"+cep+"/json/",
                Address.class);
    }


}
