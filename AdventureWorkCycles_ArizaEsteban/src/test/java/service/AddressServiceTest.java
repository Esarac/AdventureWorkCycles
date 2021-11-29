package service;

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
import co.edu.icesi.awc.dao.AddressDAO;
import co.edu.icesi.awc.model.person.Address;
import co.edu.icesi.awc.model.person.Stateprovince;
import co.edu.icesi.awc.repository.AddressRepository;
import co.edu.icesi.awc.repository.StateprovinceRepository;
import co.edu.icesi.awc.service.AddressService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;
	@Mock
	private AddressDAO addressRepository;
	@Mock
	private StateprovinceRepository stateprovinceRepository;
	
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 15 #121-25", addressSave.getAddressline1());
			assertEquals("Cali", addressSave.getCity());
			assertEquals("760008", addressSave.getPostalcode());
			assertEquals(stateprovince1, addressSave.getStateprovince());
			
			//Verify that the method have been called with this args
			verify(stateprovinceRepository).findById(1);
			verify(addressRepository).save(address1);
		}
		
		@Test
		public void saveTestWrongAddressline1() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("   ");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongCity() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("   ");
			address1.setPostalcode("760008");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongPostalcode() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("   ");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongStateprovince() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.empty());
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			//Verify that the method have been called with this args
			verify(stateprovinceRepository).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
	}
	
	@Nested
	@Tag("update")
	class Update {
		
		@Test
		public void updateTestCorrect() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Address address2 = new Address();
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address2));
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 15 #121-25", addressSave.getAddressline1());
			assertEquals("Cali", addressSave.getCity());
			assertEquals("760008", addressSave.getPostalcode());
			assertEquals(stateprovince1, addressSave.getStateprovince());
			
			verify(addressRepository).findById(1);
			verify(stateprovinceRepository).findById(1);
			verify(addressRepository).save(address1);
		}
		
		@Test
		public void updateTestWrongAddressline1() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("   ");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Address address2 = new Address();
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address2));
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(addressRepository).findById(1);
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongCity() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("   ");
			address1.setPostalcode("760008");
			
			Address address2 = new Address();
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address2));
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(addressRepository).findById(1);
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongPostalcode() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("   ");
			
			Address address2 = new Address();
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address2));
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(addressRepository).findById(1);
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongStateprovince() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Address address2 = new Address();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address2));
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.empty());
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(addressRepository).findById(1);
			verify(stateprovinceRepository).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongNotFound() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(addressRepository.findById(1)).thenReturn(Optional.empty());
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(addressRepository.save(address1)).thenReturn(address1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(addressRepository).findById(1);
			verify(stateprovinceRepository, times(0)).findById(1);
			verify(addressRepository, times(0)).save(address1);
		}
	}
	
}
