package eu.rebase.marsrover.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static eu.rebase.marsrover.domain.CardinalDirection.NORTH;

class GridTest {

    public static final Grid DEFAULT_GRID = new Grid(-10, 10, -10, 10);

    @ParameterizedTest
    @CsvSource(value = {
            "1,1",
            "10,10",
            "-1,-1",
            "0,0",
            "-5,10",
    })
    void shouldBeValidPositionInGrid(Integer x, Integer y) {
        // given
        var position = new Position(new Coordinate(x, y), NORTH);
        // when
        boolean actual = DEFAULT_GRID.isValidPosition(position);
        // then
        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "11,11,false",
            "11,10,false",
            "10,11,false",
            "-11,-11,false",
            "-11,-10,false",
            "-10,-11,false",
            "11,-11,false",
            "11,-10,false",
            "10,-11,false",
            "-11,11,false",
            "-11,10,false",
            "-10,11,false",
    })
    void shouldBeInValidPositionInGrid(Integer x, Integer y) {
        // given
        var position = new Position(new Coordinate(x, y), NORTH);
        // when
        boolean actual = DEFAULT_GRID.isValidPosition(position);
        // then
        Assertions.assertThat(actual).isFalse();
    }

}