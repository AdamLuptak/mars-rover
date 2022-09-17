package eu.rebase.marsrover;


import eu.rebase.marsrover.cli.CommandInputParser;
import eu.rebase.marsrover.cli.PositionInputParser;
import eu.rebase.marsrover.domain.Grid;
import eu.rebase.marsrover.domain.MarsRover;
import eu.rebase.marsrover.exception.InvalidGridPositionException;

public class Application {
    public static void main(String[] args) {
        var initPositionString = args[0];
        var commandsString = args[1];

        var grid = new Grid(-10,10,-10,10);
        var positionInputParser = new PositionInputParser();
        var commandInputParser = new CommandInputParser();

        var initPosition = positionInputParser.parse(initPositionString);
        var commands = commandInputParser.parse(commandsString);

       if(!grid.isValidPosition(initPosition)){
           throw new InvalidGridPositionException("Invalid init position: %s".formatted(initPosition));
       }

        var marsRover = new MarsRover(initPosition, grid);

        var positionReport = marsRover.execute(commands);
        System.out.println(positionReport);
    }
}