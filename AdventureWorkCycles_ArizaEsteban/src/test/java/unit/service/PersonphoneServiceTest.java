package unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.model.person.Personphone;
import co.edu.icesi.awc.model.person.PersonphonePK;
import co.edu.icesi.awc.model.person.Phonenumbertype;
import co.edu.icesi.awc.repository.PersonRepository;
import co.edu.icesi.awc.repository.PersonphoneRepository;
import co.edu.icesi.awc.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.service.PersonphoneService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class PersonphoneServiceTest {

	@InjectMocks
	private PersonphoneService personphoneService;
	@Mock
	private PersonphoneRepository personphoneRepository;
	@Mock
	private PersonRepository personRepository;
	@Mock
	private PhonenumbertypeRepository phonenumbertyperepository;
	
	@Autowired
	public PersonphoneServiceTest(PersonphoneService personphoneService, PersonphoneRepository personphoneRepository, PersonRepository personRepository, PhonenumbertypeRepository phonenumbertyperepository) {
		this.personphoneService = personphoneService;
		this.personphoneRepository = personphoneRepository;
		this.personRepository = personRepository;
		this.phonenumbertyperepository = phonenumbertyperepository;
	}
	
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set Up
			Personphone personphone1 = new Personphone();
			
			Person person1 = new Person();
			Phonenumbertype phonenumbertype1 = new Phonenumbertype();
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person1));
			when(phonenumbertyperepository.findById(1)).thenReturn(Optional.of(phonenumbertype1));
			when(personphoneRepository.save(personphone1)).thenReturn(personphone1);
			
			//Method
			Personphone personphoneSave = personphoneService.save(personphone1, "3183741903", 1, 1);
			
			//Asserts
			assertNotNull(personphoneSave);
			assertEquals(personphoneSave.getId().getPhonenumber(), "3183741903");
			assertEquals(personphoneSave.getPerson(), person1);
			assertEquals(personphoneSave.getPhonenumbertype(), phonenumbertype1);
			
			verify(personRepository).findById(1);
			verify(phonenumbertyperepository).findById(1);
			verify(personphoneRepository).save(personphone1);
		}
		
		@Test
		public void saveTestWrongPhonenumber() {
			//Set Up
			Personphone personphone1 = new Personphone();
			
			Person person1 = new Person();
			Phonenumbertype phonenumbertype1 = new Phonenumbertype();
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person1));
			when(phonenumbertyperepository.findById(1)).thenReturn(Optional.of(phonenumbertype1));
			when(personphoneRepository.save(personphone1)).thenReturn(personphone1);
			
			//Method
			Personphone personphoneSave = personphoneService.save(personphone1, "4847803", 1, 1);
			
			//Asserts
			assertNull(personphoneSave);
			
			verify(personRepository, times(0)).findById(1);
			verify(phonenumbertyperepository, times(0)).findById(1);
			verify(personphoneRepository, times(0)).save(personphone1);
		}
		
		@Test
		public void saveTestWrongPerson() {
			//Set Up
			Personphone personphone1 = new Personphone();
			
			Phonenumbertype phonenumbertype1 = new Phonenumbertype();
			
			when(personRepository.findById(1)).thenReturn(Optional.empty());
			when(phonenumbertyperepository.findById(1)).thenReturn(Optional.of(phonenumbertype1));
			when(personphoneRepository.save(personphone1)).thenReturn(personphone1);
			
			//Method
			Personphone personphoneSave = personphoneService.save(personphone1, "3183741903", 1, 1);
			
			//Asserts
			assertNull(personphoneSave);
			
			verify(personRepository).findById(1);
			verify(phonenumbertyperepository).findById(1);
			verify(personphoneRepository, times(0)).save(personphone1);
		}
		
		@Test
		public void saveTestWrongPhonenumbertype() {
			//Set Up
			Personphone personphone1 = new Personphone();
			
			Person person1 = new Person();
			
			when(personRepository.findById(1)).thenReturn(Optional.of(person1));
			when(phonenumbertyperepository.findById(1)).thenReturn(Optional.empty());
			when(personphoneRepository.save(personphone1)).thenReturn(personphone1);
			
			//Method
			Personphone personphoneSave = personphoneService.save(personphone1, "3183741903", 1, 1);
			
			//Asserts
			assertNull(personphoneSave);
			
			verify(personRepository).findById(1);
			verify(phonenumbertyperepository).findById(1);
			verify(personphoneRepository, times(0)).save(personphone1);
		}
	}
	
	@Nested
	@Tag("update")
	class Update {
		@Test
		public void updateTestCorrect() {
			//Set Up
			Personphone personphone1 = new Personphone();
			PersonphonePK pk = new PersonphonePK();
			pk.setPhonenumber("3183741903");
			pk.setBusinessentityid(1);
			pk.setPhonenumbertypeid(1);
			personphone1.setId(pk);
			
			Personphone personphone2 = new Personphone();
			
			Person person1 = new Person();
			Phonenumbertype phonenumbertype1 = new Phonenumbertype();
			
			when(personphoneRepository.findById(pk)).thenReturn(Optional.of(personphone2));
			when(personRepository.findById(1)).thenReturn(Optional.of(person1));
			when(phonenumbertyperepository.findById(1)).thenReturn(Optional.of(phonenumbertype1));
			when(personphoneRepository.save(personphone1)).thenReturn(personphone1);
			
			//Method
			Personphone personphoneSave = personphoneService.update(personphone1);
			
			//Asserts
			assertNotNull(personphoneSave);
			assertEquals(personphoneSave.getId().getPhonenumber(), "3183741903");
			
			verify(personphoneRepository).findById(pk);
			verify(personRepository).findById(1);
			verify(phonenumbertyperepository).findById(1);
			verify(personphoneRepository).save(personphone1);
		}
		
		@Test
		public void updateTestWrongNotFound() {
			//Set Up
			Personphone personphone1 = new Personphone();
			PersonphonePK pk = new PersonphonePK();
			pk.setPhonenumber("3183741903");
			personphone1.setId(pk);
			
			Person person1 = new Person();
			Phonenumbertype phonenumbertype1 = new Phonenumbertype();
			
			when(personphoneRepository.findById(pk)).thenReturn(Optional.empty());
			when(personRepository.findById(1)).thenReturn(Optional.of(person1));
			when(phonenumbertyperepository.findById(1)).thenReturn(Optional.of(phonenumbertype1));
			when(personphoneRepository.save(personphone1)).thenReturn(personphone1);
			
			//Method
			Personphone personphoneSave = personphoneService.update(personphone1);
			
			//Asserts
			assertNull(personphoneSave);
			
			verify(personphoneRepository).findById(pk);
			verify(personRepository, times(0)).findById(1);
			verify(phonenumbertyperepository, times(0)).findById(1);
			verify(personphoneRepository, times(0)).save(personphone1);
		}
	}
}
