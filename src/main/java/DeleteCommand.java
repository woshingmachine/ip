import java.io.IOException;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(String arguments) throws TwinBotException {
        this.index = Parser.parseTaskIndex(arguments);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        if (index >= taskList.getSize()) {
            throw new TwinBotException("Invalid Number.");
        }
        Task task = taskList.removeTask(index);
        saveList(taskList, storage, ui);
        ui.showMessage("Ok twin, I've removed this task: " + task);
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
