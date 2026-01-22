public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return this.getStatusIcon()
                + "[Event] "
                + this.getDescription()
                + " | Start date: "
                + this.getStart()
                + " | End date: "
                + this.getEnd();
    }
}
