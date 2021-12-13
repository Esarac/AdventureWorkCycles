package co.edu.icesi.awc;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import co.edu.icesi.awc.back.dao.AddressDAO;
import co.edu.icesi.awc.back.dao.BusinessentityaddressDAO;
import co.edu.icesi.awc.back.dao.CustomerDAO;
import co.edu.icesi.awc.back.dao.PersonDAO;
import co.edu.icesi.awc.back.dao.PersonphoneDAO;
import co.edu.icesi.awc.back.dao.StoreDAO;
import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Addresstype;
import co.edu.icesi.awc.back.model.person.Businessentity;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.BusinessentityaddressPK;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.model.person.PersonphonePK;
import co.edu.icesi.awc.back.model.person.Phonenumbertype;
import co.edu.icesi.awc.back.model.sales.Customer;
import co.edu.icesi.awc.back.model.sales.Store;
import co.edu.icesi.awc.back.repository.AddresstypeRepository;
import co.edu.icesi.awc.back.repository.BusinessentityRepository;
import co.edu.icesi.awc.back.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.back.security.UserApp;
import co.edu.icesi.awc.back.security.UserType;
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
		//##Businessentity
		BusinessentityRepository br = c.getBean(BusinessentityRepository.class);
		br.save(new Businessentity());
		br.save(new Businessentity());
		br.save(new Businessentity());
		
		//##Addresstype
		AddresstypeRepository ar = c.getBean(AddresstypeRepository.class);
		ar.save(new Addresstype());
		ar.save(new Addresstype());
		ar.save(new Addresstype());
		
		//##Phonenumbertype
		PhonenumbertypeRepository hr = c.getBean(PhonenumbertypeRepository.class);
		hr.save(new Phonenumbertype());
		hr.save(new Phonenumbertype());
		
		//~~Person
		PersonDAO ps = c.getBean(PersonDAO.class);
		
		Person person1 = new Person();//:)
		person1.setModifieddate(new Timestamp(0));
		person1.setFirstname("Esteban");
		person1.setLastname("Ariza");
		person1.setTitle("Titulo2");
		person1.setPersontype("Humano");
		ps.save(person1);
		
		Person person2 = new Person();
		person2.setModifieddate(new Timestamp(0));
		person2.setFirstname("Isabella");
		person2.setLastname("Villamil");
		person2.setTitle("Titulo1");
		person2.setPersontype("Humano");
		ps.save(person2);
		
		Person person3 = new Person();
		person3.setModifieddate(new Timestamp(0));
		person3.setFirstname("Samuel");
		person3.setLastname("Acosta");
		person3.setTitle("Titulo2");
		person3.setPersontype("Alien");
		ps.save(person3);
		
		//~~Address[Special Query 10-12]
		AddressDAO as = c.getBean(AddressDAO.class);
		
		Address address1 = new Address();//:)
		address1.setAddressline1("Calle 15 #121-25");
		address1.setCity("Medellin");
		address1.setPostalcode("050021");
		address1.setModifieddate(makeTimestamp(2021, 3, 10));
		as.save(address1);
		
		Address address2 = new Address();
		address2.setAddressline1("Carrera 124");
		address2.setCity("Bogota");
		address2.setPostalcode("110211");
		address2.setModifieddate(makeTimestamp(2021, 3, 13));
		as.save(address2);
		
		Address address3 = new Address();
		address3.setAddressline1("Cl. 18 #122-135");
		address3.setCity("Cali");
		address3.setPostalcode("760008");
		address3.setModifieddate(makeTimestamp(2021, 3, 10));
		as.save(address3);
		
		Address address4 = new Address();
		address4.setAddressline1("Cl. 5 #31-90");
		address4.setCity("Pasto");
		address4.setPostalcode("760009");
		address4.setModifieddate(makeTimestamp(2021, 3, 11));
		as.save(address4);
		
		//~~Businessentityaddress
		//***
		List<Address> addresses = as.findAll();
		List<Addresstype> addresstypes = (List<Addresstype>) ar.findAll();
		List<Businessentity> businessentities = (List<Businessentity>) br.findAll();
		//***
		
		BusinessentityaddressDAO bs = c.getBean(BusinessentityaddressDAO.class);
		
		Businessentityaddress businessentityaddress1 = new Businessentityaddress();//:)
		businessentityaddress1.setAddress(addresses.get(0));
		businessentityaddress1.setAddresstype(addresstypes.get(0));
		businessentityaddress1.setBusinessentity(businessentities.get(0));
		businessentityaddress1.setId( factoryBusinessentityaddressPK(1,1,1) );
		bs.save(businessentityaddress1);
		
		Businessentityaddress businessentityaddress2 = new Businessentityaddress();
		businessentityaddress2.setAddress(addresses.get(1));
		businessentityaddress2.setAddresstype(addresstypes.get(1));
		businessentityaddress2.setBusinessentity(businessentities.get(1));
		businessentityaddress2.setId( factoryBusinessentityaddressPK(2,2,2) );
		bs.save(businessentityaddress2);
		
		Businessentityaddress businessentityaddress3 = new Businessentityaddress();
		businessentityaddress3.setAddress(addresses.get(2));
		businessentityaddress3.setAddresstype(addresstypes.get(2));
		businessentityaddress3.setBusinessentity(businessentities.get(2));
		businessentityaddress3.setId( factoryBusinessentityaddressPK(3,3,3) );
		bs.save(businessentityaddress3);
		
		Businessentityaddress businessentityaddress4 = new Businessentityaddress();
		businessentityaddress4.setAddress(addresses.get(3));
		businessentityaddress4.setAddresstype(addresstypes.get(2));
		businessentityaddress4.setBusinessentity(businessentities.get(2));
		businessentityaddress4.setId( factoryBusinessentityaddressPK(4,3,3) );
		bs.save(businessentityaddress4);
		
		//~~Personphone
		//***
		List<Person> persons = ps.findAll();
		List<Phonenumbertype> phonenumbertypes = (List<Phonenumbertype>) hr.findAll();
		//***
		
		PersonphoneDAO hs = c.getBean(PersonphoneDAO.class);
		
		Personphone personphone1 = new Personphone();//:)
		personphone1.setPerson(persons.get(0));
		personphone1.setPhonenumbertype(phonenumbertypes.get(0));
		personphone1.setId( factoryPersonphonePK(1,"3183741903",1) );
		hs.save(personphone1);
		
		Personphone personphone2 = new Personphone();
		personphone2.setPerson(persons.get(1));
		personphone2.setPhonenumbertype(phonenumbertypes.get(1));
		personphone2.setId( factoryPersonphonePK(2,"3158728989",2) );
		hs.save(personphone2);
		
		Personphone personphone3 = new Personphone();
		personphone3.setPerson(persons.get(2));
		personphone3.setPhonenumbertype(phonenumbertypes.get(1));
		personphone3.setId( factoryPersonphonePK(3,"3188031038",2) );
		hs.save(personphone3);
		
		Personphone personphone4 = new Personphone();
		personphone4.setPerson(persons.get(1));
		personphone4.setPhonenumbertype(phonenumbertypes.get(0));
		personphone4.setId( factoryPersonphonePK(2,"3182352827",1) );
		hs.save(personphone4);
		
		Personphone personphone5 = new Personphone();
		personphone5.setPerson(persons.get(2));
		personphone5.setPhonenumbertype(phonenumbertypes.get(1));
		personphone5.setId( factoryPersonphonePK(3,"3115890190",2) );
		hs.save(personphone5);
		
		//~Store
		StoreDAO str = c.getBean(StoreDAO.class);
		Store store = new Store();
		store.setDemographics("demo");
		store.setName("name");
		str.save(store);
		
		//~Customer
		CustomerDAO cr = c.getBean(CustomerDAO.class);
		Customer customer = new Customer();
		customer.setPersonid(1);
		cr.save(customer);
		//...
	}

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
	
	private static Timestamp makeTimestamp(int year, int month, int day) {
	    Calendar cal = new GregorianCalendar();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month - 1);
	    cal.set(Calendar.DATE, day);
	    
	    return new Timestamp(cal.getTimeInMillis());
	 }
	
}
