package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter<String, Ingredient>{
	static List<Ingredient> ingredientList = Arrays.asList(
			new Ingredient("LAV", "Lavash"), new Ingredient("KUR", "Kura"));

	@Override
	public Ingredient convert(String source) {
		for (Ingredient ingredient : ingredientList) {
			if (source.equals(ingredient.getId()))
				return ingredient;
		}
		return null;
	}

}
