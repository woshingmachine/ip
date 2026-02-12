package twinbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import twinbot.exception.TwinBotException;

/**
 * Represents an event task with a start and end time.
 * Extends Task with additional start and end time fields.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with the given description, start time, and end
     * time.
     * Constructs an Event task with the given description, start time, and end
     * time.
     *
     * @param description the task description
     * @param startString the start time of the event as a string
     * @param endString   the end time of the event as a string
     * @param endString   the end time of the event as a string
     * @throws TwinBotException if either date string cannot be parsed
     */
    public Event(String description, String startString, String endString) throws TwinBotException {
        super(description, TaskType.EVENT);
        assert startString != null : "Start time cannot be null";
        assert endString != null : "End time cannot be null";
        assert !startString.trim().isEmpty() : "Start time cannot be empty";
        assert !endString.trim().isEmpty() : "End time cannot be empty";
        this.start = parseDateTime(startString);
        this.end = parseDateTime(endString);
        if (this.start == null) {
            throw new TwinBotException("Invalid start date format. Use: yyyy-MM-dd HH:mm or d/M/yyyy HHmm");
        }
        if (this.end == null) {
            throw new TwinBotException("Invalid end date format. Use: yyyy-MM-dd HH:mm or d/M/yyyy HHmm");
        }
    }

    /**
     * Parses a date/time string in multiple formats.
     * Supported formats:
     * - yyyy-MM-dd'T'HH:mm (e.g., 2019-12-02T18:00) - ISO format from storage
     * - yyyy-MM-dd HH:mm (e.g., 2019-12-02 18:00)
     * - d/M/yyyy HHmm (e.g., 2/12/2019 1800)
     *
     * @param dateString the date string to parse
     * @return parsed LocalDateTime, or null if parsing fails
     */
    private LocalDateTime parseDateTime(String dateString) {
        String trimmed = dateString.trim();
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
                // Try next format
            }
        }

        return null;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time as LocalDateTime
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time as LocalDateTime
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Converts the event task into a string suitable for saving to a file.
     *
     * @return String representation of the event task for storage
     */
    @Override
    public String toFileString() {
        return toFileStringCore() + " | " + start + " | " + end + formatTagsForStorage();
    }

    /**
     * Returns a string representation of the event task for display.
     *
     * @return formatted String containing status, type, description, start
     *         date/time, and end date/time
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return this.getStatusIcon()
                + "[E] "
                + this.getDescription()
                + " | from: "
                + this.start.format(formatter)
                + " to: "
                + this.end.format(formatter)
                + formatTagsForDisplay();
    }
}
