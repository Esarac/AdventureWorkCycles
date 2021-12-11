package co.edu.icesi.awc.back.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.awc.back.dao.AddressDAO;
import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Stateprovince;
import co.edu.icesi.awc.back.repository.AddressRepository;
import co.edu.icesi.awc.back.repository.StateprovinceRepository;

@Service
public class AddressService{
	//MainRepo
	private AddressDAO addressRepository;
	//OtherRepos
	private StateprovinceRepository stateprovinceRepository;
	
	//Constructor
	@Autowired
	public AddressService(AddressDAO addressRepository, StateprovinceRepository stateprovinceRepository) {
		this.addressRepository = addressRepository;
		this.stateprovinceRepository = stateprovinceRepository;
	}
	
	//Methods
	//Main Methods---
	public Address save(Address entity, Integer stateprovinceid) {
		Address sAddress = null;
		
		boolean addressline1V = (entity.getAddressline1() != null) && (!entity.getAddressline1().isBlank());
		boolean cityV = (entity.getCity() != null) && (!entity.getCity().isBlank());
		boolean postalcodeV = (entity.getPostalcode() != null) && (!entity.getPostalcode().isBlank());
		
		if(addressline1V && cityV && postalcodeV) {
			Optional<Stateprovince> optional = this.stateprovinceRepository.findById(stateprovinceid);
			if(optional.isPresent()) {
				entity.setModifieddate(Timestamp.from(Instant.now()));
				
				entity.setStateprovince(optional.get());
				
				sAddress = this.addressRepository.save(entity);
			}
		}
		
		return sAddress;
	}
	
	public Address update(Address entity, Integer stateprovinceid) {
		Address entityActual = null;
		
		if(entity.getAddressid() != null) {
			Optional<Address> optinalEntity = addressRepository.findById(entity.getAddressid());
			if(optinalEntity.isPresent()) {
				entityActual = save(entity, stateprovinceid);
			}
		}
		
		return entityActual;
	}
	//---
	
	public Optional<Address> findByPK(Integer id) {
		return addressRepository.findById(id);
	}
	
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}
	
	public void delete(Address entity) {
		addressRepository.delete(entity);
	}
	
	public void delete(Integer addressid) {
		addressRepository.deleteById(addressid);
	}
	
}
