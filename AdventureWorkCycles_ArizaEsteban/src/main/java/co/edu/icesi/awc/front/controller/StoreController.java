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
import co.edu.icesi.awc.back.dao.StoreDAO;
import co.edu.icesi.awc.back.model.sales.Store;

@Controller
@RequestMapping("/store")
public class StoreController {
    //Attributes
    private StoreDAO storeService;

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
    public String addPost(@Validated @ModelAttribute Store store, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action) {
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
        Store store = storeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        model.addAttribute("store", store);
        return "store/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@Validated @ModelAttribute Store store, BindingResult bindingResult,  @PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
        String dir = "redirect:/store/";
       
        if(action != null && !action.equals("Cancel")) {
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
    @GetMapping("/del/{id}")
    public String deleteStore(@PathVariable("id") Integer id, Model model) {
        Store store = storeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        storeService.delete(store);
        model.addAttribute("stores", storeService.findAll());
        return "store/index";
    }
}
