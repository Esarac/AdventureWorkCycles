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

import co.edu.icesi.awc.back.dao.StoreDAO;
import co.edu.icesi.awc.back.model.sales.Store;

@Controller
@RequestMapping("/store")
public class StoreController {
    //Atribute
    StoreDAO storeService;

    //Constructor
    @Autowired
    public StoreController(StoreDAO storeService) {
        this.storeService = storeService;
    }

    //~Mapping
    //Index
    @GetMapping("/")
    public String indexGet(Model model) {
        model.addAttribute("stores", storeService.findAll());
        return "store/index";
    }

    //Add
    @GetMapping("/add")
    public String addGet(Model model) {
        model.addAttribute("store", new Store());
        return "store/add";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute Store store, Model model, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action) {
        String dir = "redirect:/store/";
        
        if(!action.equals("Cancel")) {
            if(!bindingResult.hasErrors()) {
                storeService.save(store);
            }
            else {
                dir = "store/add";
            }
        }

        return dir;
    }

    //Edit
    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable("id") Integer id, Model model) {
        Optional<Store> store = storeService.findById(id);
        if(store.isEmpty()) {
            throw new IllegalArgumentException("Invalid id");
        }
        model.addAttribute("store", store.get());
        return "store/edit";
    }

    @PostMapping("edit/{id}")
    public String editPost(@Validated @ModelAttribute Store store, @PathVariable("id") Integer id, Model model, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action) {
        String dir = "redirect:/store/";

        if(!action.equals("Cancel")) {
            if(!bindingResult.hasErrors()) {
                storeService.save(store);
            }
            else {
                dir = "store/edit";
            }

        }
        
        return dir;
    }

    //Delete
}
