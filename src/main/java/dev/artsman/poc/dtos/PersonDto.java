package dev.artsman.poc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(name = "Person")
public class PersonDto {
	private UUID id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
}
