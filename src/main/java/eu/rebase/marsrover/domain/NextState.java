package eu.rebase.marsrover.domain;

public record NextState(Integer deltaX, Integer deltaY, CardinalDirection left, CardinalDirection right) {
}
