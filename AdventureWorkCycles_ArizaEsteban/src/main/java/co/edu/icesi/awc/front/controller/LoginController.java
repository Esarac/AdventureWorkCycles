package co.edu.icesi.awc.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	//Index
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		return "access-denied";
	}
	
}
