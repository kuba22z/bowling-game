package game;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameStateTest {
    static Stream<Arguments> calculateScoreTestArguments()
    {
        return Stream.of(
                Arguments.arguments(List.of(1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6), 133),
                Arguments.arguments(List.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,10), 300),
                Arguments.arguments(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0),
                Arguments.arguments(List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), 20)
        );
    }

    @MethodSource("calculateScoreTestArguments")
    @ParameterizedTest
    void calculateScoreTest(List<Integer> pins, int expectedScore) {
        final var game =new BowlingGameState(10,10);

        pins.forEach(game::roll);

        assertEquals(expectedScore,game.calculateScore());
    }
}