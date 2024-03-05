package dev.artsman.poc.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PersonCreateDto {
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotNull
	private LocalDate birthday;
}
