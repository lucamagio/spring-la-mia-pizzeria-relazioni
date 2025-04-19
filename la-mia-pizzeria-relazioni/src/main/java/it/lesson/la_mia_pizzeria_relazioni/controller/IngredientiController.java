package it.lesson.la_mia_pizzeria_relazioni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import it.lesson.la_mia_pizzeria_relazioni.model.Ingredienti;
import it.lesson.la_mia_pizzeria_relazioni.model.Pizza;
import it.lesson.la_mia_pizzeria_relazioni.repository.IngredientiRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

    @Autowired
    private IngredientiRepository ingredientiRepository;

    @GetMapping()
    public String ingredienti(Model model) {
        model.addAttribute("list", ingredientiRepository.findAll());
        model.addAttribute("ingredientiObj", new Ingredienti());

        return "ingredienti";
    }

    @PostMapping("/create")
    public String creazione(@Valid @ModelAttribute("ingredientiObj") Ingredienti ingrediente, BindingResult bindingResult) {
        if (! bindingResult.hasErrors()) {
            ingredientiRepository.save(ingrediente);
        }
        
        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Ingredienti ingrediente = ingredientiRepository.findById(id).get();

        for(Pizza pizza : ingrediente.getPizza()){
            pizza.getIngredienti().remove(ingrediente);
        }

        ingredientiRepository.deleteById(id);
        
        return "redirect:/ingredienti";
    }
    
    
    

}
