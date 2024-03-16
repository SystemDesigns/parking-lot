package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandTest {

    @Test
    public void testCommandParsingFromInput() {
        validateCommandParsing("my_command 1 2 3", "my_command", List.of("1", "2", "3"));
        validateCommandParsing("my_command  1    3", "my_command", List.of("1", "3"));
        validateCommandParsing("park 1 3", "park", List.of("1", "3"));
    }

    private void validateCommandParsing(final String input, final String expectedCommandName, final List<String> expectedCommandParams) {
        Command command = new Command(input);
        assertNotNull(command);
        assertEquals(expectedCommandName, command.getCommandName());
        assertEquals(expectedCommandParams, command.getParams());
    }

}