package integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import co.edu.icesi.awc.model.person.Address;
import co.edu.icesi.awc.model.person.Stateprovince;
import co.edu.icesi.awc.repository.StateprovinceRepository;
import co.edu.icesi.awc.service.AddressService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class AddressServiceTest {
	
	private AddressService addressService;
	
	private StateprovinceRepository stateprovinceRepository;

	@Autowired
	public AddressServiceTest(AddressService addressService, StateprovinceRepository stateprovinceRepository) {
		this.addressService = addressService;
		this.stateprovinceRepository = stateprovinceRepository;
	}
	
	@Nested
	@Tag("create")
	@TestMethodOrder(OrderAnnotation.class)
	class Create{
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Stateprovince stateprovince1 = new Stateprovince();
			stateprovinceRepository.save(stateprovince1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 15 #121-25", addressSave.getAddressline1());
			assertEquals("Cali", addressSave.getCity());
			assertEquals("760008", addressSave.getPostalcode());
			
			Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(1);
			
			assertEquals(addressSave.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
			//assertEquals(stateprovinceSave.get().getAddresses().get(0).getAddressid(), addressSave.getAddressid());
		}
		
		@Test
		@Order(2)
		public void saveTestWrongAddressline1() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("   ");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(3)
		public void saveTestWrongCity() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("   ");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(4)
		public void saveTestWrongPostalcode() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("   ");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(5)
		public void saveTestWrongStateprovince() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
	}
	
	@Nested
	@Tag("update")
	@TestMethodOrder(OrderAnnotation.class)
	class Update{
		@Test
		@Order(1)
		public void updateTestCorrect() {
			//Set up
			Address address1 = addressService.findByPK(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			Stateprovince stateprovince1 = new Stateprovince();
			stateprovinceRepository.save(stateprovince1);
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 5", addressSave.getAddressline1());
			assertEquals("Bogota", addressSave.getCity());
			assertEquals("760009", addressSave.getPostalcode());

			Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(2);
			
			assertEquals(addressSave.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
			//assertEquals(stateprovinceSave.get().getAddresses().get(0).getAddressid(), addressSave.getAddressid());
			
		}
		
		@Test
		@Order(2)
		public void updateTestWrongAddressline1() {
			//Set up
			Address address1 = addressService.findByPK(1).get();
			address1.setAddressline1("   ");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(3)
		public void updateTestWrongCity() {
			//Set up
			Address address1 = addressService.findByPK(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("   ");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(4)
		public void updateTestWrongPostalcode() {
			//Set up
			Address address1 = addressService.findByPK(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("   ");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(5)
		public void updateTestWrongStateprovince() {
			//Set up
			Address address1 = addressService.findByPK(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 3);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(6)
		public void updateTestWrongNotFound() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(2);
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
	}
}
