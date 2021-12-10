package co.edu.icesi.awc.back.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.awc.back.model.person.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	
}
