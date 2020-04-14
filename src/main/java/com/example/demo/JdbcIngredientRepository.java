package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Work with database
 */

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;
		
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) 
		throws SQLException {
		return new Ingredient(rs.getString("id") , rs.getString("name"));
	}
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("SELECT id, name FROM Ingredient", 
				this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("SELECT id, name FROM Ingredient", 
				this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name) values (?, ?)",
	        ingredient.getId(), ingredient.getName());
		return ingredient;
	}

}
