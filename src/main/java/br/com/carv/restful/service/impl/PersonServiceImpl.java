package br.com.carv.restful.service.impl;

import br.com.carv.restful.controller.impl.PersonControllerImpl;
import br.com.carv.restful.exception.ResourceNotFoundException;
import br.com.carv.restful.model.Person;
import br.com.carv.restful.model.dto.request.PersonRequest;
import br.com.carv.restful.model.dto.request.PersonUpdateRequest;
import br.com.carv.restful.model.dto.response.PersonResponse;
import br.com.carv.restful.repository.PersonRepository;
import br.com.carv.restful.service.PersonService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository personRepository;
	private final ModelMapper mapper;
	private final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

	private final PagedResourcesAssembler<PersonResponse> resourcesAssembler;
	
	public PersonServiceImpl(PersonRepository personRepository, ModelMapper mapper, PagedResourcesAssembler<PersonResponse> resourcesAssembler) {
		this.personRepository = personRepository;
		this.mapper = mapper;
		this.resourcesAssembler = resourcesAssembler;
	}
	@Override
	public PersonResponse findByKey(Long id) {
		logger.info("Getting Person by id: " + id);
		PersonResponse personResponse = personRepository.findById(id).map(person -> mapper.map(person, PersonResponse.class)).
				orElseThrow(() -> new ResourceNotFoundException("person.not.found"));
		personResponse.add(linkTo(methodOn(PersonControllerImpl.class).findById(id)).withSelfRel());
		return personResponse;
	}

	@Override
	public PersonResponse save(PersonRequest person) {
		logger.info("Saving Person into database");
		Person entity = mapper.map(person, Person.class);
		personRepository.save(entity);
		PersonResponse personResponse = mapper.map(entity, PersonResponse.class);
		personResponse.add(linkTo(methodOn(PersonControllerImpl.class).findById(personResponse.getId())).withSelfRel());
		return personResponse;
	}
	@Override
	public PersonResponse update(PersonUpdateRequest person) {
		logger.info("Updating Person into database");
		Person entity = mapper.map(person, Person.class);
		personRepository.saveAndFlush(entity);
		PersonResponse personResponse = mapper.map(entity, PersonResponse.class);
		personResponse.add(linkTo(methodOn(PersonControllerImpl.class).findById(personResponse.getId())).withSelfRel());
		return personResponse;
	}

	@Override
	public List<PersonResponse> findAll() {
		logger.info("Getting all persons into database");
		List<PersonResponse> persons = personRepository.findAll()
				.stream().map(person -> mapper.map(person, PersonResponse.class))
				.collect(Collectors.toList());
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonControllerImpl.class).findById(p.getId())).withSelfRel()));
		return persons;
	}

	@Override
	public Page<PersonResponse> findAllPaginated(Pageable pageable) {
		logger.info("Getting list of person paginated");
		List<PersonResponse> personResponses = personRepository.findAll(pageable)
				.stream().map(person -> mapper.map(person, PersonResponse.class))
				.collect(Collectors.toList());
		personResponses.stream().forEach(p -> p.add(linkTo(methodOn(PersonControllerImpl.class).findById(p.getId())).withSelfRel()));
		return new PageImpl<PersonResponse>(personResponses, pageable, personResponses.size());
	}

	@Override
	public PagedModel<EntityModel<PersonResponse>> anotherFindAllPaginated(Pageable pageable) {
		logger.info("Getting list of person paginated PagedModel");
		List<PersonResponse> personResponses = personRepository.findAll(pageable)
				.stream().map(person -> mapper.map(person, PersonResponse.class))
				.collect(Collectors.toList());
		personResponses.stream().forEach(p -> p.add(linkTo(methodOn(PersonControllerImpl.class).findById(p.getId())).withSelfRel()));

		Link link = linkTo(methodOn(PersonControllerImpl.class).anotherFindAllPaginated(pageable)).withSelfRel();
		return resourcesAssembler.toModel(new PageImpl<PersonResponse>(personResponses, pageable, personResponses.size()), link);
	}

	@Transactional
	@Override
	public PersonResponse disablePerson(Long id) {
		logger.info("Disable Person by id: " + id);
		personRepository.disablePerson(id);
		PersonResponse personResponse = personRepository.findById(id).map(person -> mapper.map(person, PersonResponse.class)).
				orElseThrow(() -> new ResourceNotFoundException("person.not.found"));
		personResponse.add(linkTo(methodOn(PersonControllerImpl.class).findById(id)).withSelfRel());
		return personResponse;
	}

	@Override
	public void delete(Long key) {
		logger.info("Deleting person by id: " + key);
		personRepository.delete(findByIdPerson(key));
	}

	private Person findByIdPerson(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
	}

}
