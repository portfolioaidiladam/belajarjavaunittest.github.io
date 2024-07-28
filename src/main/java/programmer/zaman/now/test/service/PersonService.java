package programmer.zaman.now.test.service;

import programmer.zaman.now.test.data.Person;
import programmer.zaman.now.test.repository.PersonRepository;

import java.util.UUID;
// belajar mocking di test

public class PersonService {

  private PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  // di logic untuk nge get data ke database
  // jadi semua logicnya ada di service
  public Person get(String id) {
    Person person = personRepository.selectById(id);
    if (person != null) {
      return person;
    } else {
      throw new IllegalArgumentException("Person not found");
    }
  }
  // belajar verifikasi di mocking
  // dimana akan membuat object Person dengan id random,
  // lalu menyimpan ke database via PersonRepository, lalu mengembalikan object person tersebut
  public Person register(String name){
    var person = new Person(UUID.randomUUID().toString(), name);
    personRepository.insert(person);
    return person;
  }
}
