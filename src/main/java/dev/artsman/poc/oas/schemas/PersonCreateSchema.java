package dev.artsman.poc.oas.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(
	name = "PersonCreate",
	example = """
	{
	\t"firstName": "Frank",
	\t"lastName": "Castle",
	\t"birthday": "1979-11-15"
	}
	"""
)
public interface PersonCreateSchema {
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	String getFirstName();

	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	String getLastName();

	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDate getBirthday();
}
