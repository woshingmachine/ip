package twinbot.command;

import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.task.Deadline;
import twinbot.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    /**
     * Constructs an AddDeadlineCommand with parsed arguments.
     *
     * @param arguments the arguments to parse for deadline task
     * @throws TwinBotException if parsing fails
     */
    public AddDeadlineCommand(String arguments) throws TwinBotException {
        String[] parts = Parser.parseDeadline(arguments);
        this.description = parts[0];
        this.deadline = parts[1];
    }

    /**
     * Executes the add deadline command.
     *
     * @param taskList the task list to add to
     * @param ui       the UI for user interaction
     * @param storage  the storage for saving tasks
     * @throws TwinBotException if command execution fails
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        Deadline deadlineTask = new Deadline(description, deadline);
        taskList.addTask(deadlineTask);
        saveTaskList(taskList, storage);
        String message = "Added: " + description + " by " + deadline + "\n" + listCount(taskList.getSize());
        ui.showMessage(message);
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
