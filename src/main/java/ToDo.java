/**
 * Represents a to-do task.
 * Extends Task with no additional fields.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the to-do task for display.
     *
     * @return formatted String containing status, type, and description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + "[T] " + this.getDescription();
    }
}
