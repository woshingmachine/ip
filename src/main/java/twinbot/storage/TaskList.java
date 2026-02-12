package twinbot.storage;

import java.util.ArrayList;

import twinbot.task.Task;

/**
 * Manages the list of tasks.
 * Provides operations to add, remove, retrieve, and query tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add cannot be null";
        tasks.add(task);
    }

    /**
     * Removes a task at the given index.
     *
     * @param index the 0-based index of the task to remove
     * @return the removed task
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task removeTask(int index) {
        assert index >= 0 : "Index must be non-negative";
        return tasks.remove(index);
    }

    /**
     * Gets a task at the given index.
     *
     * @param index the 0-based index
     * @return the task at the index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index must be non-negative";
        return tasks.get(index);
    }

    /**
     * Gets the total number of tasks.
     *
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets all tasks.
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
