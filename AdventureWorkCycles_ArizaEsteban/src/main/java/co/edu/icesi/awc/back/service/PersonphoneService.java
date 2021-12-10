package co.edu.icesi.awc.back.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.icesi.awc.back.dao.PersonphoneDAO;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.model.person.PersonphonePK;
import co.edu.icesi.awc.back.model.person.Phonenumbertype;
import co.edu.icesi.awc.back.repository.PersonRepository;
import co.edu.icesi.awc.back.repository.PersonphoneRepository;
import co.edu.icesi.awc.back.repository.PhonenumbertypeRepository;

@Service
public class PersonphoneService{
	//MainRepo
	private PersonphoneDAO personphoneRepository;
	//OtherRepos
	private PersonRepository personRepository;
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	
	//Constructor
	public PersonphoneService(PersonphoneDAO personphoneRepository, PersonRepository personRepository, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.personphoneRepository = personphoneRepository;
		this.personRepository = personRepository;
		this.phonenumbertypeRepository = phonenumbertypeRepository;
	}
	
	//Methods
	//Main Methods
	public Personphone save(Personphone entity, String phonenumber, Integer businessentityid, Integer phonenumbertypeid) {
		
		Personphone sPersonphone = null;
		
		boolean phonenumberV = phonenumber.length() == 10;
		
		if(phonenumberV) {
			Optional<Person> optionalPerson = this.personRepository.findById(businessentityid);
			Optional<Phonenumbertype> optionalPhonenumbertype = this.phonenumbertypeRepository.findById(phonenumbertypeid);
			
			if(optionalPerson.isPresent() && optionalPhonenumbertype.isPresent()) {
				entity.setModifieddate(Timestamp.from(Instant.now()));
				
				entity.setPerson(optionalPerson.get());
				entity.setPhonenumbertype(optionalPhonenumbertype.get());
				
				PersonphonePK pk = new PersonphonePK();
				pk.setPhonenumber(phonenumber);
				pk.setBusinessentityid(businessentityid);
				pk.setPhonenumbertypeid(phonenumbertypeid);
				entity.setId(pk);
				
				sPersonphone = this.personphoneRepository.save(entity);
			}
		}
		
		return sPersonphone;
	}
	
	public Personphone update(Personphone entity) {
		Personphone entityActual = null;
		
		if(entity.getId() != null) {
			Optional<Personphone> optionalEntity = personphoneRepository.findById(entity.getId());
			if(optionalEntity.isPresent()) {
				entityActual = save(entity, entity.getId().getPhonenumber(), entity.getId().getBusinessentityid(), entity.getId().getPhonenumbertypeid());
			}
		}
		
		return entityActual;
	}
	//...
	
	public Optional<Personphone> findByPK(Integer businessentityid, String phonenumber, Integer phonenumbertypeid) {
		PersonphonePK id = new PersonphonePK();
		id.setBusinessentityid(businessentityid);
		id.setPhonenumber(phonenumber);
		id.setPhonenumbertypeid(phonenumbertypeid);
		
		return personphoneRepository.findById(id);
	}
	
	public Iterable<Personphone> findAll() {
		return personphoneRepository.findAll();
	}
	
	public void delete(Personphone entity) {
		personphoneRepository.delete(entity);
	}
	
}
