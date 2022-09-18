package eu.rebase.marsrover.cli;

import java.util.Objects;

public record CommandInputParser() {

    public String parse(String commandString) {
        if (Objects.nonNull(commandString) && !commandString.isBlank() && commandString.matches("[LRFB]*")) {
            return commandString;
        } else {
            throw new IllegalArgumentException("Cannot parse position from: %s only 'LRFB' is supported".formatted(commandString));
        }
    }
}
