package programmer.zaman.now.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import programmer.zaman.now.test.resolver.RandomParameterResolver;

import java.util.List;
import java.util.Random;
// belajar Dependency Injection di Test Menggunakan Parameter Resolver
//@Extensions({
//        @ExtendWith(RandomParameterResolver.class)
//})

public class RandomCalculatorTest extends AbstractCalculatorTest {

  @Test
  void testRandom(TestInfo info, Random random) {
    var a = random.nextInt();
    var b = random.nextInt();

    var result = calculator.add(a, b);
    var expected = a + b;

    Assertions.assertEquals(expected, result);

  }
 // belajar Test Berulang
 // JUnit mendukung eksekusi unit test berulang kali sesuai dengan jumlah yang kita tentukan
 // Untuk mengulang eksekusi unit test, kita bisa menggunakan annotation @RepeatedTest di method unit test nya

 // @RepeatedTest juga bisa digunakan untuk mengubah detail nama test nya, dan kita bisa menggunakan value {displayName}
 // untuk mendapatkan display name, {currentRepetition} untuk mendapatkan perulangan ke berapa saat ini,
 // dan {totalRepetitions} untuk mendapatkan total perulangan nya
  @DisplayName("Test random calculator")
  @RepeatedTest(
      value = 10,
      name = "{displayName} ke {currentRepetition} dari {totalRepetitions}"
  )
  void testRandomRepeat(TestInfo info, Random random) {
    var a = random.nextInt();
    var b = random.nextInt();

    var result = calculator.add(a, b);
    var expected = a + b;

    Assertions.assertEquals(expected, result);

  }
  // belajar Test Berulang Informasi Perulangan
  @DisplayName("Test random calculator")
  @RepeatedTest(value = 10, name = "{displayName}")
  void testRandomRepeatInfo(TestInfo info, Random random, RepetitionInfo repetitionInfo) {
    System.out.println(info.getDisplayName() + " ke " + repetitionInfo.getCurrentRepetition() + " dari " +
            repetitionInfo.getTotalRepetitions());
    var a = random.nextInt();
    var b = random.nextInt();

    var result = calculator.add(a, b);
    var expected = a + b;

    Assertions.assertEquals(expected, result);

  }
  // Test dengan Parameter
  // Parameter dengan @ValueSource
  @DisplayName("Test calculator")
  @ParameterizedTest(name = "{displayName} dengan parameter {0}")
  @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 45, 34, 56})
  void testWithParameter(int value) {
    var expected = value + value;
    var result = calculator.add(value, value);

    Assertions.assertEquals(expected, result);
  }


    // Test dengan Parameter
    // Parameter dengan @MethodSource
  public static List<Integer> parameterSource() {
    return List.of(1, 2, 34, 56, 33, 65, 67, 35);
  }

  @DisplayName("Test calculator")
  @ParameterizedTest(name = "{displayName} dengan parameter {0}")
  @MethodSource({"parameterSource"})
  void testWithMethodSource(Integer value) {
    var expected = value + value;
    var result = calculator.add(value, value);

    Assertions.assertEquals(expected, result);
  }
}
