package twinbot.command;

import java.io.IOException;

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

    /**
     * Saves the task list to storage.
     *
     * @param taskList the task list to save
     * @param storage  the storage to save to
     * @throws TwinBotException if saving fails
     */
    protected void saveTaskList(TaskList taskList, Storage storage) throws TwinBotException {
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new TwinBotException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Formats a task list as a numbered string.
     *
     * @param taskList the task list to format
     * @return formatted string with numbered tasks
     */
    protected String formatTaskList(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append(i + 1).append(". ").append(taskList.getTask(i).toString());
            if (i < taskList.getSize() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
