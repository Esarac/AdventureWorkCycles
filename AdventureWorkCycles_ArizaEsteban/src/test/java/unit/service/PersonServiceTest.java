package unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.repository.PersonRepository;
import co.edu.icesi.awc.service.PersonService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personService;
	@Mock
	private PersonRepository personRepository;
	@Mock
	private BusinessentityRepository businessentityRepository;
	
	@Autowired
	public PersonServiceTest(PersonService personService, PersonRepository personRepository, BusinessentityRepository businessentityRepository) {
		this.personService = personService;
		this.personRepository = personRepository;
		this.businessentityRepository = businessentityRepository;
	}
	
	@Nested
	@Tag("create")
	class Create{
		
		@Test
		public void saveTestCorrect() {
			//Set Up
			Person person1 = new Person();
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.save(person1);
			
			//Asserts
			assertNotNull(personSaver);
			assertEquals(personSaver.getModifieddate(), new Timestamp(0));
			assertEquals(personSaver.getFirstname(), "Esteban");
			assertEquals(personSaver.getLastname(), "Ariza");
			assertEquals(personSaver.getBusinessentity(), businessentity1);
			
			verify(personRepository).save(person1);
			verify(businessentityRepository).save(businessentity1);
		}
		
		@Test
		public void saveTestWrongModifieddate() {
			//Set Up
			Person person1 = new Person();
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.save(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
		@Test
		public void saveTestWrongFirstname() {
			//Set Up
			Person person1 = new Person();
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Es");
			person1.setLastname("Ariza");
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.save(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
		@Test
		public void saveTestWrongLastname() {
			//Set Up
			Person person1 = new Person();
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Esteban");
			person1.setLastname("Ar");
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.save(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
	}
	
	@Nested
	@Tag("update")
	class Update{
		
		@Test
		public void updateTestCorrect() {
			//Set Up
			Person person1 = new Person();
			person1.setBusinessentityid(1);
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Person person2 = new Person();
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person2));
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNotNull(personSaver);
			assertEquals(personSaver.getModifieddate(), new Timestamp(0));
			assertEquals(personSaver.getFirstname(), "Esteban");
			assertEquals(personSaver.getLastname(), "Ariza");
			assertEquals(personSaver.getBusinessentity(), businessentity1);
			
			verify(personRepository).findById(1);
			verify(personRepository).save(person1);
			verify(businessentityRepository).save(businessentity1);
		}
		
		@Test
		public void updateTestWrongModifieddate() {
			//Set Up
			Person person1 = new Person();
			person1.setBusinessentityid(1);
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Person person2 = new Person();
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person2));
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository).findById(1);
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
		@Test
		public void updateTestWrongFirstname() {
			//Set Up
			Person person1 = new Person();
			person1.setBusinessentityid(1);
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Es");
			person1.setLastname("Ariza");
			
			Person person2 = new Person();
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person2));
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository).findById(1);
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
		@Test
		public void updateTestWrongLastname() {
			//Set Up
			Person person1 = new Person();
			person1.setBusinessentityid(1);
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Esteban");
			person1.setLastname("Ar");
			
			Person person2 = new Person();
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person2));
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository).findById(1);
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
		@Test
		public void updateTestWrongNotFound() {
			//Set Up
			Person person1 = new Person();
			person1.setBusinessentityid(1);
			person1.setModifieddate(new Timestamp(0));
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.findById(1)).thenReturn(Optional.empty());
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save(businessentity1)).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNull(personSaver);
			
			verify(personRepository).findById(1);
			verify(personRepository, times(0)).save(person1);
			verify(businessentityRepository, times(0)).save(businessentity1);
		}
		
	}
	
}
