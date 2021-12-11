package co.edu.icesi.awc.back.restcontroller;

import java.sql.Timestamp;

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

import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.back.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

	//Attributes
	private PersonService personService;
	
	//Constructor
	@Autowired
	public PersonRestController(PersonService personService) {
		this.personService = personService;
	}
	
	//Methods
	//~Create
	@PostMapping
	public Person add(@RequestBody Person person) {
		return personService.save(person);
	}
	
	//~Update
	@PutMapping
	public Person update(@RequestBody Person person) {
		return personService.update(person);
	}
	
	//~Read
	@GetMapping("/get/all")
	public Iterable<Person> getAll(){
		return personService.findAll();
	}
	
	@GetMapping("/{id}")
	public Person getById(@PathVariable("id") Integer id){
		return personService.findByPK(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
	
	@GetMapping("/get/title")
	public Iterable<Person> getByTitle(@RequestParam("title") String title){
		return personService.findByTitle(title);
	}
	
	@GetMapping("/get/persontype")
	public Iterable<Person> getByPersontype(@RequestParam("persontype") String persontype){
		return personService.findByPersontype(persontype);
	}
	
	@GetMapping("/get/specialquery")
	public Iterable<?> getBySpecialQuery(@RequestParam("start") Timestamp start, @RequestParam("end") Timestamp end){
		return personService.findBySpecialQuery(start, end);
	}
	
	//~Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		personService.delete(id);
	}
}
