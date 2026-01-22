public class Task {
    private String description;
    private TaskStatus status;
    private TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type =type;
        this.status = TaskStatus.TODO;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return status == TaskStatus.DONE;
    }

    public void mark() {
        this.status = TaskStatus.DONE;
    }

    public void unmark() {
        this.status = TaskStatus.TODO;
    }

    public String getStatusIcon() {
        return (this.isDone() ? "[X]" : "[ ]");
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
