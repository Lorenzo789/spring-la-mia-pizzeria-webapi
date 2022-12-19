package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.IngredientService;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "home";
	}
	
	@GetMapping("/user/pizza/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		Pizza selectedPizza = optPizza.get();
		
		model.addAttribute("selectedPizza", selectedPizza);
		
		return "show";
	}
	
	@GetMapping("/admin/pizza/create")
	public String create(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "create";
	}
	
	@PostMapping("/admin/pizza/create")
	public String store(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/pizza/create";
		}
		
		List<Ingredient> ingredients = pizza.getIngredients();
		
		for(Ingredient ingredient : ingredients) {
			
			ingredient.getPizze().add(pizza);
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/admin/pizza/update/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "edit";
	}
	
	@PostMapping("/admin/pizza/update")
	public String update(Pizza pizza) {
		
		pizzaService.save(pizza);		
		
		return "redirect:/";
	}
	
	@GetMapping("/admin/pizza/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/user/search/pizza")
	public String searchPizza(@RequestParam(name="query", required=false)String query, Model model) {
		
		List<Pizza> pizze = null;
		
		if (query == null) {
			
			pizze = pizzaService.findAll();
			
		} else {
			
			pizze = pizzaService.searchByName(query);
			
		}
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("query", query);
		
		return "home";
	}
}
