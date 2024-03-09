package dev.artsman.poc.repositories;

import dev.artsman.poc.entities.PersonEntity;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {
	private static final HashMap<UUID, PersonEntity> database;
	static {
		database = new HashMap<>();
	}

	public PersonEntity save(PersonEntity personEntity) {
		var uuid = UUID.randomUUID();
		personEntity.setId(uuid);
		database.put(uuid, personEntity);
		return personEntity;
	}

	public PersonEntity update(PersonEntity personEntity) {
		database.put(personEntity.getId(), personEntity);
		return personEntity;
	}

	public List<PersonEntity> findAll() {
		return database.values().stream().toList();
	}

	public PersonEntity findById(UUID id) {
		return database.get(id);
	}

	public void delete(UUID id) {
		database.remove(id);
	}
}
