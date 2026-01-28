/**
 * Represents a generic task with a description, status, and type.
 * This is the base class for ToDo, Deadline, and Event tasks.
 */
public class Task {
    private String description;
    private TaskStatus status;
    private TaskType type;

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description the task description
     * @param type the type of task (ToDo, Deadline, or Event)
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type =type;
        this.status = TaskStatus.TODO;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if this task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return status == TaskStatus.DONE;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.status = TaskStatus.TODO;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "[X]" if done, "[ ]" if not done
     */
    public String getStatusIcon() {
        return (this.isDone() ? "[X]" : "[ ]");
    }

    /**
     * Converts the task into a string suitable for saving to a file.
     * Format: [Type code] | [0 or 1 done status] | [description]
     *
     * @return String representation of the task for storage
     */
    public String toFileString() {
        return type.getCode() + " | " + (isDone() ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return formatted String containing status and description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
