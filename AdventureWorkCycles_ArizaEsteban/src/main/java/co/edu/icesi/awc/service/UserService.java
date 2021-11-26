package co.edu.icesi.awc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.repository.PersonRepository;
import co.edu.icesi.awc.security.UserApp;
import co.edu.icesi.awc.security.UserRepository;

@Service
public class UserService {
	//MainRepo
	private UserRepository userRepository;
	
	//Constructor
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//Methods
	//Main Methods---
	public UserApp save(UserApp entity) {
		return this.userRepository.save(entity);
	}
	
	public UserApp update(UserApp entity) {
		UserApp entityActual = null;
		
		Optional<UserApp> optinalEntity = findByPK(entity.getId());
		if(optinalEntity.isPresent()){
			entityActual = save(entity);
		}
		
		return entityActual;
	}
	//---
	
	public Optional<UserApp> findByPK(Integer id) {
		return this.userRepository.findById(id);
	}
	
	public Iterable<UserApp> findAll() {
		return this.userRepository.findAll();
	}
	
	public void delete(UserApp entity) {
		this.userRepository.delete(entity);
		
	}
	
}
