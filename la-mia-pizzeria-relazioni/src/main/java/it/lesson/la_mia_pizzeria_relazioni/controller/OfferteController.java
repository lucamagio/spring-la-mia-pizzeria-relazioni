package it.lesson.la_mia_pizzeria_relazioni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import it.lesson.la_mia_pizzeria_relazioni.model.Offerte;
import it.lesson.la_mia_pizzeria_relazioni.repository.OfferteRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/offerte")
public class OfferteController {

    @Autowired
    private OfferteRepository offerteRepository;

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("offerta") Offerte formOfferta, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("editMode", false);
            model.addAttribute("offerta", formOfferta);
            return "offerteCreate";
        }

        offerteRepository.save(formOfferta);

        return "redirect:/pizzeria/" + formOfferta.getPizza().getId();
    }

    @GetMapping("/create/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Offerte offerta = offerteRepository.findById(id).get();
        model.addAttribute("offerta", offerta);
        model.addAttribute("editMode", true);
        return "offerteCreate";
    }

    @PostMapping("/create/{id}")
    public String doEdit(@Valid @ModelAttribute ("offerta") Offerte offerta, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("editMode", true);
            model.addAttribute("offerta", offerta);
            return "offerteCreate";
        }

        offerteRepository.save(offerta);
        return "redirect:/pizzeria/" + offerta.getPizza().getId();
    }
    
    
   
}
