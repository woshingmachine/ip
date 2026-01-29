package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList the task list to operate on
     * @param ui       the UI for user interaction
     * @param storage  the storage for saving changes
     * @throws TwinBotException if the command execution fails
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException;

    /**
     * Checks if this command should exit the program.
     *
     * @return true if the program should exit, false otherwise
     */
    public abstract boolean isExit();

    /**
     * Returns a message showing the current number of tasks.
     *
     * @param count the total number of tasks
     * @return formatted message with task count
     */
    protected String listCount(int count) {
        return "You now have " + count + " tasks in your list, twin";
    }
}
