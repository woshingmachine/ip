package twinbot.command;

import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    
    /**
     * Executes the exit command.
     *
     * @param taskList the task list (unused)
     * @param ui the UI for user interaction
     * @param storage the storage (unused)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
    
    /**
     * Indicates whether this command exits the application.
     *
     * @return true as this command exits
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
