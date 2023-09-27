package com.person.persondesafio.infra.address;

import com.person.persondesafio.core.entities.Address;
import com.person.persondesafio.core.services.AddressService;

public abstract class AddressInfra implements AddressService {

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
    abstract Address restAddress(final String cep);
}
