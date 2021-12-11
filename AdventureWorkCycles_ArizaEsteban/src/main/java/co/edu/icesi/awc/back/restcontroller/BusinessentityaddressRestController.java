package co.edu.icesi.awc.back.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.icesi.awc.back.model.person.Businessentityaddress;
import co.edu.icesi.awc.back.service.BusinessentityaddressService;

@RestController
@RequestMapping("/api/businessentityaddress")
public class BusinessentityaddressRestController {
	
	//Attributes
	private BusinessentityaddressService busentaddressService;
	
	//Constructor
	@Autowired
	public BusinessentityaddressRestController(BusinessentityaddressService busentaddressService) {
		this.busentaddressService = busentaddressService;
	}
	
	//Methods
	//~Create
	@PostMapping
	public Businessentityaddress add(Businessentityaddress entity) {
		return busentaddressService.save(entity, null, null, null);//[Change]
	}
	
	//~Update
	@PutMapping
	public Businessentityaddress update(Businessentityaddress entity) {
		return busentaddressService.update(entity);
	}
	
	//~Read
	@GetMapping("/get/all")
	public Iterable<Businessentityaddress> getAll() {
		return busentaddressService.findAll();
	}
	
	@GetMapping("/{id1}_{id2}_{id3}")
	public Businessentityaddress getById(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") Integer addressid, @PathVariable("id3") Integer addresstypeid) {
		return busentaddressService.findByPK(businessentityid, addressid, addresstypeid).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
	
	//~Delete
	@DeleteMapping("/{id1}_{id2}_{id3}")
	public void delete(@PathVariable("id1") Integer businessentityid, @PathVariable("id2") Integer addressid, @PathVariable("id3") Integer addresstypeid) {
		busentaddressService.delete(businessentityid, addressid, addresstypeid);
	}

}
