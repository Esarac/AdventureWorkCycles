package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

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
import co.edu.icesi.awc.back.dao.PersonDAO;
import co.edu.icesi.awc.back.model.person.Businessentity;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.repository.BusinessentityRepository;
import co.edu.icesi.awc.back.repository.PersonRepository;
import co.edu.icesi.awc.back.service.PersonService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personService;
	@Mock
	private PersonDAO personRepository;
	@Mock
	private BusinessentityRepository businessentityRepository;
	
	@Nested
	@Tag("create")
	class Create{
		
		@Test
		public void saveTestCorrect() {
			//Set Up
			Person person1 = new Person();
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Businessentity businessentity1 = new Businessentity();
			
			when(personRepository.save(person1)).thenReturn(person1);
			when(businessentityRepository.save( any(Businessentity.class) )).thenReturn(businessentity1);
			
			//Method
			Person personSaver = personService.save(person1);
			
			//Asserts
			assertNotNull(personSaver);
			assertNotNull(personSaver.getModifieddate());
			assertEquals(personSaver.getFirstname(), "Esteban");
			assertEquals(personSaver.getLastname(), "Ariza");
			assertEquals(personSaver.getBusinessentity(), businessentity1);
			
			verify(personRepository).save(person1);
			verify(businessentityRepository).save( any(Businessentity.class) );
			
		}
		
		@Test
		public void saveTestWrongFirstname() {
			//Set Up
			Person person1 = new Person();
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
			person1.setFirstname("Esteban");
			person1.setLastname("Ariza");
			
			Person person2 = new Person();
			
			Businessentity businessentity1 = new Businessentity();
			person1.setBusinessentity(businessentity1);
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person2));
			when(personRepository.save(person1)).thenReturn(person1);
			
			//Method
			Person personSaver = personService.update(person1);
			
			//Asserts
			assertNotNull(personSaver);
			assertNotNull(personSaver.getModifieddate());
			assertEquals(personSaver.getFirstname(), "Esteban");
			assertEquals(personSaver.getLastname(), "Ariza");
			assertEquals(personSaver.getBusinessentity(), businessentity1);
			
			verify(personRepository).findById(1);
			verify(personRepository).save(person1);
			verify(businessentityRepository, times(0)).save( any(Businessentity.class) );
		}
		
		@Test
		public void updateTestWrongFirstname() {
			//Set Up
			Person person1 = new Person();
			person1.setBusinessentityid(1);
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
