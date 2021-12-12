package co.edu.icesi.awc.front.businessdelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.restcontroller.AddressRestController;
import co.edu.icesi.awc.back.restcontroller.BusinessentityaddressRestController;
import co.edu.icesi.awc.back.restcontroller.PersonRestController;
import co.edu.icesi.awc.back.restcontroller.PersonphoneRestController;

@Component
public class BusinessDelegateInterface {
		
		//Methods
		//~Person
		public Iterable<Person> personFindAll(){
			return restTemplate.getAll();
		}
		
		public Person personSave(Person person) {
			return restTemplate.add(person);
		}
		
		public Person personFindById(Integer id) {
			return restTemplate.getById(id);
		}
		
		public void personUpdate(Person person) {
			restTemplate.update(person);
		}
		
		//~Address
		public Iterable<Address> addressFindAll() {
			return restTemplatea.getAll();
		}
		
		public Address addressSave(Address address) {
			return restTemplatea.add(address);
		}
		
		public Address addressFindById(Integer id) {
			return restTemplatea.getById(id);
		}
		
		public void addressUpdate(Address address) {
			restTemplatea.update(address);
		}
		
		//~Business Entity Address
		public Iterable<Businessentityaddress> businessentityaddressFindAll() {
			return restTemplateb.getAll();
		}
		
		public Businessentityaddress businessentityaddressSave(Businessentityaddress businessentityaddress) {
			return restTemplateb.add(businessentityaddress);
		}
		
		public Businessentityaddress businessentityaddressFindById(Integer id1, Integer id2, Integer id3) {
			return restTemplateb.getById(id1,id2,id3);
		}
		
		public void businessentityaddressUpdate(Businessentityaddress businessentityaddress) {
			restTemplateb.update(businessentityaddress);
		}
		
		//~Person Phone
		public Iterable<Personphone> personphoneFindAll() {
			return restTemplatep.getAll();
		}
		
		public Personphone personphoneSave(Personphone personphone) {
			return restTemplatep.add(personphone);
		}
		
		public Personphone personphoneFindById(Integer id1, String id2, Integer id3) {
			return restTemplatep.getById(id1,id2,id3);
		}
		
		public void personphoneUpdate(Personphone personphone) {
			restTemplatep.update(personphone);
		}
		
		@Autowired
		PersonRestController restTemplate;
		@Autowired
		AddressRestController restTemplatea;
		@Autowired
		BusinessentityaddressRestController restTemplateb;
		@Autowired
		PersonphoneRestController restTemplatep;
}
