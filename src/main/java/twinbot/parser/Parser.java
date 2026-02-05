package twinbot.parser;

import twinbot.command.AddDeadlineCommand;
import twinbot.command.AddEventCommand;
import twinbot.command.AddTodoCommand;
import twinbot.command.Command;
import twinbot.command.DeleteCommand;
import twinbot.command.ExitCommand;
import twinbot.command.FindCommand;
import twinbot.command.HelpCommand;
import twinbot.command.InvalidCommand;
import twinbot.command.ListCommand;
import twinbot.command.MarkCommand;
import twinbot.command.UnmarkCommand;
import twinbot.exception.TwinBotException;

/**
 * Parses user input into commands.
 */
public class Parser {

    /**
     * Parses the user's full command string into a Command object.
     *
     * @param fullCommand the complete user input
     * @return a Command object representing the user's action
     * @throws TwinBotException if parsing fails
     */
    public static Command parse(String fullCommand) throws TwinBotException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments.trim());
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            case "find":
                return new FindCommand(arguments);
            case "help":
                return new HelpCommand();
            default:
                return new InvalidCommand();
        }
    }

    /**
     * Parses a deadline command to extract description and deadline.
     *
     * @param arguments the arguments after "deadline"
     * @return array containing [description, deadline]
     * @throws TwinBotException if format is invalid
     */
    public static String[] parseDeadline(String arguments) throws TwinBotException {
        String[] parts = arguments.split("/by", 2);
        if (parts.length < 2) {
            throw new TwinBotException("Twin, use 'deadline task /by date'\n"
                    + "Valid date formats: yyyy-MM-dd HH:mm, d/M/yyyy HHmm, or d/M/yyyy");
        }

        String description = parts[0].trim();
        String deadline = parts[1].trim();

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new TwinBotException("Twin, use 'deadline task /by date'\n"
                    + "Valid date formats: yyyy-MM-dd HH:mm, d/M/yyyy HHmm, or d/M/yyyy");
        }

        return new String[] { description, deadline };
    }

    /**
     * Parses an event command to extract description, start, and end times.
     *
     * @param arguments the arguments after "event"
     * @return array containing [description, start, end]
     * @throws TwinBotException if format is invalid
     */
    public static String[] parseEvent(String arguments) throws TwinBotException {
        String[] parts = arguments.split("/from|/to", 3);
        if (parts.length < 3) {
            throw new TwinBotException("Twin, use 'event task /from start /to end.'\n"
                    + "Valid date formats: yyyy-MM-dd HH:mm, d/M/yyyy HHmm, or d/M/yyyy");
        }

        String description = parts[0].trim();
        String start = parts[1].trim();
        String end = parts[2].trim();

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new TwinBotException("Twin, use 'event task /from start /to end.'\n"
                    + "Valid date formats: yyyy-MM-dd HH:mm, d/M/yyyy HHmm, or d/M/yyyy");
        }

        return new String[] { description, start, end };
    }

    /**
     * Parses a task index from a string.
     *
     * @param indexString the string containing the index
     * @return the parsed index (0-based)
     * @throws TwinBotException if the index is invalid
     */
    public static int parseTaskIndex(String indexString) throws TwinBotException {
        try {
            int index = Integer.parseInt(indexString.trim()) - 1;
            if (index < 0) {
                throw new TwinBotException("Twin, use a positive number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TwinBotException("Please enter a valid number.");
        }
    }
}
