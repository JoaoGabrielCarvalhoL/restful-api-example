package br.com.carv.restful.service;

import java.util.List;

import br.com.carv.restful.model.dto.request.PersonRequest;
import br.com.carv.restful.model.dto.request.PersonUpdateRequest;
import br.com.carv.restful.model.dto.response.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.carv.restful.model.Person;

public interface PersonService {
	
	PersonResponse findByKey(final Long key);
	
	PersonResponse save(final PersonRequest person);

	PersonResponse update(final PersonUpdateRequest person);
	
	List<PersonResponse> findAll();
	
	Page<PersonResponse> findAllPaginated(final Pageable pageable);
	
	void delete(final Long key);

}
