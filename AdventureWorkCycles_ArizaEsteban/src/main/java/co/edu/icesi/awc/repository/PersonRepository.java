package co.edu.icesi.awc.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.awc.model.person.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	
}
