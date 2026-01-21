public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + "[ToDo] " + this.getDescription();
    }
}
