package ee.mihkel.veebipood.service;

import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
//@AllArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // A robust, common regex pattern
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?^_{|}~-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?^_{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$";
    private final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    private final EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();// true

    public Person savePerson(Person person){
        if (person.getId() != null){
            throw new RuntimeException("Cannot signup with ID");
        }
        if (person.getEmail() == null){
            throw new RuntimeException("Cannot signup without email");
        }
        // .isEmpty() ---> "" TRUE
        // .isBlank() ---> "    " TRUE
        if (person.getPassword() == null || person.getPassword().isBlank()){
            throw new RuntimeException("Cannot signup without password");
        }
        if (!PATTERN.matcher(person.getEmail()).matches()) {
            throw new RuntimeException("Invalid email");
        }
        if (!validator.isValid(person.getPersonCode())){
            throw new RuntimeException("Invalid personal code");
        }

        return personRepository.save(person);
    }
}
