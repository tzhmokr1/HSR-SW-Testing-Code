package ch.hsr.testing.unittest.tdd.worldcup;

import ch.hsr.testing.unittest.tdd.worldcup.GameResult;
import ch.hsr.testing.unittest.tdd.worldcup.WorldCupCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WorldCupCalculatorTest {

    private WorldCupCalculator worldCupCalculator = new WorldCupCalculator();

    @Test
    public void testWrongWinner() {
        int nofPoints = worldCupCalculator.calculate(new GameResult(1, 3), new GameResult(2, 2));
        assertThat(nofPoints, is(0));
    }

    @Test
    public void testCorrectResult() {
        int nofPoints = worldCupCalculator.calculate(new GameResult(3, 1), new GameResult(3, 1));
        assertThat(nofPoints, is(3));
    }


    @Test
    public void testCorrectResultTie() {
        int nofPoints = worldCupCalculator.calculate(new GameResult(3, 3), new GameResult(3, 3));
        assertThat(nofPoints, is(3));
    }

    @Test
    public void testSameDelta() {
        int nofPoints = worldCupCalculator.calculate(new GameResult(4, 3), new GameResult(3, 2));
        assertThat(nofPoints, is(2));
    }

    @Test
    public void testCorrectWinner() {
        int nofPoints = worldCupCalculator.calculate(new GameResult(3, 1), new GameResult(1, 0));
        assertThat(nofPoints,is(1));
    }

    @Test
    public void testTieWithWrongResult() {
        int nofPoints = worldCupCalculator.calculate(new GameResult(3, 3), new GameResult(1, 1));
        assertThat(nofPoints,is(1));
    }


    @Test
    public void testThrowsExceptionOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> worldCupCalculator.calculate(null, new GameResult(1, 1)));
    }

    @ParameterizedTest(name = "{index} => tipp:{0}, result:{1}  => {2} Points")
    @MethodSource("createResults")
    void testCalculator(GameResult tipp, GameResult result, int expectedNofPoints) {
        int nofPoints = worldCupCalculator.calculate(tipp, result);
        assertThat(nofPoints, is(expectedNofPoints));
    }

    private static Stream<Arguments> createResults() {
        return Stream.of(
                Arguments.of(new GameResult(1, 2), new GameResult(4, 1), 0),
                Arguments.of(new GameResult(1, 2), new GameResult(1, 3), 1));
    }

}
