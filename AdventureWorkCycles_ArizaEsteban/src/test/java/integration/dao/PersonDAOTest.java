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
import co.edu.icesi.awc.dao.PersonDAO;
import co.edu.icesi.awc.model.person.Person;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class PersonDAOTest {

	private PersonDAO personDao;
	
	@Autowired
	public PersonDAOTest(PersonDAO personDao) {
		this.personDao = personDao;
	}
	
	@Test
	@Order(1)
	public void saveTest() {
		//Set Up
		Person person1 = new Person();
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
		person3.setLastname("Ariza");
		person3.setTitle("Titulo2");
		person3.setPersontype("Alien");
		
		personDao.save(person1);
		personDao.save(person2);
		personDao.save(person3);
		
		List<Person> persons = personDao.findAll();
		
		//Assert
		assertEquals(persons.size(), 3);
		
		assertEquals(persons.get(0).getFirstname(), "Esteban");
		assertEquals(persons.get(1).getFirstname(), "Isabella");
		assertEquals(persons.get(2).getFirstname(), "Samuel");
	}
	
	@Test
	@Order(2)
	public void findByIdTest() {
		//Assert
		assertEquals(personDao.findById(1).getFirstname(), "Esteban");
		assertEquals(personDao.findById(2).getFirstname(), "Isabella");
		assertEquals(personDao.findById(3).getFirstname(), "Samuel");
		assertNull(personDao.findById(4));
	}
	
	@Test
	@Order(3)
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
	@Order(4)
	public void findByTitleTest() {
		//Set Up
		List<Person> persons = personDao.findByTitle("Titulo2");
		
		//Assert
		assertEquals(persons.size(), 2);
		
		assertEquals(persons.get(0).getFirstname(), "Esteban");
		assertEquals(persons.get(1).getFirstname(), "Samuel");
	}
	
	@Test
	@Order(5)
	public void findByPersontypeTest() {
		//Set Up
		List<Person> persons = personDao.findByPersontype("Humano");
		
		//Assert
		assertEquals(persons.size(), 2);
		
		assertEquals(persons.get(0).getFirstname(), "Esteban");
		assertEquals(persons.get(1).getFirstname(), "Isabella");
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
		Person person1 = personDao.findById(1);
		
		personDao.delete(person1);
		
		List<Person> persons = personDao.findAll();
		
		//Assert
		assertEquals(persons.size(), 2);
		
		assertEquals(persons.get(0).getFirstname(), "Isabella");
		assertEquals(persons.get(1).getFirstname(), "Samuel");
		System.out.println(persons);
		
	}
	
	@Test
	@Order(8)
	public void deleteByIdTest() {
		//Set Up
		personDao.deleteById(3);
		
		List<Person> persons = personDao.findAll();
		
		//Assert
		assertEquals(persons.size(), 1);
		
		assertEquals(persons.get(0).getFirstname(), "Isabella");
	}
	
}
