package com.person.persondesafio.infra;

import com.person.persondesafio.core.entities.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCepAddressService implements AddressInfra{
    @Override
    public Address validationAddress(Address address) {
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
        return restTemplate.getForObject("https://cdn.apicep.com/file/apicep/"+cep+".json",
                Address.class);
    }
}
