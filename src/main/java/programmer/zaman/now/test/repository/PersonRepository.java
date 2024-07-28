package programmer.zaman.now.test.repository;

import programmer.zaman.now.test.data.Person;
// belajar mocking di test disini kita buat interfacenya
// anggap saja ini jembatan antara program kita dengan database
public interface PersonRepository {

  Person selectById(String id);
  // belajar verifikasi di mocking
  // untuk menyimpan data ke database , namun tidak mengembalikan value
  void insert(Person person);

}
