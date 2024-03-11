package dev.artsman.poc.controllers;

import dev.artsman.poc.dtos.PersonCreateDto;
import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.dtos.PersonUpdateDto;
import dev.artsman.poc.mappers.PersonMapper;
import dev.artsman.poc.oas.resources.PeopleResource;
import dev.artsman.poc.services.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/people")
@RestController
@RequiredArgsConstructor
public class PeopleController implements PeopleResource {
	private final PersonService service;
	private final PersonMapper mapper;

	@GetMapping
	public ResponseEntity<List<PersonDto>> findAll() {
		var responseDtos = service.findAll();
		if (responseDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(responseDtos);
	}

	@PostMapping
	public ResponseEntity<PersonDto> save(@Valid @RequestBody PersonCreateDto requestDto) {
		var entityToCreate = mapper.toEntity(requestDto);
		var entityCreated = service.save(entityToCreate);

		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(entityCreated.getId())
		 	.toUri();

		var responseDto = mapper.toDto(entityCreated);
		return ResponseEntity.created(uri).body(responseDto);
	}

	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<PersonDto> update(@PathVariable UUID id, @RequestBody PersonUpdateDto requestDto) {
		if (requestDto.hasNoPropertiesToUpdate()) {
			return ResponseEntity.badRequest().build();
		}

		var entityToUpdate = service.findById(id);
		if (Objects.isNull(entityToUpdate)) {
			ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(requestDto, entityToUpdate, ignoreNullProperties(requestDto));

		var entityUpdated = service.update(entityToUpdate);
		return ResponseEntity.ok(mapper.toDto(entityUpdated));
	}

	@DeleteMapping
	@Override
	public ResponseEntity<Void> delete(UUID id) {
		var personEntity = service.findById(id);
		if (Objects.isNull(personEntity)) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	public static String[] ignoreNullProperties(Object source) {
		var wrappedSource = new BeanWrapperImpl(source);
		return Stream.of(wrappedSource.getPropertyDescriptors())
			.map(FeatureDescriptor::getName)
			.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
			.toArray(String[]::new);
	}
}
