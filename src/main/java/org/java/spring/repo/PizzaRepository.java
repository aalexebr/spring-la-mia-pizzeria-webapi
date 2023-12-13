package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
	
	List <Pizza> findByNameContaining(String x);

}
