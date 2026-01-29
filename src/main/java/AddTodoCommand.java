import java.io.IOException;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private String description;
    
    public AddTodoCommand(String description) throws TwinBotException {
        if (description.isEmpty()) {
            throw new TwinBotException("Twin, use 'todo task'");
        }
        this.description = description;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TwinBotException {
        ToDo toDo = new ToDo(description);
        taskList.addTask(toDo);
        saveList(taskList, storage, ui);
        ui.showMessage("Added: " + toDo.getDescription());
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
