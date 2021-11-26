package integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.service.PersonService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

	private PersonService personService;
	
	private BusinessentityRepository businessentityRepository;
	
	@Autowired
	public PersonServiceTest(PersonService personService, BusinessentityRepository businessentityRepository) {
		this.personService = personService;
		this.businessentityRepository = businessentityRepository;
	}
	
	@Nested
	@Tag("create")
	@TestMethodOrder(OrderAnnotation.class)
	class Create{
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set Up
			Person person1 = new Person();
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			//Method
			Person personSaver = personService.save(person1);
			
			//Asserts
			assertNotNull(personSaver);
			assertEquals(personSaver.getModifieddate(), new Timestamp(0));
			assertEquals(personSaver.getFirstname(), "Esteban");
			assertEquals(personSaver.getLastname(), "Ariza");
			
			Optional<Businessentity> businessentitySaver = businessentityRepository.findById(1);
			
			assertTrue(businessentitySaver.isPresent());
			assertEquals(personSaver.getBusinessentity().getBusinessentityid(), businessentitySaver.get().getBusinessentityid());
			assertEquals(businessentitySaver.get().getPerson().getBusinessentityid(), personSaver.getBusinessentityid());
		}
		
		@Test
		@Order(2)
		public void saveTestWrongModifieddate() {
			//Set Up
			Person person1 = new Person();
			person1.setFirstname("Esteban");
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
		public void saveTestWrongFirstname() {
			//Set Up
			Person person1 = new Person();
			person1.setModifieddate(new Timestamp(0));
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
		@Order(4)
		public void saveTestWrong() {
			//Set Up
			Person person1 = new Person();
			person1.setModifieddate(new Timestamp(0));
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
	@Tag("update")
	@TestMethodOrder(OrderAnnotation.class)
	class Update{
		@Test
		@Order(1)
		public void updateTestCorrect() {
			//Set Up
			Person person1 = personService.findByPK(1).get();
			person1.setModifieddate(new Timestamp(1000));
			person1.setFirstname("Isabella");
			person1.setLastname("Villamil");
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNotNull(personSaver);
			assertEquals(personSaver.getModifieddate(), new Timestamp(1000));
			assertEquals(personSaver.getFirstname(), "Isabella");
			assertEquals(personSaver.getLastname(), "Villamil");
		}
		
		@Test
		@Order(2)
		public void updateTestWrongModifieddate() {
			//Set Up
			Person person1 = personService.findByPK(1).get();
			person1.setModifieddate(null);
			person1.setFirstname("Isabella");
			person1.setLastname("Villamil");
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNull(personSaver);
		}
		
		@Test
		@Order(3)
		public void updateTestWrongFirstname() {
			//Set Up
			Person person1 = personService.findByPK(1).get();
			person1.setModifieddate(new Timestamp(1000));
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
			person1.setModifieddate(new Timestamp(1000));
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
			person1.setModifieddate(new Timestamp(1000));
			person1.setFirstname("Isabella");
			person1.setLastname("Villamil");
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNull(personSaver);
		}
	}
}
