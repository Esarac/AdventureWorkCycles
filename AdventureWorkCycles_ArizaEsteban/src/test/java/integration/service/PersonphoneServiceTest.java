package integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.model.person.Personphone;
import co.edu.icesi.awc.model.person.PersonphonePK;
import co.edu.icesi.awc.model.person.Phonenumbertype;
import co.edu.icesi.awc.repository.PersonRepository;
import co.edu.icesi.awc.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.service.PersonphoneService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class PersonphoneServiceTest {

	private PersonphoneService personphoneService;
	
	private PersonRepository personRepository;
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	@Autowired
	public PersonphoneServiceTest(PersonphoneService personphoneService, PersonRepository personRepository, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.personphoneService = personphoneService;
		this.personRepository = personRepository;
		this.phonenumbertypeRepository = phonenumbertypeRepository;
	}
	
	@Nested
	@Tag("create")
	@TestMethodOrder(OrderAnnotation.class)
	class Create{
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set Up
			Personphone personphone1 = new Personphone();
			
			Person person1 = new Person();
			personRepository.save(person1);
			Phonenumbertype phonenumbertype1 = new Phonenumbertype();
			phonenumbertypeRepository.save(phonenumbertype1);
			
			//Method
			Personphone personphoneSave = personphoneService.save(personphone1,"3183741903", 1, 1);
			
			//Asserts
			assertNotNull(personphoneSave);
			assertEquals(personphoneSave.getId().getPhonenumber(), "3183741903");
			
			Optional<Person> personSave = personRepository.findById(1);
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
	@Tag("update")
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
