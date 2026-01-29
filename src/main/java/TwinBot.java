import java.io.IOException;

/**
 * TwinBot is a task management chatbot application.
 * It allows users to create, manage, and track tasks including to-dos, deadlines, and events.
 */
public class TwinBot {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/twinbot.txt");
        TaskList taskList;

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            ParsedCommand parsed = Parser.parse(input);
            String command = parsed.getCommand();
            String arguments = parsed.getArguments();

            switch (command) {
            case "bye":
                ui.showLine();
                ui.showGoodbye();
                ui.showLine();
                ui.close();
                return;
            case "list":
                ui.showLine();
                ui.showMessage("Here are your tasks, twin:\n");
                printList(taskList, ui);
                ui.showLine();
                break;
            case "mark":
            case "unmark":
                handleMarkUnmark(command, arguments, taskList, storage, ui);
                break;
            case "todo":
                handleTodo(arguments, taskList, storage, ui);
                break;
            case "deadline":
                handleDeadline(arguments, taskList, storage, ui);
                break;
            case "event":
                handleEvent(arguments, taskList, storage, ui);
                break;
            case "delete":
                handleDelete(arguments, taskList, storage, ui);
                break;
            case "help":
                ui.showMessage(
                        "- todo <task>\n"
                        + "- deadline <task> /by <date>\n"
                        + "- event <task> /from <start> to <end>\n"
                        + "- bye\n- list\n- mark <number>\n- unmark <number>\n"
                        + "- delete <number>\n"
                );
                break;
            default:
                ui.showError("Twin, icl idk what that means. Type help for list of commands.");
            }
        }
    }

    private static void handleTodo(String arguments, TaskList taskList, Storage storage, Ui ui) {
        try {
            String description = arguments.trim();
            if (description.isEmpty()) {
                throw new TwinBotException("Twin, use 'todo task'\n");
            }
            ToDo toDo = new ToDo(description);
            taskList.addTask(toDo);
            saveList(taskList, storage, ui);
            ui.showLine();
            ui.showMessage("Added: " + toDo.getDescription() + "\n" + listCount(taskList.getSize()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleDeadline(String arguments, TaskList taskList, Storage storage, Ui ui) {
        try {
            String[] parts = Parser.parseDeadline(arguments);
            String description = parts[0];
            String deadline = parts[1];
            Deadline deadlineTask = new Deadline(description, deadline);
            taskList.addTask(deadlineTask);
            saveList(taskList, storage, ui);
            ui.showLine();
            ui.showMessage("Added: " + description + " by " + deadline + "\n" + listCount(taskList.getSize()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleEvent(String arguments, TaskList taskList, Storage storage, Ui ui) {
        try {
            String[] parts = Parser.parseEvent(arguments);
            String description = parts[0];
            String start = parts[1];
            String end = parts[2];
            Event event = new Event(description, start, end);
            taskList.addTask(event);
            saveList(taskList, storage, ui);
            ui.showLine();
            ui.showMessage("Added: " + description + " from " + start + " to " + end + "\n" + listCount(taskList.getSize()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleDelete(String arguments, TaskList taskList, Storage storage, Ui ui) {
        try {
            int index = Parser.parseTaskIndex(arguments);
            if (index >= taskList.getSize()) {
                throw new TwinBotException("Invalid Number.\n");
            }
            Task task = taskList.removeTask(index);
            saveList(taskList, storage, ui);
            ui.showLine();
            ui.showMessage("Ok twin, I've removed this task: " + task + "\n" + listCount(taskList.getSize()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleMarkUnmark(
            String command,
            String arguments,
            TaskList taskList,
            Storage storage,
            Ui ui
    ) {
        boolean isMark = command.equalsIgnoreCase("mark");
        try {
            int index = Parser.parseTaskIndex(arguments);
            if (index >= taskList.getSize()) {
                throw new TwinBotException("Invalid Number.\n");
            }
            if (isMark) {
                taskList.getTask(index).mark();
            } else {
                taskList.getTask(index).unmark();
            }
            String action = isMark ? "marked" : "unmarked";
            printListWithHeader("Nice, twin! I've " + action + " the item.\n", taskList, ui);
            saveList(taskList, storage, ui);
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Prints all tasks in the list with their index numbers.
     *
     * @param taskList the TaskList to print
     * @param ui the Ui object for output
     */
    public static void printList(TaskList taskList, Ui ui) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }

    /**
     * Prints all tasks in the list with a header message.
     *
     * @param header the header message to print
     * @param taskList the TaskList to print
     * @param ui the Ui object for output
     */
    public static void printListWithHeader(String header, TaskList taskList, Ui ui) {
        ui.showLine();
        ui.showMessage(header);
        printList(taskList, ui);
        ui.showLine();
    }

    /**
     * Returns a message showing the current number of tasks.
     *
     * @param count the total number of tasks
     * @return formatted message with task count
     */
    public static String listCount(int count) {
        return "\nYou now have " + count + " tasks in your list, twin\n";
    }

    /**
     * Saves the current list of tasks using the provided Storage object.
     * Prints an error if saving fails.
     *
     * @param taskList the TaskList to save
     * @param storage the Storage object to use
     * @param ui the Ui object for error messages
     */
    public static void saveList(TaskList taskList, Storage storage, Ui ui) {
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            ui.showError("Error saving tasks: " + e.getMessage());
        }
    }
}
