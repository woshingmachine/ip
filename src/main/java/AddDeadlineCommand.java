import java.io.IOException;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;
    
    public AddDeadlineCommand(String arguments) throws TwinBotException {
        String[] parts = Parser.parseDeadline(arguments);
        this.description = parts[0];
        this.deadline = parts[1];
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        Deadline deadlineTask = new Deadline(description, deadline);
        taskList.addTask(deadlineTask);
        saveList(taskList, storage, ui);
        ui.showMessage("Added: " + description + " by " + deadline);
        ui.showMessage(listCount(taskList.getSize()));
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
    
    private void saveList(TaskList taskList, Storage storage, Ui ui) throws TwinBotException {
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new TwinBotException("Error saving tasks: " + e.getMessage());
        }
    }
}
