package twinbot.command;

import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command representing an invalid user input.
 */
public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("Twin, icl idk what that means. Type help for list of commands.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
