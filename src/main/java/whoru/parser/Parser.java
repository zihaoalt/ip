package whoru.parser;

import whoru.command.*;

public class Parser {
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
        } else {
            command = new UnknownCommand();
        }

        return command;
    }
}
