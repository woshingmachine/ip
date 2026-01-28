/**
 * Represents the type of a task.
 * Used to distinguish ToDo, Deadline, and Event tasks.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String code;

    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the code used for file storage (T, D or E).
     * @return the code used for file storage
     */
    public String getCode() {
        return this.code;
    }
}
