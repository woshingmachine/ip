public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return this.getStatusIcon()
                + "[Deadline] "
                + this.getDescription()
                + " | By: "
                + this.getDeadline();
    }
}
