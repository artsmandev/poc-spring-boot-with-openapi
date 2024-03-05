package dev.artsman.poc.services;

import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.entities.PersonEntity;
import dev.artsman.poc.mapper.PersonMapper;
import dev.artsman.poc.repositories.PersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
	private final PersonRepository repository;
	private final PersonMapper mapper;

	public PersonEntity save(PersonEntity entity) {
		return repository.save(entity);
	}

	public PersonEntity update(PersonEntity entity) {
		return repository.update(entity);
	}

	public List<PersonDto> findAll() {
		List<PersonEntity> personEntities = repository.findAll();
		if (personEntities.isEmpty()) {
			return List.of();
		}
		return personEntities.stream()
			.map(mapper::toDto)
			.toList();
	}

	public PersonEntity findById(UUID id) {
		return repository.findById(id);
	}
}
