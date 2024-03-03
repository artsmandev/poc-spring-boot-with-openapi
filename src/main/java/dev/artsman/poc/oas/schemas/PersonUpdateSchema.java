package dev.artsman.poc.oas.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(
	name = "PersonUpdate",
	minProperties = 1
)
public interface PersonUpdateSchema {
	String getFirstName();
	String getLastName();
	LocalDate getBirthday();
}
