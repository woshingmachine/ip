package twinbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import twinbot.exception.TwinBotException;

/**
 * JUnit test class for Deadline.
 */
public class DeadlineTest {

    @Test
    public void constructor_validInput_success() throws TwinBotException {
        Deadline deadline = new Deadline("return book", "2024-12-31 18:00");
        assertEquals("return book", deadline.getDescription());
        assertFalse(deadline.isDone());
    }

    @Test
    public void constructor_invalidDateFormat_throwsException() {
        assertThrows(TwinBotException.class, () -> new Deadline("return book", "invalid-date"));
    }

    @Test
    public void toString_unmarkedDeadline_correctFormat() throws TwinBotException {
        Deadline deadline = new Deadline("return book", "2024-12-31 18:00");
        assertTrue(deadline.toString().contains("[ ][D] return book"));
        assertTrue(deadline.toString().contains("| By:"));
    }

    @Test
    public void toString_markedDeadline_correctFormat() throws TwinBotException {
        Deadline deadline = new Deadline("return book", "2024-12-31 18:00");
        deadline.mark();
        assertTrue(deadline.toString().contains("[X][D] return book"));
    }

    @Test
    public void mark_deadlineTask_marksAsDone() throws TwinBotException {
        Deadline deadline = new Deadline("return book", "2024-12-31 18:00");
        deadline.mark();
        assertTrue(deadline.isDone());
    }
}
