package dev.artsman.poc.entities;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonEntity {
	@EqualsAndHashCode.Include
	private UUID id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
}
