package twinbot;

import java.io.IOException;

import twinbot.command.Command;
import twinbot.exception.TwinBotException;
import twinbot.parser.Parser;
import twinbot.storage.Storage;
import twinbot.storage.TaskList;
import twinbot.ui.Ui;

/**
 * TwinBot is a task management chatbot application.
 * It allows users to create, manage, and track tasks including to-dos,
 * deadlines, and events.
 */
public class TwinBot {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for TwinBot.
     *
     * @param filePath the path to the data file for storing tasks
     */
    public TwinBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of the TwinBot application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (TwinBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Main method to start the TwinBot application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new TwinBot("./data/twinbot.txt").run();
    }
}
