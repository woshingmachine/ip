/**
 * Command to display all tasks.
 */
public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Here are your tasks, twin:");
        printList(taskList, ui);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
    
    private void printList(TaskList taskList, Ui ui) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }
}
