package programmer.zaman.now.test;

import org.junit.jupiter.api.*;
import org.opentest4j.TestAbortedException;
import programmer.zaman.now.test.generator.SimpleDisplayNameGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayNameGeneration(SimpleDisplayNameGenerator.class)
//@DisplayName("Test untuk Calculator class")
public class CalculatorTest {

  private Calculator calculator = new Calculator();
  //  belajar sebelum dan setelah Semu Unit Test
  @BeforeAll
  public static void beforeAll(){
    System.out.println("Before all");
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("After all");
  }

  //  belajar sebelum dan setelah Test
  @BeforeEach
  // digunakan untuk menandai function yang akan dieksekusi sebelum unit test dijalankan
  public void setUp() {
    System.out.println("Before each");
  }

  @AfterEach
  //digunakan untuk menandai function yang akan dieksekusi setelah unit test dijalankan
  //Ingat, bahwa ini akan selalu dieksekusi setiap kali untuk function @Test, bukan sekali untuk class test saja
  public void tearDown() {
    System.out.println("After each");
  }

  @Test
  // @DisplayName("Test skenario sukses untuk method add(integer, integer)")
  public void testAddSuccess() {
    var result = calculator.add(10, 10);
    assertEquals(20, result);
//    if (result != 20) {
//      throw new RuntimeException("Test gagal");
//    }

  }

  @Test
  public void testDivideSuccess() {
    var result = calculator.divide(100, 10);
    assertEquals(10, result);
  }

  @Test
  public void testDivideFailed() {
    assertThrows(IllegalArgumentException.class, () -> {
      calculator.divide(10, 0);
    });
  }

  @Test
  // belajar menonaktifkan Test
  @Disabled
  public void testComingSoon() {

  }

  // belajar membatalkan test
  // jika tidak dev maka ini akan kita aborted
  // setting enviromentnya dulu agar bisa jalan di edit konfiguration -> enviroment variable
  // namenya : PROFILE valuenya : DEV
  @Test
  public void testAborted(){
    var profile = System.getenv("PROFILE");
    if(!"DEV".equals(profile)){
      throw new TestAbortedException("Test dibatalkan karena bukan DEV");
    }

    // unit test untuk DEV
  }

  // belajar menggunakan assumption
  // get Profile enviromentnnya harus dev , pakai ini saja lebih mudah
  @Test
  public void testAssumptions(){
    assumeTrue("DEV".equals(System.getenv("PROFILE")));

    // unit test untuk DEV
  }

}
