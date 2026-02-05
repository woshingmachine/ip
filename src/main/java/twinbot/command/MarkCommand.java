package twinbot.command;

import java.io.IOException;

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
        if (index >= taskList.getSize()) {
            throw new TwinBotException("Invalid Number.");
        }
        taskList.getTask(index).mark();
        saveList(taskList, storage, ui);
        StringBuilder sb = new StringBuilder("Nice, twin! I've marked the item.\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append(i + 1).append(". ").append(taskList.getTask(i).toString()).append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        ui.showMessage(sb.toString());
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
