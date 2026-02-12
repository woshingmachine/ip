package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.Task;
import twinbot.ui.Ui;

/**
 * Command to remove a tag from a task.
 */
public class UntagCommand extends Command {
    private final int index;
    private final String tag;

    /**
     * Constructs an UntagCommand with parsed arguments.
     *
     * @param arguments the arguments containing the task index and tag
     * @throws TwinBotException if parsing fails
     */
    public UntagCommand(String arguments) throws TwinBotException {
        if (arguments == null || arguments.trim().isEmpty()) {
            throw new TwinBotException("Twin, use 'untag <index> #tag'.");
        }
        String[] parts = arguments.trim().split(" ", 2);
        if (parts.length < 2) {
            throw new TwinBotException("Twin, use 'untag <index> #tag'.");
        }
        this.index = Parser.parseTaskIndex(parts[0]);
        this.tag = parts[1].trim();
        if (!this.tag.startsWith("#")) {
            throw new TwinBotException("Twin, tag must start with #.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        if (index >= taskList.getSize()) {
            throw new TwinBotException("Invalid Number.");
        }
        Task task = taskList.getTask(index);
        if (!task.getTags().contains(tag.substring(1).toLowerCase())) {
            throw new TwinBotException("Twin, that tag is not on this task.");
        }
        task.removeTag(tag);
        saveTaskList(taskList, storage);
        String message = "Ok twin, I've removed this tag:\n" + task;
        ui.showMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
