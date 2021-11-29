package co.edu.icesi.awc.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.dao.PersonDAO;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Person;

@Service
public class PersonService{
	//MainRepo
	private PersonDAO personRepository;
	
	//OtherRepos
	private BusinessentityRepository businessentityRepository;
	
	//Constructor
	@Autowired
	public PersonService(PersonDAO personRepository, BusinessentityRepository businessentityRepository) {
		this.personRepository = personRepository;
		this.businessentityRepository = businessentityRepository;
	}

	//Methods
	//Main Methods---
	public Person save(Person entity) {
		Person sPerson = null;
		
		boolean firstnameV = (entity.getFirstname() != null) && (entity.getFirstname().length() >= 3);
		boolean lastnameV = (entity.getLastname() != null) && (entity.getLastname().length() >= 3);
		
		if(firstnameV && lastnameV){
			entity.setModifieddate(Timestamp.from(Instant.now()));
			
			if(entity.getBusinessentity() == null) {
				//Businessentity
				Businessentity newBusinessentity = new Businessentity();
				newBusinessentity.setModifieddate(entity.getModifieddate());
				
				newBusinessentity = this.businessentityRepository.save(newBusinessentity);
				
				entity.setBusinessentity(newBusinessentity);
				//...
			}
			
			sPerson = this.personRepository.save(entity);
		}
		
		return sPerson;
	}
	
	public Person update(Person entity) {
		Person entityActual = null;
		
		if(entity.getBusinessentityid() != null) {
			Optional<Person> optinalEntity = personRepository.findById(entity.getBusinessentityid());
			if(optinalEntity.isPresent()){
				entityActual = save(entity);
			}
		}
		
		return entityActual;
	}
	//---
	
	public Optional<Person> findByPK(Integer id) {
		return personRepository.findById(id);
	}
	
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}
	
	public void delete(Person entity) {
		personRepository.delete(entity);
		
	}
	
}
