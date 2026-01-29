package twinbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import twinbot.exception.TwinBotException;
import twinbot.task.Deadline;
import twinbot.task.Task;
import twinbot.task.ToDo;

/**
 * JUnit test class for TaskList.
 */
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void constructor_newTaskList_isEmpty() {
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void addTask_singleTask_success() throws TwinBotException {
        Task task = new ToDo("read book");
        taskList.addTask(task);

        assertEquals(1, taskList.getTasks().size());
        assertFalse(taskList.isEmpty());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void addTask_multipleTasks_correctOrder() throws TwinBotException {
        Task task1 = new ToDo("task 1");
        Task task2 = new ToDo("task 2");
        Task task3 = new ToDo("task 3");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        assertEquals(3, taskList.getTasks().size());
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
        assertEquals(task3, taskList.getTask(2));
    }

    @Test
    public void removeTask_validIndex_success() throws TwinBotException {
        Task task1 = new ToDo("task 1");
        Task task2 = new ToDo("task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);

        Task removed = taskList.removeTask(0);

        assertEquals(task1, removed);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    public void removeTask_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.removeTask(0));
    }

    @Test
    public void getTask_validIndex_returnsTask() throws TwinBotException {
        Task task = new Deadline("return book", "2024-12-31 18:00");
        taskList.addTask(task);

        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void getTask_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(0));
    }

    @Test
    public void isEmpty_withTasks_returnsFalse() throws TwinBotException {
        taskList.addTask(new ToDo("task"));
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void isEmpty_afterRemovingAllTasks_returnsTrue() throws TwinBotException {
        taskList.addTask(new ToDo("task"));
        taskList.removeTask(0);
        assertTrue(taskList.isEmpty());
    }
}
