package eu.rebase.marsrover.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static eu.rebase.marsrover.domain.CardinalDirection.*;


class MarsRoverTest {

    public static final Grid DEFAULT_GRID = new Grid(-10, 10, -10, 10);

    @ParameterizedTest
    @CsvSource(value = {
            "FLFFFRFLB|(6, 4) NORTH",
    }, delimiter = '|')
    void shouldMoveToCorrectPosition(String commands, String expected) {
        // given
        var rover = initializeRover(EAST, 4, 2, DEFAULT_GRID);
        // when
        var actual = rover.execute(commands);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "F|(0, 1) NORTH",
            "FFFFFFFFFFF|(0, -9) NORTH",
            "B|(0, -1) NORTH",
            "BBBBBBBBBBB|(0, 9) NORTH",
            "L|(0, 0) WEST",
            "LL|(0, 0) SOUTH",
            "LLL|(0, 0) EAST",
            "LLLL|(0, 0) NORTH",
            "R|(0, 0) EAST",
            "RR|(0, 0) SOUTH",
            "RRR|(0, 0) WEST",
            "RRRR|(0, 0) NORTH",
    }, delimiter = '|')
    void shouldMoveFromNorthToCorrectPosition(String commands, String expected) {
        // given
        var rover = initializeRover(NORTH, 0, 0, DEFAULT_GRID);
        // when
        var actual = rover.execute(commands);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "F|(0, -1) SOUTH",
            "FFFFFFFFFFF|(0, 9) SOUTH",
            "B|(0, 1) SOUTH",
            "BBBBBBBBBBB|(0, -9) SOUTH",
            "L|(0, 0) EAST",
            "LL|(0, 0) NORTH",
            "LLL|(0, 0) WEST",
            "LLLL|(0, 0) SOUTH",
            "R|(0, 0) WEST",
            "RR|(0, 0) NORTH",
            "RRR|(0, 0) EAST",
            "RRRR|(0, 0) SOUTH",
    }, delimiter = '|')
    void shouldMoveFromSouthToCorrectPosition(String commands, String expected) {
        // given
        var rover = initializeRover(SOUTH, 0, 0, DEFAULT_GRID);
        // when
        var actual = rover.execute(commands);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "F|(1, 0) EAST",
            "FFFFFFFFFFF|(-9, 0) EAST",
            "B|(-1, 0) EAST",
            "BBBBBBBBBBB|(9, 0) EAST",
            "L|(0, 0) NORTH",
            "LL|(0, 0) WEST",
            "LLL|(0, 0) SOUTH",
            "LLLL|(0, 0) EAST",
            "R|(0, 0) SOUTH",
            "RR|(0, 0) WEST",
            "RRR|(0, 0) NORTH",
            "RRRR|(0, 0) EAST",
    }, delimiter = '|')
    void shouldMoveFromEastToCorrectPosition(String commands, String expected) {
        // given
        var rover = initializeRover(EAST, 0, 0, DEFAULT_GRID);
        // when
        var actual = rover.execute(commands);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "F|(-1, 0) WEST",
            "FFFFFFFFFFF|(9, 0) WEST",
            "B|(1, 0) WEST",
            "BBBBBBBBBBB|(-9, 0) WEST",
            "L|(0, 0) SOUTH",
            "LL|(0, 0) EAST",
            "LLL|(0, 0) NORTH",
            "LLLL|(0, 0) WEST",
            "R|(0, 0) NORTH",
            "RR|(0, 0) EAST",
            "RRR|(0, 0) SOUTH",
            "RRRR|(0, 0) WEST",
    }, delimiter = '|')
    void shouldMoveFromWestToCorrectPosition(String commands, String expected) {
        // given
        var rover = initializeRover(WEST, 0, 0, DEFAULT_GRID);
        // when
        var actual = rover.execute(commands);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "F|(9, 0) WEST",
            "FFFFFFFFFFF|(9, 0) WEST",
            "B|(1, 0) WEST",
            "BBBBBBBBBBB|(1, 0) WEST",
            "L|(0, 0) SOUTH",
            "LL|(0, 0) EAST",
            "LLL|(0, 0) NORTH",
            "LLLL|(0, 0) WEST",
            "R|(0, 0) NORTH",
            "RR|(0, 0) EAST",
            "RRR|(0, 0) SOUTH",
            "RRRR|(0, 0) WEST",
    }, delimiter = '|')
    void shouldMoveCorrectPositionWithDifferentGrid(String commands, String expected) {
        // given
        var rover = initializeRover(WEST, 0, 0, new Grid(0,10,0,10));
        // when
        var actual = rover.execute(commands);
        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionInCaseOfWrongCommand() {
        // given
        var rover = initializeRover(WEST, 0, 0, new Grid(0,10,0,10));
        // when
        String wrongCommand = "W";
        Assertions.assertThatThrownBy( () -> rover.execute(wrongCommand))
                // then
                .hasMessage("Unsupported rover move command: %s".formatted(wrongCommand));
    }

    private static MarsRover initializeRover(CardinalDirection initDirection, int initX, int initY, Grid grid) {
        Coordinate initCoordinate = new Coordinate(initX, initY);
        return new MarsRover(new Position(initCoordinate, initDirection), grid);
    }
}
