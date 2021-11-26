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
import co.edu.icesi.awc.model.person.Address;
import co.edu.icesi.awc.model.person.Addresstype;
import co.edu.icesi.awc.model.person.Businessentity;
import co.edu.icesi.awc.model.person.Businessentityaddress;
import co.edu.icesi.awc.model.person.BusinessentityaddressPK;
import co.edu.icesi.awc.repository.AddressRepository;
import co.edu.icesi.awc.repository.AddresstypeRepository;
import co.edu.icesi.awc.repository.BusinessentityRepository;
import co.edu.icesi.awc.repository.BusinessentityaddressRepository;
import co.edu.icesi.awc.service.BusinessentityaddressService;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class BusinessentityaddressServiceTest {
	
	@InjectMocks
	private BusinessentityaddressService businessentityaddressService;
	@Mock
	private BusinessentityaddressRepository businessentityaddressRepository;
	@Mock
	private AddressRepository addressRepository;
	@Mock
	private BusinessentityRepository businessentityRepository;
	@Mock
	private AddresstypeRepository addresstypeRepository;//No decia en el enunciado
	
	@Autowired
	public BusinessentityaddressServiceTest(BusinessentityaddressService businessentityaddressService, BusinessentityaddressRepository businessentityaddressRepository, AddressRepository addressRepository, BusinessentityRepository businessentityRepository, AddresstypeRepository addresstypeRepository) {
		this.businessentityaddressService = businessentityaddressService;
		this.businessentityaddressRepository = businessentityaddressRepository;
		this.addressRepository = addressRepository;
		this.businessentityRepository = businessentityRepository;
		this.addresstypeRepository = addresstypeRepository;
	}
	
	@Nested
	@Tag("create")
	class Create{
		
		@Test
		public void saveTestCorrect() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			Address address1 = new Address();
			Businessentity businessentity1 = new Businessentity();
			Addresstype addresstype1 = new Addresstype();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address1));
			when(businessentityRepository.findById(1)).thenReturn(Optional.of(businessentity1));
			when(addresstypeRepository.findById(1)).thenReturn(Optional.of(addresstype1));
			when(businessentityaddressRepository.save(businessentityaddress1)).thenReturn(businessentityaddress1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 1);
			
			//Asserts
			assertNotNull(businessentityaddressSave);
			assertEquals(businessentityaddressSave.getAddress(), address1);
			assertEquals(businessentityaddressSave.getBusinessentity(), businessentity1);
			assertEquals(businessentityaddressSave.getAddresstype(), addresstype1);
			
			verify(addressRepository).findById(1);
			verify(businessentityRepository).findById(1);
			verify(addresstypeRepository).findById(1);
			verify(businessentityaddressRepository).save(businessentityaddress1);
		}
		
		@Test
		public void saveTestWrongAddress() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			Businessentity businessentity1 = new Businessentity();
			Addresstype addresstype1 = new Addresstype();
			
			when(addressRepository.findById(1)).thenReturn(Optional.empty());
			when(businessentityRepository.findById(1)).thenReturn(Optional.of(businessentity1));
			when(addresstypeRepository.findById(1)).thenReturn(Optional.of(addresstype1));
			when(businessentityaddressRepository.save(businessentityaddress1)).thenReturn(businessentityaddress1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 1);
			
			//Asserts
			assertNull(businessentityaddressSave);
			
			verify(addressRepository).findById(1);
			verify(businessentityRepository).findById(1);
			verify(addresstypeRepository).findById(1);
			verify(businessentityaddressRepository, times(0)).save(businessentityaddress1);
		}
		
		@Test
		public void saveTestWrongBusinessentity() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			Address address1 = new Address();
			Addresstype addresstype1 = new Addresstype();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address1));
			when(businessentityRepository.findById(1)).thenReturn(Optional.empty());
			when(addresstypeRepository.findById(1)).thenReturn(Optional.of(addresstype1));
			when(businessentityaddressRepository.save(businessentityaddress1)).thenReturn(businessentityaddress1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 1);
			
			//Asserts
			assertNull(businessentityaddressSave);
			
			verify(addressRepository).findById(1);
			verify(businessentityRepository).findById(1);
			verify(addresstypeRepository).findById(1);
			verify(businessentityaddressRepository, times(0)).save(businessentityaddress1);
		}
		
		@Test
		public void saveTestWrongAddresstype() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			Address address1 = new Address();
			Businessentity businessentity1 = new Businessentity();
			
			when(addressRepository.findById(1)).thenReturn(Optional.of(address1));
			when(businessentityRepository.findById(1)).thenReturn(Optional.of(businessentity1));
			when(addresstypeRepository.findById(1)).thenReturn(Optional.empty());
			when(businessentityaddressRepository.save(businessentityaddress1)).thenReturn(businessentityaddress1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.save(businessentityaddress1, 1, 1, 1);
			
			//Asserts
			assertNull(businessentityaddressSave);
			
			verify(addressRepository).findById(1);
			verify(businessentityRepository).findById(1);
			verify(addresstypeRepository).findById(1);
			verify(businessentityaddressRepository, times(0)).save(businessentityaddress1);
		}
	}
	
	@Nested
	@Tag("update")
	class Update{
		
		@Test
		public void updateTestCorrect() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			BusinessentityaddressPK pk = new BusinessentityaddressPK();
			pk.setAddressid(1);
			pk.setAddresstypeid(1);
			pk.setBusinessentityid(1);
			businessentityaddress1.setId(pk);
			
			Businessentityaddress businessentityaddress2 = new Businessentityaddress();
			
			Address address1 = new Address();
			Businessentity businessentity1 = new Businessentity();
			Addresstype addresstype1 = new Addresstype();
			
			when(businessentityaddressRepository.findById(pk)).thenReturn(Optional.of(businessentityaddress2));
			when(addressRepository.findById(1)).thenReturn(Optional.of(address1));
			when(businessentityRepository.findById(1)).thenReturn(Optional.of(businessentity1));
			when(addresstypeRepository.findById(1)).thenReturn(Optional.of(addresstype1));
			when(businessentityaddressRepository.save(businessentityaddress1)).thenReturn(businessentityaddress1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.update(businessentityaddress1);
			
			//Asserts
			assertNotNull(businessentityaddressSave);
			assertEquals(businessentityaddressSave.getAddress(), address1);
			assertEquals(businessentityaddressSave.getBusinessentity(), businessentity1);
			assertEquals(businessentityaddressSave.getAddresstype(), addresstype1);
			
			verify(businessentityaddressRepository).findById(pk);
			verify(addressRepository).findById(1);
			verify(businessentityRepository).findById(1);
			verify(addresstypeRepository).findById(1);
			verify(businessentityaddressRepository).save(businessentityaddress1);
		}
		
		@Test
		public void updateTestWrongNotFound() {
			//Set Up
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			BusinessentityaddressPK pk = new BusinessentityaddressPK();
			pk.setAddressid(1);
			pk.setAddresstypeid(1);
			pk.setBusinessentityid(1);
			businessentityaddress1.setId(pk);
			
			Address address1 = new Address();
			Businessentity businessentity1 = new Businessentity();
			Addresstype addresstype1 = new Addresstype();
			
			when(businessentityaddressRepository.findById(pk)).thenReturn(Optional.empty());
			when(addressRepository.findById(1)).thenReturn(Optional.of(address1));
			when(businessentityRepository.findById(1)).thenReturn(Optional.of(businessentity1));
			when(addresstypeRepository.findById(1)).thenReturn(Optional.of(addresstype1));
			when(businessentityaddressRepository.save(businessentityaddress1)).thenReturn(businessentityaddress1);
			
			//Method
			Businessentityaddress businessentityaddressSave = businessentityaddressService.update(businessentityaddress1);
			
			//Asserts
			assertNull(businessentityaddressSave);
			
			verify(businessentityaddressRepository).findById(pk);
			verify(addressRepository, times(0)).findById(1);
			verify(businessentityRepository, times(0)).findById(1);
			verify(addresstypeRepository, times(0)).findById(1);
			verify(businessentityaddressRepository, times(0)).save(businessentityaddress1);
		}
		
	}
	
}
