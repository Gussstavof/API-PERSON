package com.person.persondesafio.core.repositories;

import com.person.persondesafio.core.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
