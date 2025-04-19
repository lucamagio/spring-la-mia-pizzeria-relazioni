package it.lesson.la_mia_pizzeria_relazioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lesson.la_mia_pizzeria_relazioni.model.Ingredienti;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Integer>{

}
