package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.AuthToken;
import ee.mihkel.veebipood.dto.PersonLoginRecord;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-02-2026.onrender.com"})
@RestController
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    private PersonRepository personRepository;

    @GetMapping("persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PostMapping("login")
    public AuthToken login(@RequestBody PersonLoginRecord loginCredentials){
        if (loginCredentials.email() == null){
            throw new RuntimeException("Cannot login without email");
        }
        Person person = personRepository.findByEmail(loginCredentials.email());
        if (person == null){
            throw new RuntimeException("Email invalid");
        }
        if (!person.getPassword().equals(loginCredentials.password())){
            throw new RuntimeException("Password invalid");
        }
        AuthToken authToken = new AuthToken();
        authToken.setToken("blabla_token123");
        authToken.setExpiration(System.currentTimeMillis() + 9999);
        return authToken;
    }
}
