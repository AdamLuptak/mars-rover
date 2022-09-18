package eu.rebase.marsrover.cli;

import eu.rebase.marsrover.domain.Position;
import org.junit.jupiter.api.Test;

import static eu.rebase.marsrover.domain.CardinalDirection.EAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionInputParserTest {

    @Test
    void shouldParsePosition(){
        // given
        var positionInputParser = new PositionInputParser();
        var expected = new Position(4,2, EAST);
        // when
        var actual = positionInputParser.parse("4|2|EAST");
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenParsePosition(){
        // given
        var positionInputParser = new PositionInputParser();
        // when
        assertThatThrownBy(() ->  positionInputParser.parse("WRONG4|2|EAST"))
                // then
                .hasMessage("Cannot parse position from: WRONG4|2|EAST cause: For input string: \"WRONG4\", Correct format x|y|CardinalDirection");
    }
}