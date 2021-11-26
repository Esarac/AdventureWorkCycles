package co.edu.icesi.awc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.awc.model.person.Person;
import co.edu.icesi.awc.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	//Attribute
	private PersonService personService;
	
	//Constructor
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	//~Mapping
	//Index
	@GetMapping("/")
	public String indexGet(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "person/index";
	}
	
	//Add
	@GetMapping("/add")
	public String addGet(Model model) {
		model.addAttribute("person", new Person());
		return "person/add";
	}
	
	@PostMapping("/add")
	public String addPost(@Validated @ModelAttribute Person person, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/person/";
		
		if (!action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				personService.save(person);
			}
			else {
				dir = "person/add";
			}
		}
		
		return dir;
	}
	
	//Edit
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personService.findByPK(id);
		if (person.isEmpty())
			throw new IllegalArgumentException("Invalid person Id:" + id);
		model.addAttribute("person", person.get());
		return "person/edit";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@Validated @ModelAttribute Person person, BindingResult bindingResult, Model model, @PathVariable("id") long id, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/person/";
		
		if (action != null && !action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				personService.update(person);
			}
			else {
				dir = "person/edit";
			}
			
		}
		
		return dir;
	}
	//...
}
