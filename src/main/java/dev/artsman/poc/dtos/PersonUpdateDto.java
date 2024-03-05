package dev.artsman.poc.dtos;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PersonUpdateDto {
	private String firstName;
	private String lastName;
	private LocalDate birthday;

	public boolean hasNoPropertiesToUpdate() {
		return isBlank(firstName) && isBlank(lastName) && isNull(birthday);
	}
}
