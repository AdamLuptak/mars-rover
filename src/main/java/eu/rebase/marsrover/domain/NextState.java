package eu.rebase.marsrover.domain;

public record NextState(Integer deltaMoveX, Integer deltaMoveY, CardinalDirection left, CardinalDirection right) {
}
