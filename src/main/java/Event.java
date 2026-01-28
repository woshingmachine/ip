/**
 * Represents an event task with a start and end time.
 * Extends Task with additional start and end time fields.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the task description
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time as a String
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time as a String
     */
    public String getEnd() {
        return this.end;
    }

    /**
     * Converts the event task into a string suitable for saving to a file.
     *
     * @return String representation of the event task for storage
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of the event task for display.
     *
     * @return formatted String containing status, type, description, start date, and end date
     */
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
