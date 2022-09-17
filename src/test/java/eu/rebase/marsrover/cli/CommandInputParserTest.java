package eu.rebase.marsrover.cli;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandInputParserTest {

    @Test
    void shouldParseCommand() {
        // given
        var commandParser = new CommandInputParser();
        // when
        var actual = commandParser.parse("FBLR");
        // then
        assertThat(actual).isEqualTo("FBLR");
    }

    @Test
    void shouldThrowExceptionWhenEmptyString() {
        // given
        var commandParser = new CommandInputParser();
        // when
        assertThatThrownBy(() -> commandParser.parse(""))
                // then
                .hasMessage("Cannot parse position from: ");
    }

    @Test
    void shouldThrowExceptionWhenInNull() {
        // given
        var commandParser = new CommandInputParser();
        // when
        assertThatThrownBy(() -> commandParser.parse(null))
                // then
                .hasMessage("Cannot parse position from: null");
    }

    @Test
    void shouldThrowExceptionWhenParsePosition() {
        // given
        var commandParser = new CommandInputParser();
        // when
        assertThatThrownBy(() -> commandParser.parse("WRONGLFBLR"))
                // then
                .hasMessage("Cannot parse position from: WRONGLFBLR");
    }
}