package co.edu.icesi.awc.back.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.service.PersonphoneService;

@RestController
@RequestMapping("/api/personphone")
public class PersonphoneRestController {

	//Attributes
	private PersonphoneService personphoneService;
	
	//Contructor
	@Autowired
	public PersonphoneRestController(PersonphoneService personphoneService) {
		this.personphoneService = personphoneService;
	}
	
	//Methods
	//~Create
	@PostMapping
	public Personphone add(@RequestBody Personphone personphone) {
		return personphoneService.save(personphone, null, null, null);//[Change]
	}
	
	//~Update
	@PutMapping
	public Personphone update(@RequestBody Personphone personphone) {
		return personphoneService.update(personphone);
	}
	
	//~Read
	@GetMapping("/get/all")
	public Iterable<Personphone> getAll(){
		return personphoneService.findAll();
	}
	
	@GetMapping("/{id1}_{id2}_{id3}")
	public Personphone getById(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") String phonenumber, @PathVariable("id3") Integer phonenumbertypeid) {
		return personphoneService.findByPK(businessentityid, phonenumber, phonenumbertypeid).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
	
	@GetMapping("/get/prefix")
	public Iterable<Personphone> getByPrefix(@RequestParam("prefix") String prefix) {
		return personphoneService.findByPrefix(prefix);
	}
	
	@GetMapping("/get/phonenumbertype")
	public Iterable<Personphone> getByPhonenumbertype(@RequestParam("phonenumbertypeid") Integer phonenumbertypeid) {
		return personphoneService.findByPhonenumbertype(phonenumbertypeid);
	}
	
	@GetMapping("/get/specialquery")
	public Iterable<Personphone> getBySpecialQuery(){
		return personphoneService.findBySpecialQuery();
	}
	
	//~Delete
	@DeleteMapping("/{id1}_{id2}_{id3}")
	public void delete(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") String phonenumber, @PathVariable("id3") Integer phonenumbertypeid) {
		personphoneService.delete(businessentityid, phonenumber, phonenumbertypeid);
	}
}
