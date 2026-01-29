package twinbot.command;

import java.io.IOException;

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        Event event = new Event(description, start, end);
        taskList.addTask(event);
        saveList(taskList, storage, ui);
        ui.showMessage("Added: " + description + " from " + start + " to " + end);
        ui.showMessage(listCount(taskList.getSize()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void saveList(TaskList taskList, Storage storage, Ui ui) throws TwinBotException {
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new TwinBotException("Error saving tasks: " + e.getMessage());
        }
    }
}
