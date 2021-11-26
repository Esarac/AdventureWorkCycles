package integration.dao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.dao.AddressDAO;
import co.edu.icesi.awc.model.person.Address;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class AddressDAOTest {

	private AddressDAO addressDao;
	
	@Autowired
	public AddressDAOTest(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}
	
	@Test
	@Order(1)
	public void saveTest() {
		//Set Up
		Address address1 = new Address();
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
		
		addressDao.save(address1);
		addressDao.save(address2);
		addressDao.save(address3);
		
		List<Address> addresses = addressDao.findAll();
		
		//Assert
		assertEquals(addresses.size(), 3);
		
		assertEquals(addresses.get(0).getCity(), "Medellin");
		assertEquals(addresses.get(1).getCity(), "Bogota");
		assertEquals(addresses.get(2).getCity(), "Cali");
	}
	
	@Test
	@Order(2)
	public void findByIdTest() {
		//Assert
		assertEquals(addressDao.findById(1).getCity(), "Medellin");
		assertEquals(addressDao.findById(2).getCity(), "Bogota");
		assertEquals(addressDao.findById(3).getCity(), "Cali");
		assertNull(addressDao.findById(4));
	}
	
	@Test
	@Order(3)
	public void findAllTest() {
		//Set Up
		List<Address> addresses = addressDao.findAll();
		
		//Assert
		assertEquals(addresses.size(), 3);
		
		assertEquals(addresses.get(0).getCity(), "Medellin");
		assertEquals(addresses.get(1).getCity(), "Bogota");
		assertEquals(addresses.get(2).getCity(), "Cali");
	}
	
	@Test
	@Order(4)
	public void findByModifieddateTest() {
		//Set Up
		List<Address> addresses = addressDao.findByModifieddate(new Timestamp(0));
		
		//Assert
		assertEquals(addresses.size(), 2);
		
		assertEquals(addresses.get(0).getCity(), "Medellin");
		assertEquals(addresses.get(1).getCity(), "Cali");
	}
	
	@Test
	@Order(5)
	public void deleteTest() {
		//Set Up
		Address address1 = addressDao.findById(1);
		
		addressDao.delete(address1);
		
		List<Address> addresses = addressDao.findAll();
		
		//Assert
		assertEquals(addresses.size(), 2);
		
		assertEquals(addresses.get(0).getCity(), "Bogota");
		assertEquals(addresses.get(1).getCity(), "Cali");
	}
	
	@Test
	@Order(6)
	public void deleteByIdTest() {
		//Set Up
		addressDao.deleteById(3);
		
		List<Address> addresses = addressDao.findAll();
		
		//Assert
		assertEquals(addresses.size(), 1);
		
		assertEquals(addresses.get(0).getCity(), "Bogota");
	}
}
