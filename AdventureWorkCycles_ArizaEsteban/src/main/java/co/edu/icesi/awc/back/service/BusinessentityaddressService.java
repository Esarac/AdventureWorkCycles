package co.edu.icesi.awc.back.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.awc.back.dao.BusinessentityaddressDAO;
import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Addresstype;
import co.edu.icesi.awc.back.model.person.Businessentity;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.BusinessentityaddressPK;
import co.edu.icesi.awc.back.repository.AddressRepository;
import co.edu.icesi.awc.back.repository.AddresstypeRepository;
import co.edu.icesi.awc.back.repository.BusinessentityRepository;
import co.edu.icesi.awc.back.repository.BusinessentityaddressRepository;

@Service
public class BusinessentityaddressService{
	//MainRepo
	private BusinessentityaddressDAO businessentityaddressRepository;
	//OtherRepos
	private AddressRepository addressRepository;
	private BusinessentityRepository businessentityRepository;
	private AddresstypeRepository addresstypeRepository;//No decia en el enunciado
	
	
	//Constructor
	public BusinessentityaddressService(BusinessentityaddressDAO businessentityaddressRepository, AddressRepository addressRepository, BusinessentityRepository businessentityRepository, AddresstypeRepository addresstypeRepository) {
		this.businessentityaddressRepository = businessentityaddressRepository;
		this.addressRepository = addressRepository;
		this.businessentityRepository = businessentityRepository;
		this.addresstypeRepository = addresstypeRepository;
	}
	
	//Methods
	//Main Methods---
	public Businessentityaddress save(Businessentityaddress entity, Integer addressid, Integer businessentityid, Integer addresstypeid) {
		Businessentityaddress sBusinessentityaddress = null;
		
		Optional<Address> optionalAddress = this.addressRepository.findById(addressid);
		Optional<Businessentity> optionalBusinessentity = this.businessentityRepository.findById(businessentityid);
		Optional<Addresstype> optionalAddresstype = this.addresstypeRepository.findById(addresstypeid);
		
		if(optionalAddress.isPresent() && optionalBusinessentity.isPresent() && optionalAddresstype.isPresent()) {
			entity.setModifieddate(Timestamp.from(Instant.now()));
			
			entity.setAddress(optionalAddress.get());
			entity.setBusinessentity(optionalBusinessentity.get());
			entity.setAddresstype(optionalAddresstype.get());
			
			BusinessentityaddressPK pk = new BusinessentityaddressPK();
			pk.setAddressid(addressid);
			pk.setAddresstypeid(addresstypeid);
			pk.setBusinessentityid(businessentityid);
			entity.setId(pk);
			
			sBusinessentityaddress = this.businessentityaddressRepository.save(entity);
		}
		
		return sBusinessentityaddress;
	}
	
	public Businessentityaddress update(Businessentityaddress entity) {
		Businessentityaddress entityActual = null;
		
		if(entity.getId() != null){
			Optional<Businessentityaddress> optinalEntity = businessentityaddressRepository.findById(entity.getId());
			if(optinalEntity.isPresent()) {
				entityActual = save(entity, entity.getId().getAddressid(), entity.getId().getBusinessentityid(), entity.getId().getAddresstypeid());
			}
		}
				
		return entityActual;
	}
	//---
	
	public Optional<Businessentityaddress> findByPK(Integer businessentityid, Integer addressid, Integer addresstypeid) {
		BusinessentityaddressPK id = new BusinessentityaddressPK();
		id.setBusinessentityid(businessentityid);
		id.setAddressid(addressid);
		id.setAddresstypeid(addresstypeid);
		
		return businessentityaddressRepository.findById(id);
	}
	
	public Iterable<Businessentityaddress> findAll() {
		return businessentityaddressRepository.findAll();
	}
	
	public void delete(Businessentityaddress entity) {
		businessentityaddressRepository.delete(entity);
	}
}
