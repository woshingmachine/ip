/**
 * Represents a task with a deadline.
 * Extends Task with an additional deadline field.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description the task description
     * @param deadline the deadline for this task
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline for this task.
     *
     * @return the deadline as a String
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Converts the deadline task into a string suitable for saving to a file.
     *
     * @return String representation of the deadline task for storage
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + deadline;
    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return formatted String containing status, type, description, and deadline
     */
    @Override
    public String toString() {
        return this.getStatusIcon()
                + "[Deadline] "
                + this.getDescription()
                + " | By: "
                + this.getDeadline();
    }
}
