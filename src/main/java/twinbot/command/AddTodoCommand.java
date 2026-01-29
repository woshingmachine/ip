package twinbot.command;

import java.io.IOException;

import twinbot.exception.TwinBotException;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.ToDo;
import twinbot.ui.Ui;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the given description.
     *
     * @param description the description of the todo task
     * @throws TwinBotException if description is empty
     */
    public AddTodoCommand(String description) throws TwinBotException {
        if (description.isEmpty()) {
            throw new TwinBotException("Twin, use 'todo task'");
        }
        this.description = description;
    }

    /**
     * Executes the add todo command.
     *
     * @param taskList the task list to add to
     * @param ui the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws TwinBotException if command execution fails
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        ToDo toDo = new ToDo(description);
        taskList.addTask(toDo);
        saveList(taskList, storage, ui);
        ui.showMessage("Added: " + toDo.getDescription());
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
