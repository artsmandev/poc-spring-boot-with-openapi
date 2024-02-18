package dev.artsman.poc.services;

import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.entities.PersonEntity;
import dev.artsman.poc.repositories.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
	private final PersonRepository repository;

	public PersonDto save(PersonDto personDto) {
		PersonEntity personEntity = convertToEntity(personDto);
		personEntity = repository.save(personEntity);
		personDto = convertToDto(personEntity);
		return personDto;
	}

	public List<PersonDto> findAll() {
		List<PersonEntity> personEntities = repository.findAll();
		if (personEntities.isEmpty()) {
			return List.of();
		}
		return personEntities.stream()
			 .map(this::convertToDto)
			 .collect(Collectors.toList());
	}

	private PersonDto convertToDto(PersonEntity personEntity) {
		return PersonDto.builder()
			.id(personEntity.getId())
			.firstName(personEntity.getFirstName())
			.lastName(personEntity.getLastName())
			.birthday(personEntity.getBirthday())
			.build();
	}

	private PersonEntity convertToEntity(PersonDto personDto) {
		return PersonEntity.builder()
			.id(personDto.getId())
			.firstName(personDto.getFirstName())
			.lastName(personDto.getLastName())
			.birthday(personDto.getBirthday())
			.build();
	}
}
