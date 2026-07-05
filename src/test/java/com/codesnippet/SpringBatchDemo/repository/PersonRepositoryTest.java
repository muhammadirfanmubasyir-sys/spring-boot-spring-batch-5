package com.codesnippet.SpringBatchDemo.repository;

import com.codesnippet.SpringBatchDemo.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSavePerson() {
        Person person = createPerson("U001", "John", "Doe");
        Person saved = personRepository.save(person);

        assertNotNull(saved.getId());
        assertEquals("U001", saved.getUserId());
        assertEquals("John", saved.getFirstName());
        assertEquals("Doe", saved.getLastName());
    }

    @Test
    void testFindAll() {
        personRepository.save(createPerson("U001", "John", "Doe"));
        personRepository.save(createPerson("U002", "Jane", "Smith"));

        List<Person> persons = personRepository.findAll();

        assertEquals(2, persons.size());
    }

    @Test
    void testFindById() {
        Person saved = personRepository.save(createPerson("U001", "John", "Doe"));

        Optional<Person> found = personRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Person> found = personRepository.findById(999L);

        assertFalse(found.isPresent());
    }

    @Test
    void testDeleteById() {
        Person saved = personRepository.save(createPerson("U001", "John", "Doe"));
        Long id = saved.getId();

        personRepository.deleteById(id);

        assertFalse(personRepository.findById(id).isPresent());
    }

    @Test
    void testCount() {
        assertEquals(0, personRepository.count());

        personRepository.save(createPerson("U001", "John", "Doe"));
        personRepository.save(createPerson("U002", "Jane", "Smith"));

        assertEquals(2, personRepository.count());
    }

    @Test
    void testSaveMultiplePersons() {
        Person p1 = createPerson("U001", "John", "Doe");
        Person p2 = createPerson("U002", "Jane", "Smith");
        Person p3 = createPerson("U003", "Bob", "Johnson");

        personRepository.saveAll(List.of(p1, p2, p3));

        assertEquals(3, personRepository.count());
    }

    @Test
    void testDeleteAll() {
        personRepository.save(createPerson("U001", "John", "Doe"));
        personRepository.save(createPerson("U002", "Jane", "Smith"));

        personRepository.deleteAll();

        assertEquals(0, personRepository.count());
    }

    @Test
    void testExistsById() {
        Person saved = personRepository.save(createPerson("U001", "John", "Doe"));

        assertTrue(personRepository.existsById(saved.getId()));
        assertFalse(personRepository.existsById(999L));
    }

    private Person createPerson(String userId, String firstName, String lastName) {
        Person person = new Person();
        person.setUserId(userId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender("Male");
        person.setEmail(userId.toLowerCase() + "@example.com");
        person.setPhone("1234567890");
        person.setDateOfBirth("1990-01-01");
        person.setJobTitle("Developer");
        return person;
    }
}
