package co.edu.icesi.awc.front.controller;

import java.util.Optional;

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

import co.edu.icesi.awc.back.model.person.Personphone;
import co.edu.icesi.awc.back.model.person.PersonphonePK;
import co.edu.icesi.awc.back.repository.PersonRepository;
import co.edu.icesi.awc.back.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.back.service.PersonphoneService;

@Controller
@RequestMapping("/personphone")
public class PersonphoneController {
	//Attributes
	private PersonphoneService personphoneService;
	
	private PersonRepository personRepository;
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	//Constructor
	public PersonphoneController(PersonphoneService personphoneService, PersonRepository personRepository, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.personphoneService = personphoneService;
		this.personRepository = personRepository;
		this.phonenumbertypeRepository =phonenumbertypeRepository;
	}
	
	//~Mapping
	//Index
	@GetMapping("/")
	public String indexGet(Model model) {
		model.addAttribute("personphones", personphoneService.findAll());
		return "personphone/index";
	}
	
	//Add
	@GetMapping("/add")
	public String addGet(Model model) {
		Personphone newPersonphone = new Personphone();
		newPersonphone.setId(new PersonphonePK());
		
		model.addAttribute("personphone", newPersonphone);
		model.addAttribute("persons", personRepository.findAll());
		model.addAttribute("phonenumbertypes", phonenumbertypeRepository.findAll());
		return "personphone/add";
	}
	
	@PostMapping("/add")
	public String addPost(@Validated @ModelAttribute Personphone personphone, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/personphone/";
		
		if (!action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				personphoneService.save(personphone, personphone.getId().getPhonenumber(), personphone.getPerson().getBusinessentityid(), personphone.getPhonenumbertype().getPhonenumbertypeid());
			}
			else {
				model.addAttribute("persons", personRepository.findAll());
				model.addAttribute("phonenumbertypes", phonenumbertypeRepository.findAll());
				dir = "personphone/add";
			}
		}
		
		return dir;
	}
	
	//Edit
	@GetMapping("/edit/{id1}_{id2}_{id3}")
	public String editGet(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") String phonenumber, @PathVariable("id3") Integer phonenumbertypeid, Model model) {
		Optional<Personphone> personphone = personphoneService.findByPK(businessentityid, phonenumber, phonenumbertypeid);
		if (personphone.isEmpty())
			throw new IllegalArgumentException("Invalid address Id:(" + businessentityid+","+phonenumber+","+phonenumbertypeid+")");
		model.addAttribute("personphone", personphone.get());
		return "personphone/edit";
	}

	@PostMapping("/edit/{id1}_{id2}_{id3}")
	public String editPost(@Validated @ModelAttribute Personphone personphone, BindingResult bindingResult, Model model, @PathVariable("id1") Integer businessentityid, @PathVariable("id2") String phonenumber, @PathVariable("id3") Integer phonenumbertypeid, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/personphone/";
		
		if (action != null && !action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				personphoneService.update(personphone);
			}
			else {
				dir = "personphone/edit";
			}
		}
		
		return dir;
	}
	//...
}
