package br.com.carv.restful.controller.impl;

import java.util.List;

import br.com.carv.restful.controller.PersonController;
import br.com.carv.restful.model.dto.request.PersonRequest;
import br.com.carv.restful.model.dto.request.PersonUpdateRequest;
import br.com.carv.restful.model.dto.response.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.carv.restful.model.Person;
import br.com.carv.restful.service.PersonService;

@RequestMapping("/person")
@RestController
public class PersonControllerImpl implements PersonController {
	
	private final PersonService personService;

	public PersonControllerImpl(PersonService personService) {
		this.personService = personService;
	}

	@Override
	public PersonResponse save(PersonRequest person) {
		return personService.save(person);
	}

	@Override
	public PersonResponse update(PersonUpdateRequest person) {
		return personService.update(person);
	}

	@Override
	public PersonResponse findById(Long id) {
		return personService.findByKey(id);
	}

	@Override
	public List<PersonResponse> findAll() {
		return personService.findAll();
	}

	@Override
	public Page<PersonResponse> findAllPaginated(Pageable pageable) {
		return personService.findAllPaginated(pageable);
	}

	@Override
	public void delete(Long id) {
		personService.delete(id);
	}

}
