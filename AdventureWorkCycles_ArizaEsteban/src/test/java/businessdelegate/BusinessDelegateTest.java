package businessdelegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.icesi.awc.AdventureWorkCyclesArizaEstebanApplication;
import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.front.businessdelegate.BusinessDelegate;

@SpringBootTest
@ContextConfiguration(classes = AdventureWorkCyclesArizaEstebanApplication.class)
@ExtendWith(SpringExtension.class)
public class BusinessDelegateTest {
	
	
	
	//Constants
	private static final ObjectMapper mapper = new ObjectMapper();
	
	//Attributes
	private BusinessDelegate businessDelegate;
	
	private MockRestServiceServer restServiceServer;
	
	//Constructor
	@Autowired
	public BusinessDelegateTest(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
		this.restServiceServer = MockRestServiceServer.createServer(businessDelegate.getRestTemplate());
	}
	
	//Methods
	@Nested
	@Tag("person")
	class PersonTest {
		@Test
		public void personFindAllTest() {
			//Pre
			ArrayList<Person> persons = new ArrayList<>();
			persons.add(new Person());
			persons.add(new Person());
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSON_URL)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(persons), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			ArrayList<Person> list = (ArrayList<Person>) businessDelegate.personFindAll();

			//Assert
			assertEquals(2, list.size());
			
			restServiceServer.verify();
		}
		
		@Test
		public void personSaveTest() {
			//Pre
			Person person1 = new Person();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSON_URL)))
						.andExpect(method(HttpMethod.POST))
						.andRespond(withSuccess(mapper.writeValueAsString(person1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Person person = businessDelegate.personSave(person1);

			//Assert
			assertNotNull(person);
			
			restServiceServer.verify();
		}
		
		@Test
		public void personFindByIdTest() {
			//Pre
			int id = 1;
			
			Person person1 = new Person();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSON_URL+id)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(person1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Person person = businessDelegate.personFindById(id);

			//Assert
			assertNotNull(person);
			
			restServiceServer.verify();
		}
		
		@Test
		public void personUpdate() {
			//Pre
			Person person1 = new Person();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSON_URL)))
						.andExpect(method(HttpMethod.PUT))
						.andRespond(withSuccess(mapper.writeValueAsString(person1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			businessDelegate.personUpdate(person1);

			//Assert
			restServiceServer.verify();
		}
	}
	
	@Nested
	@Tag("address")
	class AddressTest {
		@Test
		public void addressFindAllTest() {
			//Pre
			ArrayList<Address> address = new ArrayList<>();
			address.add(new Address());
			address.add(new Address());
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.ADDRESS_URL)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(address), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			ArrayList<Address> list = (ArrayList<Address>) businessDelegate.addressFindAll();

			//Assert
			assertEquals(2, list.size());
			
			restServiceServer.verify();
		}
		
		@Test
		public void addressSaveTest() {
			//Pre
			Address address1 = new Address();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.ADDRESS_URL)))
						.andExpect(method(HttpMethod.POST))
						.andRespond(withSuccess(mapper.writeValueAsString(address1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Address address = businessDelegate.addressSave(address1);

			//Assert
			assertNotNull(address);
			
			restServiceServer.verify();
		}
		
		@Test
		public void addressFindById() {
			//Pre
			int id = 1;
			
			Address address1 = new Address();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.ADDRESS_URL+id)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(address1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Address address = businessDelegate.addressFindById(id);

			//Assert
			assertNotNull(address);
			
			restServiceServer.verify();
		}
		
		@Test
		public void addressUpdateTest() {
			//Pre
			Address address1 = new Address();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.ADDRESS_URL)))
						.andExpect(method(HttpMethod.PUT))
						.andRespond(withSuccess(mapper.writeValueAsString(address1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			businessDelegate.addressUpdate(address1);

			//Assert
			restServiceServer.verify();
		}
	}
	
	@Nested
	@Tag("businessentityaddress")
	class BusinessentityaddressTest {
		@Test
		public void businessentityaddressFindAllTest() {
			//Pre
			ArrayList<Businessentityaddress> businessentityaddresses = new ArrayList<>();
			businessentityaddresses.add(new Businessentityaddress());
			businessentityaddresses.add(new Businessentityaddress());
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.BUSINESSENTITYADDRESS_URL)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(businessentityaddresses), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			ArrayList<Businessentityaddress> list = (ArrayList<Businessentityaddress>) businessDelegate.businessentityaddressFindAll();

			//Assert
			assertEquals(2, list.size());
			
			restServiceServer.verify();
		}
		
		@Test
		public void businessentityaddressSaveTest() {
			//Pre
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.BUSINESSENTITYADDRESS_URL)))
						.andExpect(method(HttpMethod.POST))
						.andRespond(withSuccess(mapper.writeValueAsString(businessentityaddress1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Businessentityaddress businessentityaddress = businessDelegate.businessentityaddressSave(businessentityaddress1);

			//Assert
			assertNotNull(businessentityaddress);
			
			restServiceServer.verify();
		}
		
		@Test
		public void businessentityaddressFindByIdTest() {
			//Pre
			int id1 = 1;
			int id2 = 1;
			int id3 = 1;
			
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.BUSINESSENTITYADDRESS_URL+id1+"_"+id2+"_"+id3)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(businessentityaddress1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Businessentityaddress businessentityaddress = businessDelegate.businessentityaddressFindById(id1,id2,id3);

			//Assert
			assertNotNull(businessentityaddress);
			
			restServiceServer.verify();
		}
		
		@Test
		public void businessentityaddressUpdateTest() {
			//Pre
			Businessentityaddress businessentityaddress1 = new Businessentityaddress();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.BUSINESSENTITYADDRESS_URL)))
						.andExpect(method(HttpMethod.PUT))
						.andRespond(withSuccess(mapper.writeValueAsString(businessentityaddress1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			businessDelegate.businessentityaddressUpdate(businessentityaddress1);

			//Assert
			restServiceServer.verify();
		}
	}
	
	@Nested
	@Tag("personphone")
	class PersonphoneTest {
		@Test
		public void personphoneFindAllTest() {
			//Pre
			ArrayList<Personphone> personphones = new ArrayList<>();
			personphones.add(new Personphone());
			personphones.add(new Personphone());
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSONPHONE_URL)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(personphones), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			ArrayList<Personphone> list = (ArrayList<Personphone>) businessDelegate.personphoneFindAll();

			//Assert
			assertEquals(2, list.size());
			
			restServiceServer.verify();
		}
		
		@Test
		public void personphoneSaveTest() {
			//Pre
			Personphone personphone1 = new Personphone();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSONPHONE_URL)))
						.andExpect(method(HttpMethod.POST))
						.andRespond(withSuccess(mapper.writeValueAsString(personphone1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Personphone personphone = businessDelegate.personphoneSave(personphone1);

			//Assert
			assertNotNull(personphone);
			
			restServiceServer.verify();
		}
		
		@Test
		public void personphoneFindByIdTest() {
			//Pre
			int id1 = 1;
			String id2 = "1";
			int id3 = 1;
			
			Personphone personphone1 = new Personphone();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSONPHONE_URL+id1+"_"+id2+"_"+id3)))
						.andExpect(method(HttpMethod.GET))
						.andRespond(withSuccess(mapper.writeValueAsString(personphone1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			Personphone personphone = businessDelegate.personphoneFindById(id1,id2,id3);

			//Assert
			assertNotNull(personphone);
			
			restServiceServer.verify();
		}
		
		@Test
		public void personphoneUpdateTest() {
			//Pre
			Personphone personphone1 = new Personphone();
			
			restServiceServer.reset();
			
			//Mockito
			try {
				restServiceServer.expect(ExpectedCount.once(),
						requestTo(new URI(BusinessDelegate.PERSONPHONE_URL)))
						.andExpect(method(HttpMethod.PUT))
						.andRespond(withSuccess(mapper.writeValueAsString(personphone1), MediaType.APPLICATION_JSON));
				
			} catch (URISyntaxException | JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			//Post
			businessDelegate.personphoneUpdate(personphone1);

			//Assert
			restServiceServer.verify();
		}
	}
	
}
