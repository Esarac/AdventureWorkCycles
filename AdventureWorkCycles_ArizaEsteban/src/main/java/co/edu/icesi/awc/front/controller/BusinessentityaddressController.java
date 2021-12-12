package co.edu.icesi.awc.front.controller;

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

import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.repository.AddressRepository;
import co.edu.icesi.awc.back.repository.AddresstypeRepository;
import co.edu.icesi.awc.back.repository.BusinessentityRepository;
import co.edu.icesi.awc.back.service.BusinessentityaddressService;
import co.edu.icesi.awc.front.businessdelegate.BusinessDelegate;

@Controller
@RequestMapping("/businessentityaddress")
public class BusinessentityaddressController {
	//Attribute
	private BusinessDelegate businessDelegate;
	
	private BusinessentityRepository businessentityRepository;
	private AddresstypeRepository addresstypeRepository;
	
	//Constructor
	@Autowired
	public BusinessentityaddressController(BusinessDelegate businessDelegate, BusinessentityRepository businessentityRepository, AddresstypeRepository addresstypeRepository) {
		this.businessDelegate = businessDelegate;
		this.businessentityRepository =businessentityRepository;
		this.addresstypeRepository = addresstypeRepository;
	}
	
	//~Mapping
	//Index
	@GetMapping("/")
	public String indexGet(Model model) {
		model.addAttribute("businessentityaddresses", businessDelegate.businessentityaddressFindAll());
		return "businessentityaddress/index";
	}
	
	//Add
	@GetMapping("/add")
	public String addGet(Model model) {
		model.addAttribute("businessentityaddress", new Businessentityaddress());
		model.addAttribute("businessentities", businessentityRepository.findAll());
		model.addAttribute("addresses", businessDelegate.addressFindAll());
		model.addAttribute("addresstypes", addresstypeRepository.findAll());
		return "businessentityaddress/add";
	}
	
	@PostMapping("/add")
	public String addPost(@Validated @ModelAttribute Businessentityaddress businessentityaddress, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/businessentityaddress/";
		
		if (!action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				businessDelegate.businessentityaddressSave(businessentityaddress);
			}
			else {
				model.addAttribute("businessentities", businessentityRepository.findAll());
				model.addAttribute("addresses", businessDelegate.addressFindAll());
				model.addAttribute("addresstypes", addresstypeRepository.findAll());
				dir = "businessentityaddress/add";
			}
		}
		
		return dir;
	}
	
	//Edit
	@GetMapping("/edit/{id1}_{id2}_{id3}")
	public String editGet(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") Integer addressid, @PathVariable("id3") Integer addresstypeid, Model model) {
		Businessentityaddress businessentityaddress = businessDelegate.businessentityaddressFindById(businessentityid, addressid, addresstypeid);
		model.addAttribute("businessentityaddress", businessentityaddress);
		model.addAttribute("businessentities", businessentityRepository.findAll());
		model.addAttribute("addresses", businessDelegate.addressFindAll());
		model.addAttribute("addresstypes", addresstypeRepository.findAll());
		return "businessentityaddress/edit";
	}

	@PostMapping("/edit/{id1}_{id2}_{id3}")
	public String editPost(@Validated @ModelAttribute Businessentityaddress businessentityaddress, BindingResult bindingResult, Model model, @PathVariable("id1") Integer businessentityid, @PathVariable("id2") Integer addressid, @PathVariable("id3") Integer addresstypeid, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/businessentityaddress/";
		
		if (action != null && !action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				businessDelegate.businessentityaddressUpdate(businessentityaddress);
			}
			else {
				dir = "businessentityaddress/edit";
			}
			
		}
		
		return dir;
	}
	//...

}
