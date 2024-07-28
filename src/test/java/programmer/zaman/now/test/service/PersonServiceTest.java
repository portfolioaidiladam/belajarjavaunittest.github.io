package programmer.zaman.now.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import programmer.zaman.now.test.data.Person;
import programmer.zaman.now.test.repository.PersonRepository;
// belajar mocking test
@Extensions({
    @ExtendWith(MockitoExtension.class)
})
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  private PersonService personService;

  @BeforeEach
  void setUp() {
    personService = new PersonService(personRepository);
  }

  @Test
  void testGetPersonNotFound() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      personService.get("not found");
    });
  }

  @Test
  void testGetPersonSuccess() {
    // menambah behavior ke mock object
    Mockito.when(personRepository.selectById("aidil"))
        .thenReturn(new Person("aidil", "Aidil"));

    var person = personService.get("aidil");

    Assertions.assertNotNull(person);
    Assertions.assertEquals("aidil", person.getId());
    Assertions.assertEquals("Aidil", person.getName());
  }
  // belajar verifikasi di mocking
  // impelementasi unit testnya
  @Test
  void testRegisterSuccess() {
    var person = personService.register("Aidil");
    Assertions.assertNotNull(person);
    Assertions.assertEquals("Aidil", person.getName());
    Assertions.assertNotNull(person.getId());

    // lakukan verifikasi bisa menjaga bahwa biar gak salah manggil si mocking objectnya
    // agar user yang sudah register datanya masuk di database setelah production
    Mockito.verify(personRepository, Mockito.times(1))
        .insert(new Person(person.getId(), "Aidil"));
  }
}
