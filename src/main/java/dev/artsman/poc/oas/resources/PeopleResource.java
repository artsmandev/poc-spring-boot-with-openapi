package dev.artsman.poc.oas.resources;

import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.oas.schemas.PersonCreateSchema;
import dev.artsman.poc.oas.schemas.PersonSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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
}
