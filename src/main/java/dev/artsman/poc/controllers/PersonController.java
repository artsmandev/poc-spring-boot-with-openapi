package dev.artsman.poc.controllers;

import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.services.PersonService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/people")
@RestController
@RequiredArgsConstructor
public class PersonController {
	private final PersonService service;

	@GetMapping
	public ResponseEntity<List<PersonDto>> findAll() {
		List<PersonDto> personDtos = service.findAll();
		if (personDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(personDtos);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PersonDto> save(@RequestBody PersonDto personDto) {
		personDto = service.save(personDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(personDto.getId())
		 	.toUri();
		return ResponseEntity.created(uri).body(personDto);
	}
}
