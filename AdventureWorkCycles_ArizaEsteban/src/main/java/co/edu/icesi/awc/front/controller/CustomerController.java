package co.edu.icesi.awc.front.controller;

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
import co.edu.icesi.awc.back.dao.CustomerDAO;
import co.edu.icesi.awc.back.model.sales.Customer;
import co.edu.icesi.awc.back.service.PersonService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    //Attributes
    private CustomerDAO customerService;
    private PersonService personService;

    //Constructor
    @Autowired
    public CustomerController(CustomerDAO customerService, PersonService personService) {
        this.customerService = customerService;
        this.personService = personService;
    }

    //~Mapping
    //Index
    @GetMapping("/")
    public String indexGet(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customer/index";
    }

    //Add
    @GetMapping("/add")
    public String addGet(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("persons", personService.findAll());
        return "customer/add";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action) {
        String dir = "redirect:/customer/";

        if(!action.equals("Cancel")) {
            if(!bindingResult.hasErrors()) {
                customerService.save(customer);
            }
            else {
                dir = "customer/add";
            }
        }

        return dir;
    }

    //Edit
    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        model.addAttribute("customer", customer);
        model.addAttribute("persons", personService.findAll());
        return "customer/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model, @PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
        String dir = "redirect:/customer/";

        if(!action.equals("Cancel")) {
            if(!bindingResult.hasErrors()) {
                customerService.save(customer);
            }
            else {
                model.addAttribute("persons", personService.findAll());
                dir = "customer/edit";
            }
        }
        return dir;
    }

    //Delete
    @GetMapping("/del/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        customerService.delete(customer);
        model.addAttribute("customers", customerService.findAll());
        return "customer/index";
    }
}
