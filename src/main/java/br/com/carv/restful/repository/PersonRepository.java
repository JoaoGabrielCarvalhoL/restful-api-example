package br.com.carv.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.restful.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
