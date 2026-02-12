package twinbot.task;

import java.util.ArrayList;

import twinbot.exception.TwinBotException;

/**
 * Represents a generic task with a description, status, and type.
 * This is the base class for ToDo, Deadline, and Event tasks.
 */
public class Task {
    private String description;
    private TaskStatus status;
    private TaskType type;
    private ArrayList<String> tags;

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description the task description
     * @param type        the type of task (ToDo, Deadline, or Event)
     */
    public Task(String description, TaskType type) {
        assert description != null : "Task description cannot be null";
        assert !description.trim().isEmpty() : "Task description cannot be empty";
        assert type != null : "Task type cannot be null";
        this.description = description;
        this.type = type;
        this.status = TaskStatus.TODO;
        this.tags = new ArrayList<>();
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
     * Updates the description of this task.
     *
     * @param description the new task description
     */
    public void setDescription(String description) {
        assert description != null : "Task description cannot be null";
        assert !description.trim().isEmpty() : "Task description cannot be empty";
        this.description = description;
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
     * Adds a tag to this task.
     *
     * @param tag the tag text (with or without leading #)
     * @throws twinbot.exception.TwinBotException if tag is invalid
     */
    public void addTag(String tag) throws TwinBotException {
        String normalized = normalizeTag(tag);
        if (!tags.contains(normalized)) {
            tags.add(normalized);
        }
    }

    /**
     * Removes a tag from this task.
     *
     * @param tag the tag text (with or without leading #)
     * @throws twinbot.exception.TwinBotException if tag is invalid
     */
    public void removeTag(String tag) throws TwinBotException {
        String normalized = normalizeTag(tag);
        tags.remove(normalized);
    }

    /**
     * Returns tags attached to this task.
     *
     * @return list of tags
     */
    public ArrayList<String> getTags() {
        return new ArrayList<>(tags);
    }

    protected String toFileStringCore() {
        return type.getCode() + " | " + (isDone() ? "1" : "0") + " | " + description;
    }

    protected String formatTagsForStorage() {
        if (tags.isEmpty()) {
            return "";
        }
        return " | tags:" + String.join(",", tags);
    }

    protected String formatTagsForDisplay() {
        if (tags.isEmpty()) {
            return "";
        }
        return " (tags: " + String.join(", ", tags) + ")";
    }

    /**
     * Converts the task into a string suitable for saving to a file.
     * Format: [Type code] | [0 or 1 done status] | [description]
     *
     * @return String representation of the task for storage
     */
    public String toFileString() {
        return toFileStringCore() + formatTagsForStorage();
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return formatted String containing status and description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription() + formatTagsForDisplay();
    }

    private String normalizeTag(String tag) throws TwinBotException {
        if (tag == null) {
            throw new TwinBotException("Twin, tag cannot be null.");
        }
        String trimmed = tag.trim();
        if (trimmed.startsWith("#")) {
            trimmed = trimmed.substring(1).trim();
        }
        if (trimmed.isEmpty()) {
            throw new TwinBotException("Twin, tag cannot be empty.");
        }
        return trimmed.toLowerCase();
    }
}
