package twinbot.command;

import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command representing an invalid user input.
 */
public class InvalidCommand extends Command {
    
    /**
     * Executes the invalid command handler.
     *
     * @param taskList the task list (unused)
     * @param ui the UI for user interaction
     * @param storage the storage (unused)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("Twin, icl idk what that means. Type help for list of commands.");
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
