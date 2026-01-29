package twinbot.task;

/**
 * Represents the type of a task.
 * Used to distinguish ToDo, Deadline, and Event tasks.
 */
public enum TaskType {
    /** Task type for simple to-do tasks. */
    TODO("T"),
    /** Task type for deadline tasks. */
    DEADLINE("D"),
    /** Task type for event tasks. */
    EVENT("E");

    private final String code;

    /**
     * Constructs a TaskType with the given code.
     *
     * @param code the single character code for file storage
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the code used for file storage (T, D or E).
     * 
     * @return the code used for file storage
     */
    public String getCode() {
        return this.code;
    }
}
