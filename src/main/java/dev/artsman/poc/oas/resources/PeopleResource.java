package dev.artsman.poc.oas.resources;

import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.oas.schemas.PersonCreateSchema;
import dev.artsman.poc.oas.schemas.PersonSchema;
import dev.artsman.poc.oas.schemas.PersonUpdateSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "People", description = "People's resources")
public interface PeopleResource {
	@Operation(
		summary = "Find all.",
		description = "Find all people.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							implementation = PersonSchema.class
						)
					)
				)
			)
		}
	)
	ResponseEntity<List<PersonDto>> findAll();

	@Operation(
		summary = "Create.",
		description = "Create a person.",
		requestBody = @RequestBody(
			content = @Content(
				schema = @Schema(
					implementation = PersonCreateSchema.class
				)
			)
		),
		responses = {
			@ApiResponse(
				responseCode = "201",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(
						implementation = PersonSchema.class
					)
				)
			)
		}
	)
	ResponseEntity<PersonDto> save(PersonDto personDto);

	@Operation(
		summary = "Partially update.",
		description = """
		Partially update a Person:
		
		• Individually: just *firstName*.
		
		• Combined two: *firstName* and *lastName*.
		
		• All properties: *firstName*, *lastName* and *birthday*.
		""",
		parameters = @Parameter(
			name = "id",
			description = "Person's id.",
			example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
		),
		requestBody = @RequestBody(
			content = @Content(
				schema = @Schema(
					implementation = PersonUpdateSchema.class
				),
				examples = {
					@ExampleObject(
						name = "Individually",
						description = "Payload to update only firstName.",
						value = """
							{
								"firstName": "Frank"
							}
						"""
					),
					@ExampleObject(
						name = "Combined two",
						description = "Payload to update firstName and lastName.",
						value = """
							{
								"firstName": "Frank",
								"lastName": "Castle"
							}
						"""
					),
					@ExampleObject(
						name = "All properties",
						description = "Payload to update firstName, lastName and birthday.",
						value = """
							{
								"firstName": "Frank",
								"lastName": "Castle",
								"birthday": "1979-11-15"
							}
						"""
					)
				}
			)
		),
		responses = {
			@ApiResponse(
				responseCode = "200",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(
						implementation = PersonSchema.class
					)
				)
			)
		}
	)
	ResponseEntity<PersonDto> update(UUID id, PersonDto personDto);
}
