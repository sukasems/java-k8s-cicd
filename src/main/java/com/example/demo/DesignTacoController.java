package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// For logging
import lombok.extern.slf4j.Slf4j;


// This class will control "/design" http requests

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
		
	// For work with database
	private final IngredientRepository ingredientRepo;
	List<Ingredient> ingredientList = new ArrayList<>();
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
		// Get ingredient list from database
		this.ingredientRepo.findAll().forEach(i -> ingredientList.add(i));
	}

	// This method control GET http request
	// and prepare data fo input form
	@GetMapping
	public String showDesignForm(Model model) {
		// Add text field to model
		model.addAttribute("message", "Design you shaverma!");
		// Add Taco object to model (name design is a must)
		model.addAttribute("design", new Taco());
		model.addAttribute("ingredientList", ingredientList);
		// Pass data to view "design.html"
		return "design";
	}
	
	// This method handle POST method with data from input FORM
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, Model model) {
		if (errors.hasErrors()) {
			log.info("!Errors in user input" + errors.toString());
			// Add text with error messages field to model
			model.addAttribute("message", errors.toString());
			// Add Taco object to model (name design is a must)
			model.addAttribute("design", taco);
			model.addAttribute("ingredientList", ingredientList);
			// Pass data to view "design.html"
			return "design";
		}
		// We get Taco object from user input automatically
		log.info("Taco name: " + taco.getName());
		log.info("Taco ingredients: " + taco.getIngredients());
		// Process 
		return("redirect:/orders/current");
	}

}
