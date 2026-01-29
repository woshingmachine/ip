package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * Command to display help information.
 */
public class HelpCommand extends Command {
    
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
    
    @Override
    public boolean isExit() {
        return false;
    }
}
