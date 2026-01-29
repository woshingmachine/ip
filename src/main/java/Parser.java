/**
 * Parses user input into commands and arguments.
 */
public class Parser {
    
    /**
     * Parses the user's full command string.
     *
     * @param fullCommand the complete user input
     * @return a ParsedCommand object containing the command and arguments
     */
    public static ParsedCommand parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        
        return new ParsedCommand(command, arguments);
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
            throw new TwinBotException("Twin, use 'deadline task /by date'\n");
        }
        
        String description = parts[0].trim();
        String deadline = parts[1].trim();
        
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new TwinBotException("Twin, use 'deadline task /by date'\n");
        }
        
        return new String[]{description, deadline};
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
            throw new TwinBotException("Twin, use 'event task /from start /to end.'\n");
        }
        
        String description = parts[0].trim();
        String start = parts[1].trim();
        String end = parts[2].trim();
        
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new TwinBotException("Twin, use 'event task /from start /to end.'\n");
        }
        
        return new String[]{description, start, end};
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
                throw new TwinBotException("Twin, use a positive number.\n");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TwinBotException("Please enter a valid number.\n");
        }
    }
}

/**
 * Represents a parsed command with its arguments.
 */
class ParsedCommand {
    private final String command;
    private final String arguments;
    
    public ParsedCommand(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }
    
    public String getCommand() {
        return command;
    }
    
    public String getArguments() {
        return arguments;
    }
}
