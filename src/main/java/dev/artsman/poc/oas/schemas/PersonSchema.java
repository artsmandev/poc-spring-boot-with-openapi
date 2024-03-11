package dev.artsman.poc.oas.schemas;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;

@Schema(
	name = "Person",
	example = """
	{
	\t"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
	\t"firstName": "Frank",
	\t"lastName": "Castle",
	\t"birthday": "1979-11-15"
	}
	"""
)
public interface PersonSchema {
	@Schema(requiredMode = REQUIRED)
	UUID getId();

	@Schema(requiredMode = REQUIRED)
	String getFirstName();

	@Schema(requiredMode = REQUIRED)
	String getLastName();

	@Schema(requiredMode = REQUIRED)
	LocalDate getBirthday();
}
