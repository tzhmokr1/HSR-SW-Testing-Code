package ch.hsr.testing.unittest.parametrized;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PrimeCheckerTest {

    @ParameterizedTest(name = "Test {index} => input:{0} => isPrime?:{1}")
    @MethodSource("createTestInput")
    void testPrimeCheckerParametrized(int input, boolean isPrime) {
        boolean actual = PrimeChecker.isPrime(input);
        MatcherAssert.assertThat(actual, Matchers.is(isPrime));
    }


    private static Stream<Arguments> createTestInput() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(4, false),
                Arguments.of(9, false),
                Arguments.of(22, false),
                Arguments.of(23, true),
                Arguments.of(144, false),
                Arguments.of(7187, true)
                );
    }


}