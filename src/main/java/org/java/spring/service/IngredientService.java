package org.java.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.java.spring.pojo.Ingredient;
import org.java.spring.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	public List<Ingredient> findAll(){
		return ingredientRepo.findAll();
	}
	
	public Ingredient findById(int id) {
		return ingredientRepo.findById(id).get();
	}
	
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public void delete(Ingredient ingredient) {
		
		ingredientRepo.delete(ingredient);
	}
	
	public List<Ingredient> ingredientsById(List<Integer> ids){
		List<Ingredient> ingredients = new ArrayList<>();
		for(Integer id : ids) {
			Ingredient ing = this.findById(id);
			ingredients.add(ing);
		}
		return ingredients;
	}
	
	public void deleteById(int id) {
		
		ingredientRepo.deleteById(id);
	}

}
