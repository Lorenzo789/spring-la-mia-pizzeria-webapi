package org.generation.italy.demo;

import java.util.Arrays;
import java.util.List;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.IngredientService;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaWebapi1Application implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private IngredientService ingredientService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaWebapi1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingredient ing1 = new Ingredient("olio");
		Ingredient ing2 = new Ingredient("farina");
		Ingredient ing3 = new Ingredient("sale");
		
		ingredientService.save(ing1);
		ingredientService.save(ing2);
		ingredientService.save(ing3);
		
		List<Ingredient> allPizzaIngredient = Arrays.asList(new Ingredient[] {
				ing1,
				ing2,
				ing3
		});
		
		Pizza p1 = new Pizza("Margherita", "buona", 9, allPizzaIngredient);
		Pizza p2 = new Pizza("Diavola", "super", 10, allPizzaIngredient);
		Pizza p3 = new Pizza("Margherita di bufala", "da paura", 12, allPizzaIngredient);
		Pizza p4 = new Pizza("Salsiccia e funghi", "buonissima", 10, allPizzaIngredient);
		Pizza p5 = new Pizza("Crostino", "ce sta", 9);
		Pizza p6 = new Pizza("Margheritona", null, 20, allPizzaIngredient);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		pizzaService.save(p6);
		
		// List<Pizza> pizze = pizzaService.findAll();
		
		// List<Ingredient> ingredients = ingredientService.findAll();
	}
}
