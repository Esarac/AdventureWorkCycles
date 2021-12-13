package co.edu.icesi.awc.front.controller;

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
import co.edu.icesi.awc.back.repository.PhonenumbertypeRepository;
import co.edu.icesi.awc.front.businessdelegate.BusinessDelegateInterface;

@Controller
@RequestMapping("/personphone")
public class PersonphoneController {
	//Attributes
	private BusinessDelegateInterface businessDelegate;
	
	private PhonenumbertypeRepository phonenumbertypeRepository;
	
	//Constructor
	public PersonphoneController(BusinessDelegateInterface businessDelegate, PhonenumbertypeRepository phonenumbertypeRepository) {
		this.businessDelegate = businessDelegate;
		this.phonenumbertypeRepository =phonenumbertypeRepository;
	}
	
	//~Mapping
	//Index
	@GetMapping("/")
	public String indexGet(Model model) {
		model.addAttribute("personphones", businessDelegate.personphoneFindAll());
		return "personphone/index";
	}
	
	//Add
	@GetMapping("/add")
	public String addGet(Model model) {
		Personphone newPersonphone = new Personphone();
		newPersonphone.setId(new PersonphonePK());
		
		model.addAttribute("personphone", newPersonphone);
		model.addAttribute("persons", businessDelegate.personFindAll());
		model.addAttribute("phonenumbertypes", phonenumbertypeRepository.findAll());
		return "personphone/add";
	}
	
	@PostMapping("/add")
	public String addPost(@Validated @ModelAttribute Personphone personphone, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/personphone/";
		
		if (!action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				businessDelegate.personphoneSave(personphone);
			}
			else {
				model.addAttribute("persons", businessDelegate.personFindAll());
				model.addAttribute("phonenumbertypes", phonenumbertypeRepository.findAll());
				dir = "personphone/add";
			}
		}
		
		return dir;
	}
	
	//Edit
	@GetMapping("/edit/{id1}_{id2}_{id3}")
	public String editGet(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") String phonenumber, @PathVariable("id3") Integer phonenumbertypeid, Model model) {
		Personphone personphone = businessDelegate.personphoneFindById(businessentityid, phonenumber, phonenumbertypeid);
		model.addAttribute("personphone", personphone);
		return "personphone/edit";
	}

	@PostMapping("/edit/{id1}_{id2}_{id3}")
	public String editPost(@Validated @ModelAttribute Personphone personphone, BindingResult bindingResult, Model model, @PathVariable("id1") Integer businessentityid, @PathVariable("id2") String phonenumber, @PathVariable("id3") Integer phonenumbertypeid, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/personphone/";
		
		if (action != null && !action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				businessDelegate.personphoneUpdate(personphone);
			}
			else {
				dir = "personphone/edit";
			}
		}
		
		return dir;
	}
	
	//SpecialQuery
	@GetMapping("/query")
	public String queryGet(Model model) {
		model.addAttribute("personphones", businessDelegate.personphoneFindAll());
		model.addAttribute("personphonesQuery", businessDelegate.specialQuery());
		return "personphone/index";
	}
	//...
}
