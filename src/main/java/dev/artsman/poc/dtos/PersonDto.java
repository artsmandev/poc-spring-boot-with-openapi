package dev.artsman.poc.dtos;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDto {
	private UUID id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
}
