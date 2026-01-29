package twinbot.command;

import java.io.IOException;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with parsed task index.
     *
     * @param arguments the arguments containing the task index
     * @throws TwinBotException if parsing fails
     */
    public UnmarkCommand(String arguments) throws TwinBotException {
        this.index = Parser.parseTaskIndex(arguments);
    }

    /**
     * Executes the unmark command.
     *
     * @param taskList the task list to update
     * @param ui the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws TwinBotException if command execution fails
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        if (index >= taskList.getSize()) {
            throw new TwinBotException("Invalid Number.");
        }
        taskList.getTask(index).unmark();
        saveList(taskList, storage, ui);
        ui.showMessage("Nice, twin! I've unmarked the item.");
        printList(taskList, ui);
    }

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

    /**
     * Prints all tasks in the task list.
     *
     * @param taskList the task list to print
     * @param ui the UI to display tasks
     */

    private void printList(TaskList taskList, Ui ui) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }
}
