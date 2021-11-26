package integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.model.person.Address;
import co.edu.icesi.awc.model.person.Addresstype;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Businessentityaddress;
import co.edu.icesi.awc.model.person.BusinessentityaddressPK;
import co.edu.icesi.awc.repository.AddressRepository;
import co.edu.icesi.awc.repository.AddresstypeRepository;
import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.service.BusinessentityaddressService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class BusinessentityaddressServiceTest {

	private BusinessentityaddressService businessentityaddressService;
	
	private AddressRepository addressRepository;
	private BusinessentityRepository businessentityRepository;
	private AddresstypeRepository addresstypeRepository;
	
	@Autowired
	public BusinessentityaddressServiceTest(BusinessentityaddressService businessentityaddressService,
			AddressRepository addressRepository,
			BusinessentityRepository businessentityRepository,
			AddresstypeRepository addresstypeRepository) {
		this.businessentityaddressService =businessentityaddressService;
		this.addressRepository =addressRepository;
		this.businessentityRepository =businessentityRepository;
		this.addresstypeRepository =addresstypeRepository;
	}
	
	@Nested
	@Tag("create")
	@TestMethodOrder(OrderAnnotation.class)
	class Create{
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			Address address1 = new Address();
			addressRepository.save(address1);
			Businessentity businessentity1 = new Businessentity();
			businessentityRepository.save(businessentity1);
			Addresstype addresstype1 = new Addresstype();
			addresstypeRepository.save(addresstype1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 1);
			
			//Asserts
			assertNotNull(businessentityaddressSave);
			
			Optional<Address> addressSave = addressRepository.findById(1);
			Optional<Businessentity> businessentitySave = businessentityRepository.findById(1);
			Optional<Addresstype> addresstypeSave = addresstypeRepository.findById(1);
			
			assertEquals(businessentityaddressSave.getAddress().getAddressid(), addressSave.get().getAddressid());
			//assertEquals(addressSave.get().getBusinessentityaddresses().get(0).getId(), businessentityaddressSave.getId());
			assertEquals(businessentityaddressSave.getBusinessentity().getBusinessentityid(), businessentitySave.get().getBusinessentityid());
			//assertEquals(businessentitySave.get().getBusinessentityaddresses().get(0).getId(), businessentityaddressSave.getId());
			assertEquals(businessentityaddressSave.getAddresstype().getAddresstypeid(), addresstypeSave.get().getAddresstypeid());
			//assertEquals(addresstypeSave.get().getBusinessentityaddresses().get(0).getId(), businessentityaddressSave.getId());
		}
		
		@Test
		@Order(2)
		public void saveTestWrongAddress() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 2, 1, 1);
			
			//Asserts
			assertNull(businessentityaddressSave);
		}
		
		@Test
		@Order(3)
		public void saveTestWrongAddresstype() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 2, 1);
			
			//Asserts
			assertNull(businessentityaddressSave);
		}
		
		@Test
		@Order(4)
		public void saveTestWrongBusinessentity() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 2);
			
			//Asserts
			assertNull(businessentityaddressSave);
		}
		
	}
	
	@Nested
	@Tag("update")
	@TestMethodOrder(OrderAnnotation.class)
	class Update{
		@Test
		@Order(1)
		public void updateTestCorrect() {
			//Set Up
			Businessentityaddress businessentityaddress1 = businessentityaddressService.findByPK(1,1,1).get();
			businessentityaddress1.setRowguid(1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.update(businessentityaddress1);
			
			//Asserts
			assertNotNull(businessentityaddressSave);
			
			assertEquals(businessentityaddressSave.getRowguid(), 1);
		}
		
		@Test
		@Order(2)
		public void updateTestWrongNotFound() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.update(businessentityaddress1);
			
			//Asserts
			assertNull(businessentityaddressSave);
		}
	}
}
