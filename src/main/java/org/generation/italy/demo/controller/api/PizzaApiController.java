package org.generation.italy.demo.controller.api;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/pizze")
@CrossOrigin("*")
public class PizzaApiController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/all")
	public List<Pizza> getAllPizze() {
		
		List<Pizza> pizze = pizzaService.findAll();
		
		return pizze;
	}
	
}
