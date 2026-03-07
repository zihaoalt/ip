package whoru.parser;

import whoru.command.*;
/**
 * Parses raw user input into the corresponding {@code Command} object.
 */
public class Parser {
    /**
     * Parses the full user input and returns the corresponding command.
     *
     * @param fullCommand Full command entered by the user.
     * @return Command corresponding to the user input, or an {@code UnknownCommand}
     *         if the input does not match any known command.
     */
    public static Command parse(String fullCommand) {
        Command command = null;
        if (fullCommand.equalsIgnoreCase("bye")) {
            command = new ByeCommand();
        } else if (fullCommand.equalsIgnoreCase("list")) {
            command = new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            command = new MarkCommand(true);
        } else if (fullCommand.startsWith("unmark")) {
            command = new MarkCommand(false);
        } else if (fullCommand.startsWith("todo")) {
            command = new AddTodoCommand();
        } else if (fullCommand.startsWith("deadline")) {
            command = new AddDeadlineCommand();
        } else if (fullCommand.startsWith("event")) {
            command = new AddEventCommand();
        } else if (fullCommand.startsWith("delete")) {
            command = new DeleteCommand();
        } else if (fullCommand.startsWith("find")) {
            command = new FindCommand();
        } else {
            command = new UnknownCommand();
        }

        return command;
    }
}
