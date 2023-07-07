package com.person.persondesafio.infra;

import com.person.persondesafio.core.entities.Address;

public interface AddressInfra {
    Address validationAddress(Address address);
}
