package org.java.spring.pojo;



import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 32)
	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be null")
	@Length(max = 32, message = "name must be max 32 chars")
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(nullable = false)
	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be positive")
	private double price;
	
	private String url;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	@OneToMany(mappedBy="pizza", cascade = CascadeType.REMOVE)
	private List<SpecialOffer> specialOffer;
	
	
	public Pizza() {}
	
	public Pizza(String name,double price,String description,String url) {
		setName(name);
		setPrice(price);
		setDescription(description);
		setUrl(url);
	}
	
	
	public Pizza(String name,double price,String description,String url, Ingredient...ingredients) {
		setName(name);
		setPrice(price);
		setDescription(description);
		setUrl(url);
		setIngredients(ingredients);
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<SpecialOffer> getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(List<SpecialOffer> specialOffer) {
		this.specialOffer = specialOffer;
	}

	@JsonProperty("ingredients")
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@JsonIgnore
	public void setIngredients(Ingredient... ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	
	
	public void clearIngredients(Ingredient... ingredient) {
		for(Ingredient j : ingredient) {
			getIngredients().remove(j);
		}	
	}
	
	public void clearIngredients() {
		getIngredients().clear();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+getId()+"]"+" - "+getName()+" - "+getPrice()+"$ - "
				+getIngredients();
	}
}
