package org.java.spring.DTO;

import java.util.List;

public class PizzaRestDTO {
	
	private String name;
	private String description;
	private String url;
	private double price;
	private List<Integer> ingredientId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Integer> getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(List<Integer> ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	

}
