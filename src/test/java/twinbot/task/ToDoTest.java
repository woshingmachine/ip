package twinbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import twinbot.exception.TwinBotException;

/**
 * JUnit test class for ToDo.
 */
public class ToDoTest {

    @Test
    public void constructor_validDescription_success() throws TwinBotException {
        ToDo todo = new ToDo("read book");
        assertEquals("read book", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void mark_todoTask_marksAsDone() throws TwinBotException {
        ToDo todo = new ToDo("read book");
        todo.mark();
        assertTrue(todo.isDone());
        assertEquals("[X]", todo.getStatusIcon());
    }

    @Test
    public void unmark_markedTask_unmarksTask() throws TwinBotException {
        ToDo todo = new ToDo("read book");
        todo.mark();
        todo.unmark();
        assertFalse(todo.isDone());
        assertEquals("[ ]", todo.getStatusIcon());
    }

    @Test
    public void toString_unmarkedTask_correctFormat() throws TwinBotException {
        ToDo todo = new ToDo("read book");
        assertEquals("[ ][T] read book", todo.toString());
    }

    @Test
    public void toString_markedTask_correctFormat() throws TwinBotException {
        ToDo todo = new ToDo("read book");
        todo.mark();
        assertEquals("[X][T] read book", todo.toString());
    }
}
