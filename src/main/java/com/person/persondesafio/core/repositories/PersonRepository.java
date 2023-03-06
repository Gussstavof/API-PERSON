package com.person.persondesafio.core.repositories;

import com.person.persondesafio.core.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
