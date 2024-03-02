package dev.artsman.poc.oas.resources;

import dev.artsman.poc.dtos.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
							implementation = PersonDto.class
						)
					)
				)
			)
		}
	)
	ResponseEntity<List<PersonDto>> findAll();
}
