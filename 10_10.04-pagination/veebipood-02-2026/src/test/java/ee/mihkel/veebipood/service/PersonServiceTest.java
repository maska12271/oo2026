package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock // jäjendame andmebaasi
    private PersonRepository personRepository;

    @InjectMocks // teeb ikka @Autowired (injectib dependency), aga võtab kõik mockitud asjad kaasa
    private PersonService personService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void throwsExceptionIfIdIsGiven() {
        Person person = new Person();
        person.setId(1L);
        String message = assertThrows(RuntimeException.class, ()-> personService.savePerson(person)).getMessage();
        assertEquals("Cannot signup with ID", message);
    }

    @Test
    void throwsExceptionIfEmailIsMissing() {
        Person person = new Person();
        String message = assertThrows(RuntimeException.class, ()-> personService.savePerson(person)).getMessage();
        assertEquals("Cannot signup without email", message);
    }
// nimetused: funktsiooni nimi
// given_when_then: givenPasswordIsMissing_whenSigningUp_thenCannotSignupExceptionIsThrown
    @Test
    void throwsExceptionIfPasswordIsMissing() {
        Person person = new Person();
        person.setEmail("bla");
        String message = assertThrows(RuntimeException.class, ()-> personService.savePerson(person)).getMessage();
        assertEquals("Cannot signup without password", message);
    }

    @Test
    void throwsExceptionIfEmailNotValid() {
        Person person = new Person();
        person.setPassword("password");
        person.setEmail("email");
        String message = assertThrows(RuntimeException.class, ()-> personService.savePerson(person)).getMessage();
        assertEquals("Invalid email", message);
    }

    @Test
    void throwsExceptionIfPersonalCodeNotValid() {
        Person person = new Person();
        person.setPassword("password");
        person.setEmail("123@123.com");
        person.setPersonCode("12345");
        String message = assertThrows(RuntimeException.class, ()-> personService.savePerson(person)).getMessage();
        assertEquals("Invalid personal code", message);
    }

    @Test
    void returnsPersonIfSavedToDatabase() {
        Person person = new Person();
        person.setPassword("password");
        person.setEmail("123@123.com");
        person.setPersonCode("38109152733");

        Person returnedPerson = new Person();
        when(personRepository.save(any())).thenReturn(returnedPerson);

        Person dbPerson = personService.savePerson(person);
        assertNotNull(dbPerson);
    }
}