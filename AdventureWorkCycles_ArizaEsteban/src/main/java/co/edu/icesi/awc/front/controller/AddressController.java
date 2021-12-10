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

import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.repository.StateprovinceRepository;
import co.edu.icesi.awc.back.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController {
	//Attribute
	private AddressService addressService;
	
	private StateprovinceRepository stateprovinceRepository;
	
	//Constructor
	@Autowired
	public AddressController(AddressService addressService, StateprovinceRepository stateprovinceRepository) {
		this.addressService = addressService;
		this.stateprovinceRepository = stateprovinceRepository;
	}
	
	//~Mapping
	//Index
	@GetMapping("/")
	public String indexGet(Model model) {
		model.addAttribute("addresses", addressService.findAll());
		return "address/index";
	}
	
	//Add
	@GetMapping("/add")
	public String addGet(Model model) {
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", stateprovinceRepository.findAll());
		return "address/add";
	}
	
	@PostMapping("/add")
	public String addPost(@Validated @ModelAttribute Address address, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/address/";
		
		if (!action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				addressService.save(address, address.getStateprovince().getStateprovinceid());//Cambiar
			}
			else {
				model.addAttribute("stateprovinces", stateprovinceRepository.findAll());
				dir = "address/add";
			}
		}
		
		return dir;
	}
	
	//Edit
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable("id") Integer id, Model model) {
		Optional<Address> address = addressService.findByPK(id);
		if (address.isEmpty())
			throw new IllegalArgumentException("Invalid address Id:" + id);
		model.addAttribute("address", address.get());
		model.addAttribute("stateprovinces", stateprovinceRepository.findAll());
		return "address/edit";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@Validated @ModelAttribute Address address, BindingResult bindingResult, Model model, @PathVariable("id") long id, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/address/";
		
		if (action != null && !action.equals("Cancel")) {
			if(!bindingResult.hasErrors()) {
				addressService.update(address, address.getStateprovince().getStateprovinceid());
			}
			else {
				dir = "address/edit";
			}
			
		}
		
		return dir;
	}
	//...
}
