package dev.artsman.poc.mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import dev.artsman.poc.dtos.PersonCreateDto;
import dev.artsman.poc.dtos.PersonDto;
import dev.artsman.poc.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface PersonMapper {
	PersonEntity toEntity(PersonCreateDto personCreateDto);

	PersonDto toDto(PersonEntity personEntity);
}
