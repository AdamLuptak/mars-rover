package eu.rebase.marsrover.cli;

import eu.rebase.marsrover.domain.CardinalDirection;
import eu.rebase.marsrover.domain.Coordinate;
import eu.rebase.marsrover.domain.Position;

import java.util.Objects;

public class PositionInputParser {
    public Position parse(String initPositionString) {
        try {
            String[] split = initPositionString.split("\\|");
            var x = split[0];
            var y = split[1];
            var direction = CardinalDirection.valueOf(split[2]);

            Objects.requireNonNull(x, "cannot parse x coordinate");
            Objects.requireNonNull(y, "cannot parse y coordinate");
            Objects.requireNonNull(direction, "cannot parse direction coordinate");

            return new Position(new Coordinate(Integer.valueOf(x), Integer.valueOf(y)), direction);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse position from: %s cause: %s, Correct format x|y|CardinalDirection".formatted(initPositionString,e.getMessage()));
        }
    }
}
