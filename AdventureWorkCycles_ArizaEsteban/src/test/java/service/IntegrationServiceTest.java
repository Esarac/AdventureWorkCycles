package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
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
import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Addresstype;
import co.edu.icesi.awc.back.model.person.Businessentity;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.model.person.Phonenumbertype;
import co.edu.icesi.awc.back.model.person.Stateprovince;
import co.edu.icesi.awc.back.repository.AddressRepository;
import co.edu.icesi.awc.back.repository.AddresstypeRepository;
import co.edu.icesi.awc.back.repository.BusinessentityRepository;
import co.edu.icesi.awc.back.repository.PersonRepository;
import co.edu.icesi.awc.back.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.back.repository.StateprovinceRepository;
import co.edu.icesi.awc.back.service.AddressService;
import co.edu.icesi.awc.back.service.BusinessentityaddressService;
import co.edu.icesi.awc.back.service.PersonService;
import co.edu.icesi.awc.back.service.PersonphoneService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class IntegrationServiceTest {
	
	//Service
	private PersonService personService;
	private AddressService addressService;
	private BusinessentityaddressService businessentityaddressService;
	private PersonphoneService personphoneService;
	
	//Aux
	private BusinessentityRepository businessentityRepository;
	private StateprovinceRepository stateprovinceRepository;
	private AddresstypeRepository addresstypeRepository;
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	@Autowired
	public IntegrationServiceTest(PersonService personService, AddressService addressService, BusinessentityaddressService businessentityaddressService, PersonphoneService personphoneService,
			BusinessentityRepository businessentityRepository, StateprovinceRepository stateprovinceRepository, AddresstypeRepository addresstypeRepository, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.personService = personService;
		this.addressService = addressService;
		this.businessentityaddressService = businessentityaddressService;
		this.personphoneService = personphoneService;
		
		this.businessentityRepository = businessentityRepository;
		this.stateprovinceRepository = stateprovinceRepository;
		this.addresstypeRepository = addresstypeRepository;
		this.phonenumbertypeRepository = phonenumbertypeRepository;
	}
	
	@Nested
	@Tag("Person")
	@TestMethodOrder(OrderAnnotation.class)
	public class A_PersonServiceTest {
		
		@Nested
		@Tag("Create")
		@TestMethodOrder(OrderAnnotation.class)
		class Create{
			@Test
			@Order(1)
			public void saveTestCorrect() {
				//Set Up
				Person person1 = new Person();
				person1.setFirstname("Esteban");
				person1.setLastname("Ariza");
				
				//Method
				Person personSaver = personService.save(person1);
				
				//Asserts
				assertNotNull(personSaver);
				assertEquals(personSaver.getFirstname(), "Esteban");
				assertEquals(personSaver.getLastname(), "Ariza");
				
				//Terminar
				Optional<Businessentity> businessentitySaver = businessentityRepository.findById(1);
				
				assertTrue(businessentitySaver.isPresent());
				assertEquals(personSaver.getBusinessentity().getBusinessentityid(), businessentitySaver.get().getBusinessentityid());
				assertEquals(businessentitySaver.get().getPerson().getBusinessentityid(), personSaver.getBusinessentityid());
			}
			
			@Test
			@Order(2)
			public void saveTestWrongFirstname() {
				//Set Up
				Person person1 = new Person();
				person1.setFirstname("Es");
				person1.setLastname("Ariza");
				
				Businessentity businessentity1 = new Businessentity();
				person1.setBusinessentity(businessentity1);
				
				//Method
				Person personSaver = personService.save(person1);
				
				//Asserts
				assertNull(personSaver);
				
				Optional<Businessentity> businessentitySaver = businessentityRepository.findById(2);
				
				assertTrue(businessentitySaver.isEmpty());
			}
			
			@Test
			@Order(3)
			public void saveTestWrongLastname() {
				//Set Up
				Person person1 = new Person();
				person1.setFirstname("Esteban");
				person1.setLastname("Ar");
				
				Businessentity businessentity1 = new Businessentity();
				person1.setBusinessentity(businessentity1);
				
				//Method
				Person personSaver = personService.save(person1);
				
				//Asserts
				assertNull(personSaver);
				
				Optional<Businessentity> businessentitySaver = businessentityRepository.findById(2);
				
				assertTrue(businessentitySaver.isEmpty());
			}
		}
		
		@Nested
		@Tag("Update")
		@TestMethodOrder(OrderAnnotation.class)
		class Update{
			@Test
			@Order(1)
			public void updateTestCorrect() {
				//Set Up
				Person person1 = personService.findByPK(1).get();
				person1.setFirstname("Isabella");
				person1.setLastname("Villamil");
				
				//Method
				Person personSaver = personService.update(person1);
				
				//Asserts
				assertNotNull(personSaver);
				assertEquals(personSaver.getFirstname(), "Isabella");
				assertEquals(personSaver.getLastname(), "Villamil");
			}
			
			@Test
			@Order(3)
			public void updateTestWrongFirstname() {
				//Set Up
				Person person1 = personService.findByPK(1).get();
				person1.setFirstname("Is");
				person1.setLastname("Villamil");
				
				//Method
				Person personSaver = personService.update(person1);
				
				//Asserts
				assertNull(personSaver);
			}
			
			@Test
			@Order(4)
			public void updateTestWrongLastname() {
				//Set Up
				Person person1 = personService.findByPK(1).get();
				person1.setFirstname("Isabella");
				person1.setLastname("Vi");
				
				//Method
				Person personSaver = personService.update(person1);
				
				//Asserts
				assertNull(personSaver);
			}
			
			@Test
			@Order(5)
			public void updateTestWrongNotFound() {
				//Set Up
				Person person1 = new Person();
				person1.setBusinessentityid(2);
				person1.setFirstname("Isabella");
				person1.setLastname("Villamil");
				
				//Method
				Person personSaver = personService.update(person1);
				
				//Asserts
				assertNull(personSaver);
			}
		}
	}
	
	@Nested
	@Tag("Address")
	@TestMethodOrder(OrderAnnotation.class)
	public class B_AddressServiceTest {
		
		@Nested
		@Tag("Create")
		@TestMethodOrder(OrderAnnotation.class)
		class Create{
			@Test
			@Order(1)
			public void saveTestCorrect() {
				//Set up
				Address address1 = new Address();
				address1.setAddressline1("Calle 15 #121-25");
				address1.setCity("Cali");
				address1.setPostalcode("760008");
				
				Stateprovince stateprovince1 = new Stateprovince();
				stateprovinceRepository.save(stateprovince1);
				
				//Method
				Address addressSave =  addressService.save(address1, 1);
				
				//Asserts
				assertNotNull(addressSave);
				assertEquals("Calle 15 #121-25", addressSave.getAddressline1());
				assertEquals("Cali", addressSave.getCity());
				assertEquals("760008", addressSave.getPostalcode());
				
				Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(1);
				
				assertEquals(addressSave.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
				//assertEquals(stateprovinceSave.get().getAddresses().get(0).getAddressid(), addressSave.getAddressid());
			}
			
			@Test
			@Order(2)
			public void saveTestWrongAddressline1() {
				//Set up
				Address address1 = new Address();
				address1.setAddressline1("   ");
				address1.setCity("Cali");
				address1.setPostalcode("760008");
				
				//Method
				Address addressSave =  addressService.save(address1, 1);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(3)
			public void saveTestWrongCity() {
				//Set up
				Address address1 = new Address();
				address1.setAddressline1("Calle 15 #121-25");
				address1.setCity("   ");
				address1.setPostalcode("760008");
				
				//Method
				Address addressSave =  addressService.save(address1, 1);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(4)
			public void saveTestWrongPostalcode() {
				//Set up
				Address address1 = new Address();
				address1.setAddressline1("Calle 15 #121-25");
				address1.setCity("Cali");
				address1.setPostalcode("   ");
				
				//Method
				Address addressSave =  addressService.save(address1, 1);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(5)
			public void saveTestWrongStateprovince() {
				//Set up
				Address address1 = new Address();
				address1.setAddressline1("Calle 15 #121-25");
				address1.setCity("Cali");
				address1.setPostalcode("760008");
				
				//Method
				Address addressSave =  addressService.save(address1, 2);
				
				//Asserts
				assertNull(addressSave);
			}
		}
		
		@Nested
		@Tag("Update")
		@TestMethodOrder(OrderAnnotation.class)
		class Update{
			@Test
			@Order(1)
			public void updateTestCorrect() {
				//Set up
				Address address1 = addressService.findByPK(1).get();
				address1.setAddressline1("Calle 5");
				address1.setCity("Bogota");
				address1.setPostalcode("760009");
				
				Stateprovince stateprovince1 = new Stateprovince();
				stateprovinceRepository.save(stateprovince1);
				
				//Method
				Address addressSave =  addressService.update(address1, 2);
				
				//Asserts
				assertNotNull(addressSave);
				assertEquals("Calle 5", addressSave.getAddressline1());
				assertEquals("Bogota", addressSave.getCity());
				assertEquals("760009", addressSave.getPostalcode());

				Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(2);
				
				assertEquals(addressSave.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
				//assertEquals(stateprovinceSave.get().getAddresses().get(0).getAddressid(), addressSave.getAddressid());
				
			}
			
			@Test
			@Order(2)
			public void updateTestWrongAddressline1() {
				//Set up
				Address address1 = addressService.findByPK(1).get();
				address1.setAddressline1("   ");
				address1.setCity("Bogota");
				address1.setPostalcode("760009");
				
				//Method
				Address addressSave =  addressService.update(address1, 2);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(3)
			public void updateTestWrongCity() {
				//Set up
				Address address1 = addressService.findByPK(1).get();
				address1.setAddressline1("Calle 5");
				address1.setCity("   ");
				address1.setPostalcode("760009");
				
				//Method
				Address addressSave =  addressService.update(address1, 2);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(4)
			public void updateTestWrongPostalcode() {
				//Set up
				Address address1 = addressService.findByPK(1).get();
				address1.setAddressline1("Calle 5");
				address1.setCity("Bogota");
				address1.setPostalcode("   ");
				
				//Method
				Address addressSave =  addressService.update(address1, 2);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(5)
			public void updateTestWrongStateprovince() {
				//Set up
				Address address1 = addressService.findByPK(1).get();
				address1.setAddressline1("Calle 5");
				address1.setCity("Bogota");
				address1.setPostalcode("760009");
				
				//Method
				Address addressSave =  addressService.update(address1, 3);
				
				//Asserts
				assertNull(addressSave);
			}
			
			@Test
			@Order(6)
			public void updateTestWrongNotFound() {
				//Set up
				Address address1 = new Address();
				address1.setAddressid(2);
				address1.setAddressline1("Calle 5");
				address1.setCity("Bogota");
				address1.setPostalcode("760009");
				
				//Method
				Address addressSave =  addressService.update(address1, 2);
				
				//Asserts
				assertNull(addressSave);
			}
		}
	}
	
	@Nested
	@Tag("Businessentityaddress")
	@TestMethodOrder(OrderAnnotation.class)
	public class C_BusinessentityaddressServiceTest {
		@Nested
		@Tag("Create")
		@TestMethodOrder(OrderAnnotation.class)
		class Create{
			@Test
			@Order(1)
			public void saveTestCorrect() {
				//Set Up
				Businessentityaddress businessentityaddress1 = new Businessentityaddress();
				
				Addresstype addresstype1 = new Addresstype();
				addresstypeRepository.save(addresstype1);
				
				//Method
				Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 1);
				
				//Asserts
				assertNotNull(businessentityaddressSave);
				
				Optional<Address> addressSave = addressService.findByPK(1);
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
				Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 2);
				
				//Asserts
				assertNull(businessentityaddressSave);
			}
			
			@Test
			@Order(4)
			public void saveTestWrongBusinessentity() {
				//Set Up
				Businessentityaddress businessentityaddress1 = new Businessentityaddress();
				
				//Method
				Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 2, 1);
				
				//Asserts
				assertNull(businessentityaddressSave);
			}
			
		}
		
		@Nested
		@Tag("Update")
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

	@Nested
	@Tag("Personphone")
	@TestMethodOrder(OrderAnnotation.class)
	public class D_PersonphoneServiceTest {
		@Nested
		@Tag("Create")
		@TestMethodOrder(OrderAnnotation.class)
		class Create{
			@Test
			@Order(1)
			public void saveTestCorrect() {
				//Set Up
				Personphone personphone1 = new Personphone();
				
				Phonenumbertype phonenumbertype1 = new Phonenumbertype();
				phonenumbertypeRepository.save(phonenumbertype1);
				
				//Method
				Personphone personphoneSave = personphoneService.save(personphone1,"3183741903", 1, 1);
				
				//Asserts
				assertNotNull(personphoneSave);
				assertEquals(personphoneSave.getId().getPhonenumber(), "3183741903");
				
				Optional<Person> personSave = personService.findByPK(1);
				Optional<Phonenumbertype> phonenumbertypeSave = phonenumbertypeRepository.findById(1);
				
				assertEquals(personphoneSave.getPerson().getBusinessentityid(), personSave.get().getBusinessentityid());
				//assertEquals(personSave.get().getPersonphones().get(0).getId(), personphoneSave.getId());
				assertEquals(personphoneSave.getPhonenumbertype().getPhonenumbertypeid(), phonenumbertypeSave.get().getPhonenumbertypeid());
				//assertEquals(phonenumbertypeSave.get().getPersonphones().get(0).getId(), personphoneSave.getId());
			}
			
			@Test
			@Order(2)
			public void saveTestWrongPhonenumber() {
				//Set Up
				Personphone personphone1 = new Personphone();
				
				//Method
				Personphone personphoneSave = personphoneService.save(personphone1, "4847803", 1, 1);
				
				//Asserts
				assertNull(personphoneSave);
			}
			
			@Test
			@Order(3)
			public void saveTestWrongPerson() {
				//Set Up
				Personphone personphone1 = new Personphone();
				
				//Method
				Personphone personphoneSave = personphoneService.save(personphone1, "3183741903", 2, 1);
				
				//Asserts
				assertNull(personphoneSave);
			}
			
			@Test
			@Order(4)
			public void saveTestWrongPhonenumbertype() {
				//Set Up
				Personphone personphone1 = new Personphone();
				
				//Method
				Personphone personphoneSave = personphoneService.save(personphone1, "3183741903", 1, 2);
				
				//Asserts
				assertNull(personphoneSave);
			}
		}
		
		@Nested
		@Tag("Update")
		@TestMethodOrder(OrderAnnotation.class)
		class Update{
			@Test
			@Order(1)
			public void updateTestCorrect() {
				//Set Up
				Personphone personphone1 = personphoneService.findByPK(1, "3183741903", 1).get();
				Timestamp time = personphone1.getModifieddate();
				
				//Method
				Personphone personphoneSave = personphoneService.update(personphone1);
				
				//Asserts
				assertNotNull(personphoneSave);
				assertEquals(personphoneSave.getId().getPhonenumber(), "3183741903");
				assertNotEquals(personphoneSave.getModifieddate(), time);
			}
			
			@Test
			@Order(2)
			public void updateTestWrongNotFound() {
				//Set Up
				Personphone personphone1 = new Personphone();
				
				//Method
				Personphone personphoneSave = personphoneService.update(personphone1);
				
				//Asserts
				assertNull(personphoneSave);
			}
		}
	}
}
