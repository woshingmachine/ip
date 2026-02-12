package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with parsed task index.
     *
     * @param arguments the arguments containing the task index
     * @throws TwinBotException if parsing fails
     */
    public MarkCommand(String arguments) throws TwinBotException {
        this.index = Parser.parseTaskIndex(arguments);
    }

    /**
     * Executes the mark command.
     *
     * @param taskList the task list to update
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
        taskList.getTask(index).mark();
        saveTaskList(taskList, storage);
        String message = "Nice, twin! I've marked the item.\n" + formatTaskList(taskList);
        ui.showMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
