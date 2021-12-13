package co.edu.icesi.awc.back.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import co.edu.icesi.awc.back.dao.PersonDAO;
import co.edu.icesi.awc.back.model.person.Businessentity;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.repository.BusinessentityRepository;

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
	//~Create
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
	
	//~Update
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
	
	//~Read
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}
	
	public Optional<Person> findByPK(Integer id) {
		return personRepository.findById(id);
	}
	
	public Iterable<Person> findByTitle(String title){
		return personRepository.findByTitle(title);
	}
	
	public Iterable<Person> findByPersontype(String persontype){
		return personRepository.findByPersontype(persontype);
	}
	
	public Iterable<Pair<Person,Long>> findBySpecialQuery(Timestamp start, Timestamp end){
		@SuppressWarnings("unchecked")
		List<Object[]> data = (List<Object[]>) personRepository.specialQuery(start, end);
		
		ArrayList<Pair<Person,Long>> tuples = new ArrayList<>();
		data.forEach(d -> {
			tuples.add(Pair.with((Person)d[0],(Long)d[1]));
		});
		
		return tuples;
	}
	
	//~Delete
	public void delete(Person entity) {
		personRepository.delete(entity);
	}
	
	public void delete(Integer id) {
		personRepository.deleteById(id);
	}
	
}
