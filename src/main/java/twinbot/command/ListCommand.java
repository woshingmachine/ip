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
     * @param ui       the UI for user interaction
     * @param storage  the storage for saving tasks
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        StringBuilder sb = new StringBuilder("Here are your tasks, twin:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append(i + 1).append(". ").append(taskList.getTask(i).toString()).append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        ui.showMessage(sb.toString());
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
}
