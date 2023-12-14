package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.DTO.PizzaRestDTO;
import org.java.spring.pojo.Ingredient;
import org.java.spring.pojo.Pizza;
import org.java.spring.service.IngredientService;
import org.java.spring.service.PizzaService;
import org.java.spring.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PizzaRestController {
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	SpecialOfferService spOffService;
	
	@Autowired
	IngredientService ingService;
	
	@GetMapping("index")
	public ResponseEntity<List<Pizza>> index(@RequestParam(required=false) String name){
		
		if(name != null) {
			List <Pizza> pizzas = pizzaService.findByName(name);
			return new ResponseEntity<>(pizzas, HttpStatus.OK);
		}
		
		List <Pizza> pizzas = pizzaService.findAll();
		
		if(pizzas.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("pizza/{id}")
	public ResponseEntity <Pizza> searchByTitle(@PathVariable int  id){
		
		Pizza pizzas = pizzaService.findById(id);
		System.out.println(pizzas);
		
		if(pizzas == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity <Pizza> create(@RequestBody PizzaRestDTO pizzaDTO){
		Pizza pizza = new Pizza();
		
		pizza.setName(pizzaDTO.getName());
		pizza.setPrice(pizzaDTO.getPrice());
		pizza.setDescription(pizzaDTO.getDescription());
		pizza.setUrl(pizzaDTO.getUrl());
		
		if(pizzaDTO.getIngredientId() != null) {
			List<Ingredient> ingredients = ingService.ingredientsById(pizzaDTO.getIngredientId());
			pizza.setIngredients(ingredients);
		}
		
//		
		pizzaService.save(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Pizza> update(@RequestBody Pizza updatePizza,
										@PathVariable int id){
		Pizza pizza = pizzaService.findById(id);
		if(pizza == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(updatePizza.getName() != null) pizza.setName(updatePizza.getName());
		if(updatePizza.getPrice() > 0.0) pizza.setPrice(updatePizza.getPrice());
		if(updatePizza.getDescription() != null) pizza.setDescription(updatePizza.getDescription());
		if(updatePizza.getUrl() != null) pizza.setUrl(updatePizza.getUrl());
//		pizza.setName(updatePizza.getName());
//		pizza.setPrice(updatePizza.getPrice());
//		pizza.setDescription(updatePizza.getDescription());
//		pizza.setUrl(updatePizza.getUrl());
		pizzaService.save(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(
			@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		if(pizza == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		pizzaService.delete(pizza);
		
		return new ResponseEntity<>(" message : deleted correctly", HttpStatus.OK); 
	}
		
	
	
}
