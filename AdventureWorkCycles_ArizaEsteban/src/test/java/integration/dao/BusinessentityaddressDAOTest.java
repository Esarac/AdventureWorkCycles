package integration.dao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.dao.BusinessentityaddressDAO;
import co.edu.icesi.awc.model.person.Address;
import co.edu.icesi.awc.model.person.Addresstype;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Businessentityaddress;
import co.edu.icesi.awc.model.person.BusinessentityaddressPK;
import co.edu.icesi.awc.repository.AddressRepository;
import co.edu.icesi.awc.repository.AddresstypeRepository;
import co.edu.icesi.awc.repository.BusinessentityRepository;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class BusinessentityaddressDAOTest {
	
	private BusinessentityaddressDAO businessentityaddressDao;
	
	private AddressRepository addressRepository;
	private AddresstypeRepository addresstypeRepository;
	private BusinessentityRepository businessentityRepository;
	
	@Autowired
	public BusinessentityaddressDAOTest(BusinessentityaddressDAO businessentityaddressDao,
			AddressRepository addressRepository, AddresstypeRepository addresstypeRepository, BusinessentityRepository businessentityRepository) {
		this.businessentityaddressDao = businessentityaddressDao;
		
		this.addressRepository = addressRepository;
		this.addresstypeRepository = addresstypeRepository;
		this.businessentityRepository = businessentityRepository;
	}
	
	private Address[] addressSetUp() {
		Address[] addresses = new Address[3];
		
		addresses[0] = new Address();
		addresses[0].setAddressline1("Calle 15 #121-25");
		addresses[0].setCity("Medellin");
		addresses[0].setPostalcode("050021");
		
		addresses[1] = new Address();
		addresses[1].setAddressline1("Carrera 124");
		addresses[1].setCity("Bogota");
		addresses[1].setPostalcode("110211");
		
		addresses[2] = new Address();
		addresses[2].setAddressline1("Cl. 18 #122-135");
		addresses[2].setCity("Cali");
		addresses[2].setPostalcode("760008");
		
		addressRepository.save(addresses[0]);
		addressRepository.save(addresses[1]);
		addressRepository.save(addresses[2]);
		
		return addresses;
	}
	
	private Addresstype[] addresstypeSetUp() {
		Addresstype[] addresstypes = new Addresstype[3];
				
		addresstypes[0] = addresstypeRepository.save(new Addresstype());
		addresstypes[1] = addresstypeRepository.save(new Addresstype());
		addresstypes[2] = addresstypeRepository.save(new Addresstype());
		
		return addresstypes;
	}
	
	private Businessentity[] businessentitySetUp() {
		Businessentity[] businessentities = new Businessentity[3];
		
		businessentities[0] = businessentityRepository.save(new Businessentity());
		businessentities[1] = businessentityRepository.save(new Businessentity());
		businessentities[2] = businessentityRepository.save(new Businessentity());
	
		return businessentities;
	}
	
	private BusinessentityaddressPK[] idSetUp() {
		BusinessentityaddressPK[] ids = new BusinessentityaddressPK[3];
		
		ids[0] = new BusinessentityaddressPK(); 
		ids[0].setAddressid(1);
		ids[0].setAddresstypeid(1);
		ids[0].setBusinessentityid(1);
		
		ids[1] = new BusinessentityaddressPK(); 
		ids[1].setAddressid(2);
		ids[1].setAddresstypeid(2);
		ids[1].setBusinessentityid(2);
		
		ids[2] = new BusinessentityaddressPK(); 
		ids[2].setAddressid(3);
		ids[2].setAddresstypeid(3);
		ids[2].setBusinessentityid(3);
		
		return ids;
	}
	
	@Test
	@Order(1)
	public void saveTest() {
		//Set Up
		Address[] addresses = addressSetUp();
		Addresstype[] addresstypes = addresstypeSetUp();
		Businessentity[] businessentities = businessentitySetUp();
		BusinessentityaddressPK[] ids = idSetUp();
		
		Businessentityaddress businessentityaddress1 = new Businessentityaddress();
		businessentityaddress1.setAddress(addresses[0]);
		businessentityaddress1.setAddresstype(addresstypes[0]);
		businessentityaddress1.setBusinessentity(businessentities[0]);
		businessentityaddress1.setId(ids[0]);
		
		Businessentityaddress businessentityaddress2 = new Businessentityaddress();
		businessentityaddress2.setAddress(addresses[1]);
		businessentityaddress2.setAddresstype(addresstypes[1]);
		businessentityaddress2.setBusinessentity(businessentities[1]);
		businessentityaddress2.setId(ids[1]);
		
		Businessentityaddress businessentityaddress3 = new Businessentityaddress();
		businessentityaddress3.setAddress(addresses[2]);
		businessentityaddress3.setAddresstype(addresstypes[2]);
		businessentityaddress3.setBusinessentity(businessentities[2]);
		businessentityaddress3.setId(ids[2]);
		
		businessentityaddressDao.save(businessentityaddress1);
		businessentityaddressDao.save(businessentityaddress2);
		businessentityaddressDao.save(businessentityaddress3);
		
		List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
		
		//Assert
		assertEquals(businessentityaddresses.size(), 3);
		
		assertEquals(businessentityaddresses.get(0).getId(), ids[0]);
		assertEquals(businessentityaddresses.get(1).getId(), ids[1]);
		assertEquals(businessentityaddresses.get(2).getId(), ids[2]);
	}
	
	@Test
	@Order(2)
	public void findByIdTest() {
		//Set Up
		BusinessentityaddressPK[] ids = idSetUp();
		
		BusinessentityaddressPK id4 = new BusinessentityaddressPK(); 
		id4.setAddressid(1);
		id4.setAddresstypeid(2);
		id4.setBusinessentityid(3);
		
		//Assert
		assertNotNull(businessentityaddressDao.findById(ids[0]));
		assertNotNull(businessentityaddressDao.findById(ids[1]));
		assertNotNull(businessentityaddressDao.findById(ids[2]));
		assertNull(businessentityaddressDao.findById(id4));
	}
	
	@Test
	@Order(3)
	public void findAllTest() {
		//Set Up
		BusinessentityaddressPK[] ids = idSetUp();
		
		List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
		
		//Assert
		assertEquals(businessentityaddresses.size(), 3);
		
		assertEquals(businessentityaddresses.get(0).getId(), ids[0]);
		assertEquals(businessentityaddresses.get(1).getId(), ids[1]);
		assertEquals(businessentityaddresses.get(2).getId(), ids[2]);
	}
	
	@Test
	@Order(4)
	public void deleteTest() {
		//Set Up
		BusinessentityaddressPK[] ids = idSetUp();
		
		Businessentityaddress businessentityaddress1 = businessentityaddressDao.findById(ids[0]);
		
		businessentityaddressDao.delete(businessentityaddress1);
		
		List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
		
		//Assert
		assertEquals(businessentityaddresses.size(), 2);
		
		assertEquals(businessentityaddresses.get(0).getId(), ids[1]);
		assertEquals(businessentityaddresses.get(1).getId(), ids[2]);
	}
	
	@Test
	@Order(5)
	public void deleteByIdTest() {
		//Set Up
		BusinessentityaddressPK[] ids = idSetUp();
		
		businessentityaddressDao.deleteById(ids[2]);
		
		List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
		
		//Assert
		assertEquals(businessentityaddresses.size(), 1);
		
		assertEquals(businessentityaddresses.get(0).getId(), ids[1]);
	}
}
