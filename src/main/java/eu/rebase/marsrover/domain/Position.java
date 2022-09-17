package eu.rebase.marsrover.domain;

public record Position(Coordinate coordinate, CardinalDirection cardinalDirection) {

    public Position(Integer x, Integer y, CardinalDirection cardinalDirection) {
        this(new Coordinate(x, y), cardinalDirection);
    }
}
