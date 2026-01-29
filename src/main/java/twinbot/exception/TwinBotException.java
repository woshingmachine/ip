package twinbot.exception;

/**
 * Represents exceptions specific to TwinBot operations.
 */
public class TwinBotException extends Exception {
    /**
     * Constructs a TwinBotException with a message.
     *
     * @param message the error message
     */
    public TwinBotException(String message) {
        super(message);
    }
}
