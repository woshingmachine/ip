package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.Event;
import twinbot.ui.Ui;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    /**
     * Constructs an AddEventCommand with parsed arguments.
     *
     * @param arguments the arguments to parse for event task
     * @throws TwinBotException if parsing fails
     */
    public AddEventCommand(String arguments) throws TwinBotException {
        String[] parts = Parser.parseEvent(arguments);
        this.description = parts[0];
        this.start = parts[1];
        this.end = parts[2];
    }

    /**
     * Executes the add event command.
     *
     * @param taskList the task list to add to
     * @param ui       the UI for user interaction
     * @param storage  the storage for saving tasks
     * @throws TwinBotException if command execution fails
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        Event event = new Event(description, start, end);
        taskList.addTask(event);
        saveTaskList(taskList, storage);
        String message = "Added: " + description + " from " + start + " to " + end + "\n"
                + listCount(taskList.getSize());
        ui.showMessage(message);
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false as this command does not exit
     */

    @Override
    public boolean isExit() {
        return false;
    }
}
