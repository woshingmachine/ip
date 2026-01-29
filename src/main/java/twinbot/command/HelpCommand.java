package twinbot.command;

import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command to display help information.
 */
public class HelpCommand extends Command {
    
    /**
     * Executes the help command.
     *
     * @param taskList the task list (unused)
     * @param ui the UI for user interaction
     * @param storage the storage (unused)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage(
                "- todo <task>\n"
                + "- deadline <task> /by <date>\n"
                + "- event <task> /from <start> to <end>\n"
                + "- bye\n- list\n- mark <number>\n- unmark <number>\n"
                + "- delete <number>"
        );
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
