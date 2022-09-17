package eu.rebase.marsrover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest {

    @Test
    void shouldThrowInvalidGridPositionException() {
        var arguments = new String[]{"343|2|EAST", "FLFFFRFLB"};
        assertThatThrownBy(() -> Application.main(arguments))
                .hasMessage("Invalid init position: Position[coordinate=Coordinate[x=343, y=2], cardinalDirection=EAST]");
    }

    @Test
    void shouldMoveRover() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        var arguments = new String[]{"4|2|EAST", "FLFFFRFLB"};
        Application.main(arguments);

        assertThat(out).hasToString("(6, 4) NORTH\n");
    }
}