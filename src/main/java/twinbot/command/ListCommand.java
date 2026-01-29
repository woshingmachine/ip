package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command to display all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param taskList the task list to display
     * @param ui the UI for user interaction
     * @param storage the storage for saving tasks
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        ui.showMessage("Here are your tasks, twin:");
        printList(taskList, ui);
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
     * Prints all tasks in the task list.
     *
     * @param taskList the task list to print
     * @param ui       the UI to display tasks
     */
    private void printList(TaskList taskList, Ui ui) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }
}
