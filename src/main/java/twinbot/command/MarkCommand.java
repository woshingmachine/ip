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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        if (index >= taskList.getSize()) {
            throw new TwinBotException("Invalid Number.");
        }
        taskList.getTask(index).mark();
        saveList(taskList, storage, ui);
        ui.showMessage("Nice, twin! I've marked the item.");
        printList(taskList, ui);
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

    private void printList(TaskList taskList, Ui ui) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }
}
