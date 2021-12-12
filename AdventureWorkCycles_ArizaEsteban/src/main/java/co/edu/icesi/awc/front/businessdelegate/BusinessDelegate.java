package co.edu.icesi.awc.front.businessdelegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Personphone;
import lombok.Getter;
import lombok.Setter;

@Component
public class BusinessDelegate {
	
	//Constants
	public final static String URL = "http://localhost:8081/api";
	public final static String PERSON_URL = URL + "/person/";
	public final static String ADDRESS_URL = URL + "/address/";
	public final static String BUSINESSENTITYADDRESS_URL = URL + "/businessentityaddress/";
	public final static String PERSONPHONE_URL = URL + "/personphone/";
	
	//Attributes
	@Getter @Setter
	private RestTemplate restTemplate;
	
	//Constructor
	public BusinessDelegate() {
		this.restTemplate = new RestTemplate();
		
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
	}
	
	//Methods
	//~Person
	public Iterable<Person> personFindAll(){
		Iterable<Person> array = restTemplate.getForObject(PERSON_URL, Iterable.class);
		return array;
	}
	
	public Person personSave(Person person) {
		HttpEntity<Person> request = new HttpEntity<Person>(person);
		return restTemplate.postForObject(PERSON_URL, request, Person.class);
	}
	
	public Person personFindById(Integer id) {
		return restTemplate.getForObject(PERSON_URL+id, Person.class);
	}
	
	public void personUpdate(Person person) {
		restTemplate.put(PERSON_URL, person, Person.class);
	}
	
	//~Address
	public Iterable<Address> addressFindAll() {
		Iterable<Address> array= restTemplate.getForObject(ADDRESS_URL, Iterable.class);
		return array;
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
		Iterable<Businessentityaddress> array = restTemplate.getForObject(BUSINESSENTITYADDRESS_URL, Iterable.class);
		return array;
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
	public Iterable<Personphone> personphoneFindAll() {
		Iterable<Personphone> array = restTemplate.getForObject(PERSONPHONE_URL, Iterable.class);
		return array;
	}
	
	public Personphone personphoneSave(Personphone personphone) {
		HttpEntity<Personphone> request = new HttpEntity<Personphone>(personphone);
		return restTemplate.postForObject(PERSONPHONE_URL, request, Personphone.class);
	}
	
	public Personphone personphoneFindById(Integer id1, String id2, Integer id3) {
		return restTemplate.getForObject(PERSONPHONE_URL+id1+"_"+id2+"_"+id3, Personphone.class);
	}
	
	public void personphoneUpdate(Personphone personphone) {
		restTemplate.put(PERSONPHONE_URL, personphone, Personphone.class);
	}
	
}
