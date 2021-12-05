package com.learning.vault.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaultHome {
	
	@GetMapping("/home")
	public String showHomePage() {
		return "home";
	}
}
