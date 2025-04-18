package it.lesson.la_mia_pizzeria_relazioni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lesson.la_mia_pizzeria_relazioni.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

    public List<Pizza> findByNomeContainingIgnoreCase(String nome);
}
