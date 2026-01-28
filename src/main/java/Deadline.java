import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Extends Task with an additional deadline field.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description the task description
     * @param deadlineString the string deadline for this task
     */
    public Deadline(String description, String deadlineString) {
        super(description, TaskType.DEADLINE);
        this.deadline = parseDateTime(deadlineString);
    }

    /**
     * Parses a date/time string in multiple formats.
     * Supported formats:
     * - yyyy-MM-dd HH:mm (e.g., 2019-12-02 18:00)
     * - d/M/yyyy HHmm (e.g., 2/12/2019 1800)
     * - d/M/yyyy (e.g., 2/12/2019) - defaults to 00:00
     *
     * @param dateString the date string to parse
     * @return parsed LocalDateTime, or null if parsing fails
     */
    private LocalDateTime parseDateTime(String dateString) {
        String trimmed = dateString.trim();
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
                // Try next format
            }
        }

        System.out.println("Invalid date/time format! Use yyyy-MM-dd HH:mm or d/M/yyyy HHmm, twin.");
        return null;
    }

    /**
     * Returns the deadline for this task in storage format.
     *
     * @return the deadline as a String in yyyy-MM-dd HH:mm format
     */
    public String getDeadline() {
        if (this.deadline == null) return "";
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
        if (this.deadline == null) {
            return this.getStatusIcon() + "[D] " + this.getDescription() + " | By: (invalid date)";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return this.getStatusIcon()
                + "[D] "
                + this.getDescription()
                + " | By: "
                + this.deadline.format(formatter);
    }
}
