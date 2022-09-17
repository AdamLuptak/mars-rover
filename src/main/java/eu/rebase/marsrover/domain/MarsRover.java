package eu.rebase.marsrover.domain;

import eu.rebase.marsrover.exception.UnsupportedRoverCommandException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

import static eu.rebase.marsrover.domain.CardinalDirection.*;

@Data
@AllArgsConstructor
public class MarsRover {
    private Coordinate coordinate;
    private CardinalDirection cardinalDirection;
    private Grid grid;

    private final Map<CardinalDirection, NextState> stateMap = Map.of(
            NORTH, new NextState(0, 1, WEST, EAST),
            SOUTH, new NextState(0, -1, EAST, WEST),
            EAST, new NextState(1, 0, NORTH, SOUTH),
            WEST, new NextState(-1, 0, SOUTH, NORTH)
    );

    public MarsRover(Position initPosition, Grid grid) {
        this.coordinate = initPosition.coordinate();
        this.grid = grid;
        this.cardinalDirection = initPosition.cardinalDirection();
    }

    public String execute(String commands) {
        for (char c : commands.toCharArray()) {
            switch (c) {
                case 'L' -> moveLeft();
                case 'R' -> moveRight();
                case 'F' -> moveForward();
                case 'B' -> moveBackward();
                default -> throw new UnsupportedRoverCommandException("Unsupported rover move command: %s".formatted(c));
            }
        }
        return "(%d, %d) %s".formatted(coordinate.x(), coordinate.y(), cardinalDirection);
    }

    private void moveRight() {
        cardinalDirection = stateMap.get(cardinalDirection).right();
    }

    private void moveLeft() {
        cardinalDirection = stateMap.get(cardinalDirection).left();
    }

    private void moveBackward() {
        var nextState = stateMap.get(cardinalDirection);
        var x = this.coordinate.x() - nextState.deltaX();
        var y = this.coordinate.y() - nextState.deltaY();
        coordinate = grid.wrapCoordinate(x, y);
    }

    private void moveForward() {
        var nextState = stateMap.get(cardinalDirection);
        var x = this.coordinate.x() + nextState.deltaX();
        var y = this.coordinate.y() + nextState.deltaY();
        coordinate = grid.wrapCoordinate(x, y);
    }
}
