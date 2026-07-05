package com.codesnippet.SpringBatchDemo.config;

import com.codesnippet.SpringBatchDemo.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonProcessorTest {

    private PersonProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new PersonProcessor();
    }

    @Test
    void testProcessUppercasesNames() throws Exception {
        Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");

        Person result = processor.process(person);

        assertEquals("JOHN", result.getFirstName());
        assertEquals("DOE", result.getLastName());
    }

    @Test
    void testProcessReturnsSamePersonObject() throws Exception {
        Person person = new Person();
        person.setFirstName("alice");
        person.setLastName("smith");

        Person result = processor.process(person);

        assertSame(person, result);
    }

    @Test
    void testProcessWithMixedCase() throws Exception {
        Person person = new Person();
        person.setFirstName("jOhN");
        person.setLastName("mCcLoVe");

        Person result = processor.process(person);

        assertEquals("JOHN", result.getFirstName());
        assertEquals("MCCLOVE", result.getLastName());
    }

    @Test
    void testProcessWithAlreadyUppercase() throws Exception {
        Person person = new Person();
        person.setFirstName("ALICE");
        person.setLastName("SMITH");

        Person result = processor.process(person);

        assertEquals("ALICE", result.getFirstName());
        assertEquals("SMITH", result.getLastName());
    }

    @Test
    void testProcessPreservesOtherFields() throws Exception {
        Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");
        person.setEmail("john@example.com");
        person.setPhone("1234567890");

        Person result = processor.process(person);

        assertEquals("john@example.com", result.getEmail());
        assertEquals("1234567890", result.getPhone());
    }

    @Test
    void testProcessWithEmptyNames() throws Exception {
        Person person = new Person();
        person.setFirstName("");
        person.setLastName("");

        Person result = processor.process(person);

        assertEquals("", result.getFirstName());
        assertEquals("", result.getLastName());
    }
}
