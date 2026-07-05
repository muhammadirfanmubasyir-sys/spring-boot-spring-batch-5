package com.codesnippet.SpringBatchDemo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testDefaultConstructor() {
        Person person = new Person();
        assertNull(person.getId());
        assertNull(person.getUserId());
        assertNull(person.getFirstName());
        assertNull(person.getLastName());
        assertNull(person.getGender());
        assertNull(person.getEmail());
        assertNull(person.getPhone());
        assertNull(person.getDateOfBirth());
        assertNull(person.getJobTitle());
    }

    @Test
    void testSetAndGetId() {
        Person person = new Person();
        person.setId(1L);
        assertEquals(1L, person.getId());
    }

    @Test
    void testSetAndGetUserId() {
        Person person = new Person();
        person.setUserId("U001");
        assertEquals("U001", person.getUserId());
    }

    @Test
    void testSetAndGetFirstName() {
        Person person = new Person();
        person.setFirstName("John");
        assertEquals("John", person.getFirstName());
    }

    @Test
    void testSetAndGetLastName() {
        Person person = new Person();
        person.setLastName("Doe");
        assertEquals("Doe", person.getLastName());
    }

    @Test
    void testSetAndGetGender() {
        Person person = new Person();
        person.setGender("Male");
        assertEquals("Male", person.getGender());
    }

    @Test
    void testSetAndGetEmail() {
        Person person = new Person();
        person.setEmail("john@example.com");
        assertEquals("john@example.com", person.getEmail());
    }

    @Test
    void testSetAndGetPhone() {
        Person person = new Person();
        person.setPhone("1234567890");
        assertEquals("1234567890", person.getPhone());
    }

    @Test
    void testSetAndGetDateOfBirth() {
        Person person = new Person();
        person.setDateOfBirth("1990-01-01");
        assertEquals("1990-01-01", person.getDateOfBirth());
    }

    @Test
    void testSetAndGetJobTitle() {
        Person person = new Person();
        person.setJobTitle("Engineer");
        assertEquals("Engineer", person.getJobTitle());
    }

    @Test
    void testSetAllFields() {
        Person person = new Person();
        person.setId(10L);
        person.setUserId("U010");
        person.setFirstName("Jane");
        person.setLastName("Smith");
        person.setGender("Female");
        person.setEmail("jane@example.com");
        person.setPhone("0987654321");
        person.setDateOfBirth("1985-05-15");
        person.setJobTitle("Developer");

        assertEquals(10L, person.getId());
        assertEquals("U010", person.getUserId());
        assertEquals("Jane", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("Female", person.getGender());
        assertEquals("jane@example.com", person.getEmail());
        assertEquals("0987654321", person.getPhone());
        assertEquals("1985-05-15", person.getDateOfBirth());
        assertEquals("Developer", person.getJobTitle());
    }

    @Test
    void testSetNullValues() {
        Person person = new Person();
        person.setFirstName("John");
        person.setFirstName(null);
        assertNull(person.getFirstName());
    }
}
