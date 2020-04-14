package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	
	private Long id;
	private Date createdAt;
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 chcracters long")
	private String name;
	
	private List<Ingredient> ingredients = new ArrayList<>();	
}
