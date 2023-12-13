package org.java.spring.restcontroller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PizzaRestController {
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	SpecialOfferService spOffService;
	
	@Autowired
	IngredientService ingService;
	
	@GetMapping("index")
	public ResponseEntity<List<Pizza>> index(){
		
		List <Pizza> pizzas = pizzaService.findAll();
		
		if(pizzas.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("index/{name}")
	public ResponseEntity<List<Pizza>> searchByTitle(@PathVariable String name){
		
		List <Pizza> pizzas = pizzaService.findByName(name);
		
		if(pizzas.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity <Pizza> create(@RequestBody Pizza pizza){
		pizzaService.save(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Pizza> update(@RequestBody Pizza updatePizza,
										@PathVariable int id){
		Pizza pizza = pizzaService.findById(id);
//		if(!updatePizza.getName().isEmpty() || updatePizza.getName() != null) pizza.setName(updatePizza.getName());
//		if(updatePizza.getPrice() != 0.0) pizza.setPrice(updatePizza.getPrice());
//		if(!updatePizza.getDescription().isEmpty() || updatePizza.getDescription() != null) pizza.setDescription(updatePizza.getDescription());
//		if(!updatePizza.getUrl().isEmpty() || updatePizza.getUrl() != null) pizza.setUrl(updatePizza.getUrl());
		pizza.setName(updatePizza.getName());
		pizza.setPrice(updatePizza.getPrice());
		pizza.setDescription(updatePizza.getDescription());
		pizza.setUrl(updatePizza.getUrl());
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
