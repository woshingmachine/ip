package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.Task;
import twinbot.ui.Ui;

/**
 * Command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword the keyword to search for
     * @throws TwinBotException if keyword is empty
     */
    public FindCommand(String keyword) throws TwinBotException {
        if (keyword.trim().isEmpty()) {
            throw new TwinBotException("Twin, please provide a keyword to search for.");
        }
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                sb.append("\n").append(i + 1).append(". ").append(task.toString());
            }
        }
        if (count == 0) {
            sb = new StringBuilder("No matching tasks found.");
        }
        ui.showMessage(sb.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
