package co.edu.icesi.awc.front.controller;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import co.edu.icesi.awc.back.model.person.Person;
import co.edu.icesi.awc.front.businessdelegate.BusinessDelegateInterface;

@Controller
@RequestMapping("/person")
public class PersonController {
	//Attribute
	private BusinessDelegateInterface businessDelegate;
	
	//Constructor
	@Autowired
	public PersonController(BusinessDelegateInterface businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
	
	//~Mapping
	//Index
	@GetMapping("/")
	public String indexGet(Model model) {
		model.addAttribute("persons", businessDelegate.personFindAll());
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
				businessDelegate.personSave(person);
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
		Person person = businessDelegate.personFindById(id);
		model.addAttribute("person", person);
		return "person/edit";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@Validated @ModelAttribute Person person, BindingResult bindingResult, Model model, @PathVariable("id") long id, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/person/";
		
		if (action != null && !action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				businessDelegate.personUpdate(person);
			}
			else {
				dir = "person/edit";
			}
			
		}
		
		return dir;
	}

	//SpecialQuery
	@GetMapping("/query")
	public String queryGet(Model model, @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
		model.addAttribute("persons", businessDelegate.personFindAll());
		model.addAttribute("personsQuery", businessDelegate.specialQuery(Timestamp.valueOf(from.atStartOfDay()), Timestamp.valueOf(to.atStartOfDay())));
		return "person/index";
	}
	//...
}
