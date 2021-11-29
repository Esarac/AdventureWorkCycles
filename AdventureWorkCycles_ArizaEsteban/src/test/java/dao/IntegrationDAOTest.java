package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;

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
import co.edu.icesi.awc.dao.AddressDAO;
import co.edu.icesi.awc.dao.BusinessentityaddressDAO;
import co.edu.icesi.awc.dao.PersonDAO;
import co.edu.icesi.awc.dao.PersonphoneDAO;
import co.edu.icesi.awc.model.person.Address;
import co.edu.icesi.awc.model.person.Addresstype;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Businessentityaddress;
import co.edu.icesi.awc.model.person.BusinessentityaddressPK;
import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.model.person.Personphone;
import co.edu.icesi.awc.model.person.PersonphonePK;
import co.edu.icesi.awc.model.person.Phonenumbertype;
import co.edu.icesi.awc.repository.AddresstypeRepository;
import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.repository.PhonenumbertypeRepository;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class IntegrationDAOTest {
	
	//DAO
	private PersonDAO personDao;
	private AddressDAO addressDao;
	private BusinessentityaddressDAO businessentityaddressDao;
	private PersonphoneDAO personphoneDao;
	
	//Aux
	private AddresstypeRepository addresstypeRepository;
	private BusinessentityRepository businessentityRepository;
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	//Constructor
	@Autowired
	public IntegrationDAOTest(PersonDAO personDao, AddressDAO addressDao, BusinessentityaddressDAO businessentityaddressDao, PersonphoneDAO personphoneDao,
			AddresstypeRepository addresstypeRepository, BusinessentityRepository businessentityRepository, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.personDao = personDao;
		this.addressDao = addressDao;
		this.businessentityaddressDao = businessentityaddressDao;
		this.personphoneDao = personphoneDao;
		
		this.addresstypeRepository = addresstypeRepository;
		this.businessentityRepository = businessentityRepository;
		this.phonenumbertypeRepository = phonenumbertypeRepository;
	}
	//Methods
	private static BusinessentityaddressPK factoryBusinessentityaddressPK(int addressid, int addresstypeid, int businessentityid) {
		BusinessentityaddressPK id = new BusinessentityaddressPK(); 
		
		id.setAddressid(addressid);
		id.setAddresstypeid(addresstypeid);
		id.setBusinessentityid(businessentityid);
		
		return id;
	}
	
	private static PersonphonePK factoryPersonphonePK(int businessentityid, String phonenumber, int phonenumbertypeid) {
		PersonphonePK id = new PersonphonePK();
		
		id.setBusinessentityid(businessentityid);
		id.setPhonenumber(phonenumber);
		id.setPhonenumbertypeid(phonenumbertypeid);
		
		return id;
	}
	
	//Tests
	@Nested
	@Tag("Create")
	@TestMethodOrder(OrderAnnotation.class)
	public class Create {
		@Nested
		@Tag("Person")
		@TestMethodOrder(OrderAnnotation.class)
		public class A_PersonDAOTest {
			@Test
			@Order(1)
			public void saveTest() {
				//Set Up
				Person person1 = new Person();//:)
				person1.setModifieddate(new Timestamp(0));
				person1.setFirstname("Esteban");
				person1.setLastname("Ariza");
				person1.setTitle("Titulo2");
				person1.setPersontype("Humano");
				
				Person person2 = new Person();
				person2.setModifieddate(new Timestamp(0));
				person2.setFirstname("Isabella");
				person2.setLastname("Villamil");
				person2.setTitle("Titulo1");
				person2.setPersontype("Humano");
				
				Person person3 = new Person();
				person3.setModifieddate(new Timestamp(0));
				person3.setFirstname("Samuel");
				person3.setLastname("Acosta");
				person3.setTitle("Titulo2");
				person3.setPersontype("Alien");
				
				Person person4 = new Person();//:(
				person4.setModifieddate(new Timestamp(0));
				person4.setFirstname("Diana");
				person4.setLastname("Acosta");
				person4.setTitle("Titulo2");
				person4.setPersontype("Alien");
				
				Person person5 = new Person();
				person5.setModifieddate(new Timestamp(0));
				person5.setFirstname("Fredy");
				person5.setLastname("Ariza");
				person5.setTitle("Titulo2");
				person5.setPersontype("Alien");
				
				person1 = personDao.save(person1);//:)
				person2 = personDao.save(person2);
				person3 = personDao.save(person3);
				person4 = personDao.save(person4);//:(
				person5 = personDao.save(person5);
				
				List<Person> persons = personDao.findAll();
				
				//Assert
				assertEquals(persons.size(), 5);
				
				assertEquals(persons.get(0).getBusinessentityid(), person1.getBusinessentityid());
				assertEquals(persons.get(1).getBusinessentityid(), person2.getBusinessentityid());
				assertEquals(persons.get(2).getBusinessentityid(), person3.getBusinessentityid());
				assertEquals(persons.get(3).getBusinessentityid(), person4.getBusinessentityid());
				assertEquals(persons.get(4).getBusinessentityid(), person5.getBusinessentityid());
			}
		}
		
		@Nested
		@Tag("Address")
		@TestMethodOrder(OrderAnnotation.class)
		public class B_AddressDAOTest {
			@Test
			@Order(1)
			public void saveTest() {
				//Set Up
				Address address1 = new Address();//:)
				address1.setAddressline1("Calle 15 #121-25");
				address1.setCity("Medellin");
				address1.setPostalcode("050021");
				address1.setModifieddate(new Timestamp(0));
				
				Address address2 = new Address();
				address2.setAddressline1("Carrera 124");
				address2.setCity("Bogota");
				address2.setPostalcode("110211");
				address2.setModifieddate(new Timestamp(1000));
				
				Address address3 = new Address();
				address3.setAddressline1("Cl. 18 #122-135");
				address3.setCity("Cali");
				address3.setPostalcode("760008");
				address3.setModifieddate(new Timestamp(0));
				
				Address address4 = new Address();
				address4.setAddressline1("Cl. 5 #31-90");
				address4.setCity("Pasto");
				address4.setPostalcode("760009");
				address4.setModifieddate(new Timestamp(500));
				
				Address address5 = new Address();//:(
				address5.setAddressline1("Cl. 5 #31-90");
				address5.setCity("Cali");
				address5.setPostalcode("760007");
				address5.setModifieddate(new Timestamp(500));
				
				Address address6 = new Address();
				address6.setAddressline1("Cl. 5 #31-90");
				address6.setCity("Bogota");
				address6.setPostalcode("760006");
				address6.setModifieddate(new Timestamp(500));
				
				address1 = addressDao.save(address1);//:)
				address2 = addressDao.save(address2);
				address3 = addressDao.save(address3);
				address4 = addressDao.save(address4);
				address5 = addressDao.save(address5);//:(
				address6 = addressDao.save(address6);
				
				List<Address> addresses = addressDao.findAll();
				
				//Assert
				assertEquals(addresses.size(), 6);
				
				assertEquals(addresses.get(0).getAddressid(), address1.getAddressid());
				assertEquals(addresses.get(1).getAddressid(), address2.getAddressid());
				assertEquals(addresses.get(2).getAddressid(), address3.getAddressid());
				assertEquals(addresses.get(3).getAddressid(), address4.getAddressid());
				assertEquals(addresses.get(4).getAddressid(), address5.getAddressid());
				assertEquals(addresses.get(5).getAddressid(), address6.getAddressid());
			}
		}
		
		@Nested
		@Tag("Businessentityaddress")
		@TestMethodOrder(OrderAnnotation.class)
		public class C_BusinessentityaddressDAOTest {
			//Methods
			private void addresstypeSetUp() {	
				addresstypeRepository.save(new Addresstype());
				addresstypeRepository.save(new Addresstype());
				addresstypeRepository.save(new Addresstype());
			}
			
			private void businessentitySetUp(){
				businessentityRepository.save(new Businessentity());
				businessentityRepository.save(new Businessentity());
				businessentityRepository.save(new Businessentity());
			}
			
			//Test
			@Test
			@Order(1)
			public void saveTest() {
				//Set Up
				addresstypeSetUp();
				businessentitySetUp();
				
				List<Address> addresses = addressDao.findAll();
				
				List<Addresstype> addresstypes = (List<Addresstype>) addresstypeRepository.findAll();
				
				List<Businessentity> businessentities = (List<Businessentity>) businessentityRepository.findAll();
				
				Businessentityaddress businessentityaddress1 = new Businessentityaddress();//:)
				businessentityaddress1.setAddress(addresses.get(0));
				businessentityaddress1.setAddresstype(addresstypes.get(0));
				businessentityaddress1.setBusinessentity(businessentities.get(0));
				businessentityaddress1.setId( factoryBusinessentityaddressPK(1,1,1) );
				
				Businessentityaddress businessentityaddress2 = new Businessentityaddress();
				businessentityaddress2.setAddress(addresses.get(1));
				businessentityaddress2.setAddresstype(addresstypes.get(1));
				businessentityaddress2.setBusinessentity(businessentities.get(1));
				businessentityaddress2.setId( factoryBusinessentityaddressPK(2,2,2) );
				
				Businessentityaddress businessentityaddress3 = new Businessentityaddress();
				businessentityaddress3.setAddress(addresses.get(2));
				businessentityaddress3.setAddresstype(addresstypes.get(2));
				businessentityaddress3.setBusinessentity(businessentities.get(2));
				businessentityaddress3.setId( factoryBusinessentityaddressPK(3,3,3) );
				
				Businessentityaddress businessentityaddress4 = new Businessentityaddress();
				businessentityaddress4.setAddress(addresses.get(3));
				businessentityaddress4.setAddresstype(addresstypes.get(2));
				businessentityaddress4.setBusinessentity(businessentities.get(2));
				businessentityaddress4.setId( factoryBusinessentityaddressPK(4,3,3) );
				
				Businessentityaddress businessentityaddress5 = new Businessentityaddress();//:(
				businessentityaddress5.setAddress(addresses.get(0));
				businessentityaddress5.setAddresstype(addresstypes.get(1));
				businessentityaddress5.setBusinessentity(businessentities.get(2));
				businessentityaddress5.setId( factoryBusinessentityaddressPK(1,2,3) );
				
				Businessentityaddress businessentityaddress6 = new Businessentityaddress();
				businessentityaddress6.setAddress(addresses.get(2));
				businessentityaddress6.setAddresstype(addresstypes.get(1));
				businessentityaddress6.setBusinessentity(businessentities.get(0));
				businessentityaddress6.setId( factoryBusinessentityaddressPK(3,2,1) );
				
				businessentityaddress1 = businessentityaddressDao.save(businessentityaddress1);//:)
				businessentityaddress2 = businessentityaddressDao.save(businessentityaddress2);
				businessentityaddress3 = businessentityaddressDao.save(businessentityaddress3);
				businessentityaddress4 = businessentityaddressDao.save(businessentityaddress4);
				businessentityaddress5 = businessentityaddressDao.save(businessentityaddress5);//:(
				businessentityaddress6 = businessentityaddressDao.save(businessentityaddress6);
				
				List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
				
				//Assert
				assertEquals(businessentityaddresses.size(), 6);
				
				assertEquals(businessentityaddresses.get(0).getId(), businessentityaddress1.getId());
				assertEquals(businessentityaddresses.get(1).getId(), businessentityaddress2.getId());
				assertEquals(businessentityaddresses.get(2).getId(), businessentityaddress3.getId());
				assertEquals(businessentityaddresses.get(3).getId(), businessentityaddress4.getId());
				assertEquals(businessentityaddresses.get(4).getId(), businessentityaddress5.getId());
				assertEquals(businessentityaddresses.get(5).getId(), businessentityaddress6.getId());
			}
		}
		
		@Nested
		@Tag("Personphone")
		@TestMethodOrder(OrderAnnotation.class)
		public class D_PersonphoneDAOTest {
			//Methods
			private void phonenumbertypeSetUp(){
				phonenumbertypeRepository.save(new Phonenumbertype());
				phonenumbertypeRepository.save(new Phonenumbertype());
			}
			
			//Test
			@Test
			@Order(1)
			public void saveTest() {
				//Set Up
				phonenumbertypeSetUp();
				
				List<Person> persons = personDao.findAll();
				List<Phonenumbertype> phonenumbertypes = (List<Phonenumbertype>) phonenumbertypeRepository.findAll();
				
				Personphone personphone1 = new Personphone();//:)
				personphone1.setPerson(persons.get(0));
				personphone1.setPhonenumbertype(phonenumbertypes.get(0));
				personphone1.setId( factoryPersonphonePK(1,"3183741903",1) );
				
				Personphone personphone2 = new Personphone();
				personphone2.setPerson(persons.get(1));
				personphone2.setPhonenumbertype(phonenumbertypes.get(1));
				personphone2.setId( factoryPersonphonePK(2,"3158728989",2) );
				
				Personphone personphone3 = new Personphone();
				personphone3.setPerson(persons.get(2));
				personphone3.setPhonenumbertype(phonenumbertypes.get(1));
				personphone3.setId( factoryPersonphonePK(3,"3188031038",2) );
				
				Personphone personphone4 = new Personphone();
				personphone4.setPerson(persons.get(1));
				personphone4.setPhonenumbertype(phonenumbertypes.get(0));
				personphone4.setId( factoryPersonphonePK(2,"3182352827",1) );
				
				Personphone personphone5 = new Personphone();
				personphone5.setPerson(persons.get(2));
				personphone5.setPhonenumbertype(phonenumbertypes.get(1));
				personphone5.setId( factoryPersonphonePK(3,"3115890190",2) );
				
				Personphone personphone6 = new Personphone();//:(
				personphone6.setPerson(persons.get(1));
				personphone6.setPhonenumbertype(phonenumbertypes.get(0));
				personphone6.setId( factoryPersonphonePK(2,"0987654321",1) );
				
				Personphone personphone7 = new Personphone();
				personphone7.setPerson(persons.get(2));
				personphone7.setPhonenumbertype(phonenumbertypes.get(1));
				personphone7.setId( factoryPersonphonePK(3,"1234567890",2) );
				
				personphone1 = personphoneDao.save(personphone1);//:)
				personphone2 = personphoneDao.save(personphone2);
				personphone3 = personphoneDao.save(personphone3);
				personphone4 = personphoneDao.save(personphone4);
				personphone5 = personphoneDao.save(personphone5);
				personphone6 = personphoneDao.save(personphone6);//:(
				personphone7 = personphoneDao.save(personphone7);
				
				List<Personphone> personphones = personphoneDao.findAll();
				
				//Assert
				assertEquals(personphones.size(), 7);
				
				assertEquals(personphones.get(0).getId(), personphone1.getId());
				assertEquals(personphones.get(1).getId(), personphone2.getId());
				assertEquals(personphones.get(2).getId(), personphone3.getId());
				assertEquals(personphones.get(3).getId(), personphone4.getId());
				assertEquals(personphones.get(4).getId(), personphone5.getId());
				assertEquals(personphones.get(5).getId(), personphone6.getId());
				assertEquals(personphones.get(6).getId(), personphone7.getId());
			}
		}
	}
	
	@Nested
	@Tag("Delete")
	@TestMethodOrder(OrderAnnotation.class)
	public class Delete {
		@Nested
		@Tag("Person")
		@TestMethodOrder(OrderAnnotation.class)
		public class A_PersonDAOTest {
			@Test
			@Order(1)
			public void deleteTest() {
				//Set Up
				Person person4 = personDao.findById(4).get();
				
				personDao.delete(person4);
				
				List<Person> persons = personDao.findAll();
				
				//Assert
				assertEquals(persons.size(), 4);
				
				assertEquals(persons.get(0).getBusinessentityid(), 1);
				assertEquals(persons.get(1).getBusinessentityid(), 2);
				assertEquals(persons.get(2).getBusinessentityid(), 3);
				assertEquals(persons.get(3).getBusinessentityid(), 5);
			}
			
			@Test
			@Order(2)
			public void deleteByIdTest() {
				//Set Up
				personDao.deleteById(5);
				
				List<Person> persons = personDao.findAll();
				
				//Assert
				assertEquals(persons.size(), 3);
				
				assertEquals(persons.get(0).getBusinessentityid(), 1);
				assertEquals(persons.get(1).getBusinessentityid(), 2);
				assertEquals(persons.get(2).getBusinessentityid(), 3);
			}
		}
		
		@Nested
		@Tag("Address")
		@TestMethodOrder(OrderAnnotation.class)
		public class B_AddressDAOTest {
			@Test
			@Order(1)
			public void deleteTest() {
				//Set Up
				Address address5 = addressDao.findById(5).get();
				
				addressDao.delete(address5);
				
				List<Address> addresses = addressDao.findAll();
				
				//Assert
				assertEquals(addresses.size(), 5);
				
				assertEquals(addresses.get(0).getAddressid(), 1);
				assertEquals(addresses.get(1).getAddressid(), 2);
				assertEquals(addresses.get(2).getAddressid(), 3);
				assertEquals(addresses.get(3).getAddressid(), 4);
				assertEquals(addresses.get(4).getAddressid(), 6);
			}
			
			@Test
			@Order(2)
			public void deleteByIdTest() {
				//Set Up
				addressDao.deleteById(6);
				
				List<Address> addresses = addressDao.findAll();
				
				//Assert
				assertEquals(addresses.size(), 4);
				
				assertEquals(addresses.get(0).getAddressid(), 1);
				assertEquals(addresses.get(1).getAddressid(), 2);
				assertEquals(addresses.get(2).getAddressid(), 3);
				assertEquals(addresses.get(3).getAddressid(), 4);
			}
		}
		
		@Nested
		@Tag("Businessentityaddress")
		@TestMethodOrder(OrderAnnotation.class)
		public class C_BusinessentityaddressDAOTest {
			@Test
			@Order(1)
			public void deleteTest() {
				//Set Up
				Businessentityaddress businessentityaddress5 = businessentityaddressDao.findById(factoryBusinessentityaddressPK(1,2,3)).get();
				
				businessentityaddressDao.delete(businessentityaddress5);
				
				List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
				
				//Assert
				assertEquals(businessentityaddresses.size(), 5);
				
				assertEquals(businessentityaddresses.get(0).getId(), factoryBusinessentityaddressPK(1,1,1));
				assertEquals(businessentityaddresses.get(1).getId(), factoryBusinessentityaddressPK(2,2,2));
				assertEquals(businessentityaddresses.get(2).getId(), factoryBusinessentityaddressPK(3,3,3));
				assertEquals(businessentityaddresses.get(3).getId(), factoryBusinessentityaddressPK(4,3,3));
				assertEquals(businessentityaddresses.get(4).getId(), factoryBusinessentityaddressPK(3,2,1));
			}
			
			@Test
			@Order(2)
			public void deleteByIdTest() {
				//Set Up
				businessentityaddressDao.deleteById(factoryBusinessentityaddressPK(3,2,1));
				
				List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
				
				//Assert
				assertEquals(businessentityaddresses.size(), 4);
				
				assertEquals(businessentityaddresses.get(0).getId(), factoryBusinessentityaddressPK(1,1,1));
				assertEquals(businessentityaddresses.get(1).getId(), factoryBusinessentityaddressPK(2,2,2));
				assertEquals(businessentityaddresses.get(2).getId(), factoryBusinessentityaddressPK(3,3,3));
				assertEquals(businessentityaddresses.get(3).getId(), factoryBusinessentityaddressPK(4,3,3));
			}
		}
		
		@Nested
		@Tag("Personphone")
		@TestMethodOrder(OrderAnnotation.class)
		public class D_PersonphoneDAOTest {
			@Test
			@Order(1)
			public void deleteTest() {
				//Set Up
				Personphone personphone6 = personphoneDao.findById(factoryPersonphonePK(2,"0987654321",1)).get();
				
				personphoneDao.delete(personphone6);
				
				List<Personphone> personphones = personphoneDao.findAll();
				
				//Assert
				assertEquals(personphones.size(), 6);
				
				assertEquals(personphones.get(0).getId(), factoryPersonphonePK(1,"3183741903",1));
				assertEquals(personphones.get(1).getId(), factoryPersonphonePK(2,"3158728989",2));
				assertEquals(personphones.get(2).getId(), factoryPersonphonePK(3,"3188031038",2));
				assertEquals(personphones.get(3).getId(), factoryPersonphonePK(2,"3182352827",1));
				assertEquals(personphones.get(4).getId(), factoryPersonphonePK(3,"3115890190",2));
				assertEquals(personphones.get(5).getId(), factoryPersonphonePK(3,"1234567890",2));
			}
			
			@Test
			@Order(2)
			public void deleteByIdTest() {
				//Set Up
				personphoneDao.deleteById(factoryPersonphonePK(3,"1234567890",2));
				
				List<Personphone> personphones = personphoneDao.findAll();
				
				//Assert
				assertEquals(personphones.size(), 5);
				
				assertEquals(personphones.get(0).getId(), factoryPersonphonePK(1,"3183741903",1));
				assertEquals(personphones.get(1).getId(), factoryPersonphonePK(2,"3158728989",2));
				assertEquals(personphones.get(2).getId(), factoryPersonphonePK(3,"3188031038",2));
				assertEquals(personphones.get(3).getId(), factoryPersonphonePK(2,"3182352827",1));
				assertEquals(personphones.get(4).getId(), factoryPersonphonePK(3,"3115890190",2));
			}
		}
	}
	
	@Nested
	@Tag("Read")
	@TestMethodOrder(OrderAnnotation.class)
	public class Read {
		@Nested
		@Tag("Person")
		@TestMethodOrder(OrderAnnotation.class)
		public class A_PersonDAOTest {
			@Test
			@Order(1)
			public void findByIdTest() {
				//Assert
				assertEquals(personDao.findById(1).get().getFirstname(), "Esteban");
				assertEquals(personDao.findById(2).get().getFirstname(), "Isabella");
				assertEquals(personDao.findById(3).get().getFirstname(), "Samuel");
				assertTrue(personDao.findById(4).isEmpty());
			}
			
			@Test
			@Order(2)
			public void findAllTest() {
				//Set Up
				List<Person> persons = personDao.findAll();
				
				//Assert
				assertEquals(persons.size(), 3);
				
				assertEquals(persons.get(0).getFirstname(), "Esteban");
				assertEquals(persons.get(1).getFirstname(), "Isabella");
				assertEquals(persons.get(2).getFirstname(), "Samuel");
			}
			
			@Test
			@Order(3)
			public void findByTitleTest() {
				//Set Up
				List<Person> persons = personDao.findByTitle("Titulo2");
				
				//Assert
				assertEquals(persons.size(), 2);
				
				assertEquals(persons.get(0).getFirstname(), "Esteban");
				assertEquals(persons.get(1).getFirstname(), "Samuel");
			}
			
			@Test
			@Order(4)
			public void findByPersontypeTest() {
				//Set Up
				List<Person> persons = personDao.findByPersontype("Humano");
				
				//Assert
				assertEquals(persons.size(), 2);
				
				assertEquals(persons.get(0).getFirstname(), "Esteban");
				assertEquals(persons.get(1).getFirstname(), "Isabella");
			}
			
			@Test
			@Order(5)
			public void specialQueryTest() {
				//Set Up
				@SuppressWarnings("unchecked")
				List<Object[]> persons = (List<Object[]>) personDao.specialQuery(new Timestamp(0), new Timestamp(500));
				
				//Assert
				assertEquals(persons.size(), 2);
				
				assertEquals(((Person)persons.get(0)[0]).getFirstname(), "Samuel");
				assertEquals((Long)persons.get(0)[1], 2);
				
				assertEquals(((Person)persons.get(1)[0]).getFirstname(), "Esteban");
				assertEquals((Long)persons.get(1)[1], 1);
			}
		}
		
		@Nested
		@Tag("Address")
		@TestMethodOrder(OrderAnnotation.class)
		public class B_AddressDAOTest {
			@Test
			@Order(1)
			public void findByIdTest() {
				//Assert
				assertEquals(addressDao.findById(1).get().getCity(), "Medellin");
				assertEquals(addressDao.findById(2).get().getCity(), "Bogota");
				assertEquals(addressDao.findById(3).get().getCity(), "Cali");
				assertTrue(addressDao.findById(5).isEmpty());
			}
			
			@Test
			@Order(2)
			public void findAllTest() {
				//Set Up
				List<Address> addresses = addressDao.findAll();
				
				//Assert
				assertEquals(addresses.size(), 4);
				
				assertEquals(addresses.get(0).getCity(), "Medellin");
				assertEquals(addresses.get(1).getCity(), "Bogota");
				assertEquals(addresses.get(2).getCity(), "Cali");
				assertEquals(addresses.get(3).getCity(), "Pasto");
			}
			
			@Test
			@Order(3)
			public void findByModifieddateTest() {
				//Set Up
				List<Address> addresses = addressDao.findByModifieddate(new Timestamp(0));
				
				//Assert
				assertEquals(addresses.size(), 2);
				
				assertEquals(addresses.get(0).getCity(), "Medellin");
				assertEquals(addresses.get(1).getCity(), "Cali");
			}
		}
		
		@Nested
		@Tag("Businessentityaddress")
		@TestMethodOrder(OrderAnnotation.class)
		public class C_BusinessentityaddressDAOTest {
			@Test
			@Order(1)
			public void findByIdTest() {
				//Assert
				assertTrue(businessentityaddressDao.findById(factoryBusinessentityaddressPK(1,1,1)).isPresent());
				assertTrue(businessentityaddressDao.findById(factoryBusinessentityaddressPK(2,2,2)).isPresent());
				assertTrue(businessentityaddressDao.findById(factoryBusinessentityaddressPK(3,3,3)).isPresent());
				assertTrue(businessentityaddressDao.findById(factoryBusinessentityaddressPK(1,2,3)).isEmpty());
			}
			
			@Test
			@Order(2)
			public void findAllTest() {
				//Set Up
				List<Businessentityaddress> businessentityaddresses = businessentityaddressDao.findAll();
				
				//Assert
				assertEquals(businessentityaddresses.size(), 4);
				
				assertEquals(businessentityaddresses.get(0).getId(), factoryBusinessentityaddressPK(1,1,1));
				assertEquals(businessentityaddresses.get(1).getId(), factoryBusinessentityaddressPK(2,2,2));
				assertEquals(businessentityaddresses.get(2).getId(), factoryBusinessentityaddressPK(3,3,3));
				assertEquals(businessentityaddresses.get(3).getId(), factoryBusinessentityaddressPK(4,3,3));
			}
		}
		
		@Nested
		@Tag("Personphone")
		@TestMethodOrder(OrderAnnotation.class)
		public class D_PersonphoneDAOTest {
			@Test
			@Order(1)
			public void findByIdTest() {
				//Assert
				assertEquals(personphoneDao.findById( factoryPersonphonePK(1,"3183741903",1) ).get().getId().getPhonenumber(), "3183741903");
				assertEquals(personphoneDao.findById( factoryPersonphonePK(2,"3158728989",2) ).get().getId().getPhonenumber(), "3158728989");
				assertEquals(personphoneDao.findById( factoryPersonphonePK(3,"3188031038",2) ).get().getId().getPhonenumber(), "3188031038");
				assertTrue(personphoneDao.findById( factoryPersonphonePK(1,"3103432926",2) ).isEmpty());
			}
			
			@Test
			@Order(2)
			public void findAllTest() {
				//Set Up
				List<Personphone> personphones = personphoneDao.findAll();
				
				//Assert
				assertEquals(personphones.size(), 5);
				
				assertEquals(personphones.get(0).getId().getPhonenumber(), "3183741903");
				assertEquals(personphones.get(1).getId().getPhonenumber(), "3158728989");
				assertEquals(personphones.get(2).getId().getPhonenumber(), "3188031038");
				assertEquals(personphones.get(3).getId().getPhonenumber(), "3182352827");
				assertEquals(personphones.get(4).getId().getPhonenumber(), "3115890190");
			}
			
			@Test
			@Order(3)
			public void findByPrefixTest() {
				//Set Up
				List<Personphone> personphones = personphoneDao.findByPrefix("318");
				
				//Assert
				assertEquals(personphones.size(), 3);
				
				assertEquals(personphones.get(0).getId().getPhonenumber(), "3183741903");
				assertEquals(personphones.get(1).getId().getPhonenumber(), "3188031038");
				assertEquals(personphones.get(2).getId().getPhonenumber(), "3182352827");
			}
			
			@Test
			@Order(4)
			public void findByPhonenumbertype() {
				//Set Up
				List<Personphone> personphones = personphoneDao.findByPhonenumbertype(2);
				
				//Assert
				assertEquals(personphones.size(), 3);
				
				assertEquals(personphones.get(0).getId().getPhonenumber(), "3158728989");
				assertEquals(personphones.get(1).getId().getPhonenumber(), "3188031038");
				assertEquals(personphones.get(2).getId().getPhonenumber(), "3115890190");
			}
			
			@Test
			@Order(5)
			public void specialQueryTest() {
				//Set Up
				List<Personphone> personphones = personphoneDao.specialQuery();
				
				//Assert
				assertEquals(personphones.size(), 2);
				
				assertEquals(personphones.get(0).getId().getPhonenumber(), "3158728989");
				assertEquals(personphones.get(1).getId().getPhonenumber(), "3182352827");
			}
		}
	}
	
}
