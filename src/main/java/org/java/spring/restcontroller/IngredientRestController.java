package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.pojo.Ingredient;
import org.java.spring.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {
	
	@Autowired
	IngredientService ingServ;
	
	@GetMapping("/index")
	public ResponseEntity<List<Ingredient>> index(){
		List<Ingredient> ingredients = ingServ.findAll();
		
		if(ingredients.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(ingredients, HttpStatus.OK);
		
	}

}
