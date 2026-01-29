package twinbot.command;

import java.io.IOException;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.Deadline;
import twinbot.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;
    
    /**
     * Constructs an AddDeadlineCommand with parsed arguments.
     *
     * @param arguments the arguments to parse for deadline task
     * @throws TwinBotException if parsing fails
     */
    public AddDeadlineCommand(String arguments) throws TwinBotException {
        String[] parts = Parser.parseDeadline(arguments);
        this.description = parts[0];
        this.deadline = parts[1];
    }
    
    /**
     * Executes the add deadline command.
     *
     * @param taskList the task list to add to
     * @param ui the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws TwinBotException if command execution fails
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        Deadline deadlineTask = new Deadline(description, deadline);
        taskList.addTask(deadlineTask);
        saveList(taskList, storage, ui);
        ui.showMessage("Added: " + description + " by " + deadline);
        ui.showMessage(listCount(taskList.getSize()));
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
    
    /**
     * Saves the task list to storage.
     *
     * @param taskList the task list to save
     * @param storage the storage to save to
     * @param ui the UI for displaying messages
     * @throws TwinBotException if saving fails
     */
    private void saveList(TaskList taskList, Storage storage, Ui ui) throws TwinBotException {
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new TwinBotException("Error saving tasks: " + e.getMessage());
        }
    }
}
