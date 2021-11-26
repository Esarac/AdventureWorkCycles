package integration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.dao.PersonphoneDAO;
import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.model.person.Personphone;
import co.edu.icesi.awc.model.person.PersonphonePK;
import co.edu.icesi.awc.model.person.Phonenumbertype;
import co.edu.icesi.awc.repository.PersonRepository;
import co.edu.icesi.awc.repository.PhonenumbertypeRepository;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class PersonphoneDAOTest {
	
	private PersonphoneDAO personphoneDao;
	
	private PersonRepository personRepository;
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	@Autowired
	public PersonphoneDAOTest(PersonphoneDAO personphoneDao,
			PersonRepository personRepository, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.personphoneDao = personphoneDao;
		this.personRepository = personRepository;
		this.phonenumbertypeRepository = phonenumbertypeRepository;
	}
	
	private Person[] personSetUp() {
		Person[] persons = new Person[3];
		
		persons[0] = new Person();
		persons[0].setModifieddate(new Timestamp(0));
		persons[0].setFirstname("Esteban");
		persons[0].setLastname("Ariza");
		persons[0].setTitle("Titulo2");
		persons[0].setPersontype("Humano");
		
		persons[1] = new Person();
		persons[1].setModifieddate(new Timestamp(0));
		persons[1].setFirstname("Isabella");
		persons[1].setLastname("Villamil");
		persons[1].setTitle("Titulo1");
		persons[1].setPersontype("Humano");
		
		persons[2] = new Person();
		persons[2].setModifieddate(new Timestamp(0));
		persons[2].setFirstname("Samuel");
		persons[2].setLastname("Ariza");
		persons[2].setTitle("Titulo2");
		persons[2].setPersontype("Alien");
		
		personRepository.save(persons[0]);
		personRepository.save(persons[1]);
		personRepository.save(persons[2]);
		
		return persons;
	}
	
	private Phonenumbertype[] phonenumbertypeSetUp(){
		Phonenumbertype[] phonenumbertypes = new Phonenumbertype[2];
		
		phonenumbertypes[0] = phonenumbertypeRepository.save(new Phonenumbertype());
		phonenumbertypes[1] = phonenumbertypeRepository.save(new Phonenumbertype());
		
		return phonenumbertypes;
	}
	
	private PersonphonePK[] idsSetUp() {
		PersonphonePK[] ids = new PersonphonePK[3];
		
		ids[0] = new PersonphonePK();
		ids[0].setBusinessentityid(1);
		ids[0].setPhonenumber("3183741903");
		ids[0].setPhonenumbertypeid(1);
		
		ids[1] = new PersonphonePK();
		ids[1].setBusinessentityid(2);
		ids[1].setPhonenumber("3158728989");
		ids[1].setPhonenumbertypeid(2);
		
		ids[2] = new PersonphonePK();
		ids[2].setBusinessentityid(3);
		ids[2].setPhonenumber("3188031038");
		ids[2].setPhonenumbertypeid(2);
		
		return ids;
	}
	
	@Test
	@Order(1)
	public void saveTest() {
		//Set Up
		Person[] persons = personSetUp();
		Phonenumbertype[] phonenumbertypes = phonenumbertypeSetUp();
		PersonphonePK[] ids = idsSetUp();
		
		Personphone personphone1 = new Personphone();
		personphone1.setPerson(persons[0]);
		personphone1.setPhonenumbertype(phonenumbertypes[0]);
		personphone1.setId(ids[0]);
		
		Personphone personphone2 = new Personphone();
		personphone2.setPerson(persons[1]);
		personphone2.setPhonenumbertype(phonenumbertypes[1]);
		personphone2.setId(ids[1]);
		
		Personphone personphone3 = new Personphone();
		personphone3.setPerson(persons[2]);
		personphone3.setPhonenumbertype(phonenumbertypes[1]);
		personphone3.setId(ids[2]);
		
		personphoneDao.save(personphone1);
		personphoneDao.save(personphone2);
		personphoneDao.save(personphone3);
		
		List<Personphone> personphones = personphoneDao.findAll();
		
		//Assert
		assertEquals(personphones.size(), 3);
		
		assertEquals(personphones.get(0).getId().getPhonenumber(), "3183741903");
		assertEquals(personphones.get(1).getId().getPhonenumber(), "3158728989");
		assertEquals(personphones.get(2).getId().getPhonenumber(), "3188031038");
	}
	
	@Test
	@Order(2)
	public void findByIdTest() {
		//Set Up
		PersonphonePK[] ids = idsSetUp();
		
		PersonphonePK id4 = new PersonphonePK();
		id4.setBusinessentityid(1);
		id4.setPhonenumber("3103432926");
		id4.setPhonenumbertypeid(2);
		
		//Assert
		assertEquals(personphoneDao.findById(ids[0]).getId().getPhonenumber(), "3183741903");
		assertEquals(personphoneDao.findById(ids[1]).getId().getPhonenumber(), "3158728989");
		assertEquals(personphoneDao.findById(ids[2]).getId().getPhonenumber(), "3188031038");
		assertNull(personphoneDao.findById(id4));
	}
	
	@Test
	@Order(3)
	public void findAllTest() {
		//Set Up
		List<Personphone> personphones = personphoneDao.findAll();
		
		//Assert
		assertEquals(personphones.size(), 3);
		
		assertEquals(personphones.get(0).getId().getPhonenumber(), "3183741903");
		assertEquals(personphones.get(1).getId().getPhonenumber(), "3158728989");
		assertEquals(personphones.get(2).getId().getPhonenumber(), "3188031038");
	}
	
	@Test
	@Order(4)
	public void findByPrefixTest() {
		//Set Up
		List<Personphone> personphones = personphoneDao.findByPrefix("318");
		
		//Assert
		assertEquals(personphones.size(), 2);
		
		assertEquals(personphones.get(0).getId().getPhonenumber(), "3183741903");
		assertEquals(personphones.get(1).getId().getPhonenumber(), "3188031038");
	}
	
	@Test
	@Order(5)
	public void findByPhonenumbertype() {
		//Set Up
		List<Personphone> personphones = personphoneDao.findByPhonenumbertype(2);
		
		//Assert
		assertEquals(personphones.size(), 2);
		
		assertEquals(personphones.get(0).getId().getPhonenumber(), "3158728989");
		assertEquals(personphones.get(1).getId().getPhonenumber(), "3188031038");
	}
	
	@Test
	@Order(6)
	public void specialQueryTest() {
		//Terminar
	}
	
	@Test
	@Order(7)
	public void deleteTest() {
		//Set Up
		PersonphonePK[] ids = idsSetUp();
		
		Personphone personphone1 = personphoneDao.findById(ids[0]);
		
		personphoneDao.delete(personphone1);
		
		List<Personphone> personphones = personphoneDao.findAll();
		
		//Assert
		assertEquals(personphones.size(), 2);
		
		assertEquals(personphones.get(0).getId().getPhonenumber(), "3158728989");
		assertEquals(personphones.get(1).getId().getPhonenumber(), "3188031038");
	}
	
	@Test
	@Order(8)
	public void deleteByIdTest() {
		//Set Up
		PersonphonePK[] ids = idsSetUp();
		
		personphoneDao.deleteById(ids[2]);
		
		List<Personphone> personphones = personphoneDao.findAll();
		
		//Assert
		assertEquals(personphones.size(), 1);
		
		assertEquals(personphones.get(0).getId().getPhonenumber(), "3158728989");
	}
}
