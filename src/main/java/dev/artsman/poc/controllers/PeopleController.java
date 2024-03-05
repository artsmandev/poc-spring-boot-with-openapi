package dev.artsman.poc.controllers;

import dev.artsman.poc.dtos.PersonCreateDto;
import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.mapper.PersonMapper;
import dev.artsman.poc.oas.resources.PeopleResource;
import dev.artsman.poc.services.PersonService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<PersonDto> update(@PathVariable UUID id, @Valid @RequestBody PersonCreateDto personDto) {
		return null;
	}
}
