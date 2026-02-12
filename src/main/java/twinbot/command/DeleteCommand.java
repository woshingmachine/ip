package twinbot.command;

import java.io.IOException;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.Task;
import twinbot.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with parsed task index.
     *
     * @param arguments the arguments containing the task index
     * @throws TwinBotException if parsing fails
     */
    public DeleteCommand(String arguments) throws TwinBotException {
        this.index = Parser.parseTaskIndex(arguments);
    }

    /**
     * Executes the delete command.
     *
     * @param taskList the task list to remove from
     * @param ui       the UI for user interaction
     * @param storage  the storage for saving tasks
     * @throws TwinBotException if command execution fails
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        if (index >= taskList.getSize()) {
            throw new TwinBotException("Invalid Number.");
        }
        Task task = taskList.removeTask(index);
        saveList(taskList, storage, ui);
        String message = "Ok twin, I've removed this task: " + task + "\n" + listCount(taskList.getSize());
        ui.showMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Saves the task list to storage.
     *
     * @param taskList the task list to save
     * @param storage  the storage to save to
     * @param ui       the UI for displaying messages
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
