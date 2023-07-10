package com.person.persondesafio.infra;

import com.person.persondesafio.core.entities.Address;

public abstract class AddressInfra {

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
