package twinbot.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String NAME = "TWINBOT";
    private static final String LINE = "------------------------------";
    private Scanner scanner;
    private String lastMessage;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.lastMessage = "";
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("What's up twin, I'm");
        System.out.println(NAME);
        System.out.println("What can I do for you twin?");
        showLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        String message = "Bye. Hope to see you again soon, twin!";
        System.out.println(message);
        this.lastMessage = message;
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays error messages.
     *
     * @param messages the error messages to display
     */
    public void showError(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i]);
            sb.append(messages[i]);
            if (i < messages.length - 1) {
                sb.append("\n");
            }
        }
        this.lastMessage = sb.toString();
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Displays a message and stores it as the last message.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
        this.lastMessage = message;
    }

    /**
     * Returns the last message displayed.
     *
     * @return the last message
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Reads a command from the user.
     *
     * @return the user's input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}
