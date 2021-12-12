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

import co.edu.icesi.awc.back.model.person.Address;
import co.edu.icesi.awc.back.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressRestController {
	
	//Attributes
	private AddressService addressService;
	
	//Constructor
	@Autowired
	public AddressRestController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	//Methods
	//~Create
	@PostMapping
	public Address add(@RequestBody Address address) {
		return addressService.save(address, address.getStateprovince().getStateprovinceid());
	}
	
	//~Update
	@PutMapping
	public Address update(@RequestBody Address address) {
		return addressService.update(address, address.getStateprovince().getStateprovinceid());
	}
	
	//~Read
	@GetMapping
	public Iterable<Address> getAll() {
		return addressService.findAll();
	}
	
	@GetMapping("/{id}")
	public Address getById(@PathVariable("id") Integer addressid) {
		return addressService.findByPK(addressid).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
	
	@GetMapping("/get/modifieddate")
	public Iterable<Address> getByModifieddate(@RequestParam("modifieddate") Timestamp modifieddate) {
		return addressService.findByModifieddate(modifieddate);
	}
	
	//~Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer addressid) {
		addressService.delete(addressid);
	}

}
