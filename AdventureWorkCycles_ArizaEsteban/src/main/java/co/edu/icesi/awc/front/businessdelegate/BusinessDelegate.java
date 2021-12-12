package co.edu.icesi.awc.front.businessdelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.restcontroller.PersonRestController;

@Component
public class BusinessDelegate {
	
	//Constants
	private final static String URL = "http://localhost:8081/api";
	private final static String PERSON_URL = URL + "/person/";
	private final static String ADDRESS_URL = URL + "/address/";
	private final static String BUSINESSENTITYADDRESS_URL = URL + "/businessentityaddress/";
	private final static String PERSONPHONE_URL = URL + "/personphone/";
	
	//Attributes
	private RestTemplate restTemplate;
	
	//Constructor
	public BusinessDelegate() {
		this.restTemplate = new RestTemplate();
		
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//		messageConverters.add(converter);
//		restTemplate.setMessageConverters(messageConverters);
		
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
	}
	
	//Methods
	//~Person
	public Iterable<Person> personFindAll(){
//		Person[] array = restTemplate.getForObject(PERSON_URL, Person[].class);
//		return Arrays.asList(array);
		return personRestController.getAll();
	}
	
	public Person personSave(Person person) {
//		HttpEntity<Person> request = new HttpEntity<Person>(person);
//		return restTemplate.postForObject(PERSON_URL, request, Person.class);
		return personRestController.add(person);
	}
	
	public Person personFindById(Integer id) {
//		return restTemplate.getForObject(PERSON_URL+id, Person.class);
		return personRestController.getById(id);
	}
	
	public void personUpdate(Person person) {
//		restTemplate.put(PERSON_URL, person, Person.class);
		personRestController.update(person);
	}
	
	//~Address
	public Iterable<Address> addressFindAll() {
		Address[] array = restTemplate.getForObject(ADDRESS_URL, Address[].class);
		return Arrays.asList(array);
	}
	
	public Address addressSave(Address address) {
		HttpEntity<Address> request = new HttpEntity<Address>(address);
		return restTemplate.postForObject(ADDRESS_URL, request, Address.class);
	}
	
	public Address addressFindById(Integer id) {
		return restTemplate.getForObject(ADDRESS_URL+id, Address.class);
	}
	
	public void addressUpdate(Address address) {
		restTemplate.put(ADDRESS_URL, address, Address.class);
	}
	
	//~Business Entity Address
	public Iterable<Businessentityaddress> businessentityaddressFindAll() {
		Businessentityaddress[] array = restTemplate.getForObject(BUSINESSENTITYADDRESS_URL, Businessentityaddress[].class);
		return Arrays.asList(array);
	}
	
	public Businessentityaddress businessentityaddressSave(Businessentityaddress businessentityaddress) {
		HttpEntity<Businessentityaddress> request = new HttpEntity<Businessentityaddress>(businessentityaddress);
		return restTemplate.postForObject(BUSINESSENTITYADDRESS_URL, request, Businessentityaddress.class);
	}
	
	public Businessentityaddress businessentityaddressFindById(Integer id1, Integer id2, Integer id3) {
		return restTemplate.getForObject(BUSINESSENTITYADDRESS_URL+id1+"_"+id2+"_"+id3, Businessentityaddress.class);
	}
	
	public void businessentityaddressUpdate(Businessentityaddress businessentityaddress) {
		restTemplate.put(BUSINESSENTITYADDRESS_URL, businessentityaddress, Businessentityaddress.class);
	}
	
	//~Person Phone
	
	
	//Shhhhhhh!
	@Autowired
	PersonRestController personRestController;
}
