package eu.rebase.marsrover.domain;

import static java.lang.Math.abs;

public class Grid {

    private final Integer minWidth;
    private final Integer maxWidth;
    private final Integer minHeight;
    private final Integer maxHeight;
    private final Integer totalHeight;
    private final Integer totalWidth;


    public Grid(Integer minWidth, Integer maxWidth, Integer minHeight, Integer maxHeight) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.totalHeight = abs(minHeight) + abs(maxHeight);
        this.totalWidth = abs(minWidth) + abs(maxWidth);
    }

    public Coordinate wrapCoordinate(Integer x, Integer y) {
        var nextX = x;
        var nextY = y;

        if (nextY > maxHeight) {
            nextY = y - totalHeight;
        }

        if (nextY < minHeight) {
            nextY = y + totalHeight;
        }

        if (nextX > maxWidth) {
            nextX = x - totalWidth;
        }

        if (nextX < minWidth) {
            nextX = x + totalWidth;
        }

        return new Coordinate(nextX, nextY);
    }

    public boolean isValidPosition(Position initPosition) {
        var x = initPosition.coordinate().x();
        var y = initPosition.coordinate().y();

        return x >= minWidth && x <= maxWidth &&
                y >= minHeight && y <= maxHeight;
    }
}