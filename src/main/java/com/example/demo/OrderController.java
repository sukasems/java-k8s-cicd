package com.example.demo;

import javax.validation.Valid;

/**
 * Show order form (orderForm.html and process user input)
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("message", "Make your order!");
		return("orderForm");
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, Model model) {
		if (errors.hasErrors()) {
			log.info("Errors in order input" + errors.toString());			
			model.addAttribute("order", order);
			model.addAttribute("message", errors.getAllErrors().toString());
			return("orderForm");
		}
		log.info("Process order: " + order);
		return("redirect:/");
	}

}
