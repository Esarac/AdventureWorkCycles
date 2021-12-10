package co.edu.icesi.awc;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import co.edu.icesi.awc.back.dao.AddressDAO;
import co.edu.icesi.awc.back.dao.PersonDAO;
import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Addresstype;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Phonenumbertype;
import co.edu.icesi.awc.back.model.person.Stateprovince;
import co.edu.icesi.awc.back.repository.AddressRepository;
import co.edu.icesi.awc.back.repository.AddresstypeRepository;
import co.edu.icesi.awc.back.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.back.repository.StateprovinceRepository;
import co.edu.icesi.awc.back.security.UserApp;
import co.edu.icesi.awc.back.security.UserType;
import co.edu.icesi.awc.back.service.AddressService;
import co.edu.icesi.awc.back.service.PersonService;
import co.edu.icesi.awc.back.service.UserService;

@SpringBootApplication
public class AdventureWorkCyclesArizaEstebanApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(AdventureWorkCyclesArizaEstebanApplication.class, args);
		UserService us = c.getBean(UserService.class);
		
		//User1
		UserApp user1 = new UserApp();
		user1.setUsername("1");
		user1.setPassword("{noop}1");
		user1.setType(UserType.ADMINISTRATOR);
		us.save(user1);
		
		//User2
		UserApp user2 = new UserApp();
		user2.setUsername("2");
		user2.setPassword("{noop}2");
		user2.setType(UserType.OPERATOR);
		us.save(user2);
		
		//Test
		//~~Person
		PersonService ps = c.getBean(PersonService.class);
		Person person1 = new Person();
		person1.setFirstname("esteban");
		person1.setLastname("ariza");
		ps.save(person1);
		
		//~Stateprovince
		StateprovinceRepository sr = c.getBean(StateprovinceRepository.class);
		Stateprovince stateprovince1 = new Stateprovince();
		sr.save(stateprovince1);
		
		//~~Address
		AddressService as = c.getBean(AddressService.class);
		Address address1 = new Address();
		address1.setAddressline1("1");
		address1.setCity("cali");
		address1.setPostalcode("1007");
		as.save(address1, 1);
		
		//~Addresstype
		AddresstypeRepository tr = c.getBean(AddresstypeRepository.class);
		Addresstype addresstype1 = new Addresstype();
		tr.save(addresstype1);
		
		//~Phonenumbertype
		PhonenumbertypeRepository pr = c.getBean(PhonenumbertypeRepository.class);
		Phonenumbertype phonenumbertype1 = new Phonenumbertype();
		pr.save(phonenumbertype1);
		//...
	}

}
