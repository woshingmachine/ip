import java.io.IOException;
import java.util.ArrayList;

/**
 * TwinBot is a task management chatbot application.
 * It allows users to create, manage, and track tasks including to-dos, deadlines, and events.
 */
public class TwinBot {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/twinbot.txt");
        ArrayList<Task> list;

        try {
            list = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            list = new ArrayList<>();
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
                printList(list, ui);
                ui.showLine();
                break;
            case "mark":
            case "unmark":
                handleMarkUnmark(command, arguments, list, storage, ui);
                break;
            case "todo":
                handleTodo(arguments, list, storage, ui);
                break;
            case "deadline":
                handleDeadline(arguments, list, storage, ui);
                break;
            case "event":
                handleEvent(arguments, list, storage, ui);
                break;
            case "delete":
                handleDelete(arguments, list, storage, ui);
                break;
            case "help":
                ui.showMessage(
                        "- todo <task>\n" +
                        "- deadline <task> /by <date>\n" +
                        "- event <task> /from <start> to <end>\n" +
                        "- bye\n- list\n- mark <number>\n- unmark <number>\n" +
                        "- delete <number>\n"
                );
                break;
            default:
                ui.showError("Twin, icl idk what that means. Type help for list of commands.");
            }
        }
    }

    private static void handleTodo(String arguments, ArrayList<Task> list, Storage storage, Ui ui) {
        try {
            String description = arguments.trim();
            if (description.isEmpty()) {
                throw new TwinBotException("Twin, use 'todo task'\n");
            }
            ToDo toDo = new ToDo(description);
            list.add(toDo);
            saveList(list, storage, ui);
            ui.showLine();
            ui.showMessage("Added: " + toDo.getDescription() + "\n" + listCount(list.size()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleDeadline(String arguments, ArrayList<Task> list, Storage storage, Ui ui) {
        try {
            String[] parts = Parser.parseDeadline(arguments);
            String description = parts[0];
            String deadline = parts[1];
            
            Deadline deadlineTask = new Deadline(description, deadline);
            list.add(deadlineTask);
            saveList(list, storage, ui);
            
            ui.showLine();
            ui.showMessage("Added: " + description + " by " + deadline + "\n" + listCount(list.size()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleEvent(String arguments, ArrayList<Task> list, Storage storage, Ui ui) {
        try {
            String[] parts = Parser.parseEvent(arguments);
            String description = parts[0];
            String start = parts[1];
            String end = parts[2];
            
            Event event = new Event(description, start, end);
            list.add(event);
            saveList(list, storage, ui);
            
            ui.showLine();
            ui.showMessage("Added: " + description + " from " + start + " to " + end + "\n" + listCount(list.size()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleDelete(String arguments, ArrayList<Task> list, Storage storage, Ui ui) {
        try {
            int index = Parser.parseTaskIndex(arguments);
            if (index >= list.size()) {
                throw new TwinBotException("Invalid Number.\n");
            }
            Task task = list.get(index);
            list.remove(index);
            saveList(list, storage, ui);
            ui.showLine();
            ui.showMessage("Ok twin, I've removed this task: " + task + "\n" + listCount(list.size()));
            ui.showLine();
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleMarkUnmark(
            String command,
            String arguments,
            ArrayList<Task> list,
            Storage storage,
            Ui ui
    ) {
        boolean isMark = command.equalsIgnoreCase("mark");
        try {
            int index = Parser.parseTaskIndex(arguments);
            if (index >= list.size()) {
                throw new TwinBotException("Invalid Number.\n");
            }
            if (isMark) {
                list.get(index).mark();
            } else {
                list.get(index).unmark();
            }
            String action = isMark ? "marked" : "unmarked";
            printListWithHeader("Nice, twin! I've " + action + " the item.\n", list, ui);
            saveList(list, storage, ui);
        } catch (TwinBotException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Prints all tasks in the list with their index numbers.
     *
     * @param list the list of tasks to print
     * @param ui the Ui object for output
     */
    public static void printList(ArrayList<Task> list, Ui ui) {
        for (int i = 0; i < list.size(); i++) {
            ui.showMessage(i + 1
                    + ". "
                    + list.get(i).toString());
        }
    }

    /**
     * Prints all tasks in the list with a header message.
     *
     * @param header the header message to print
     * @param list the list of tasks to print
     * @param ui the Ui object for output
     */
    public static void printListWithHeader(String header, ArrayList<Task> list, Ui ui) {
        ui.showLine();
        ui.showMessage(header);
        printList(list, ui);
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
     * @param list the task list to save
     * @param storage the Storage object to use
     * @param ui the Ui object for error messages
     */
    public static void saveList(ArrayList<Task> list, Storage storage, Ui ui) {
        try {
            storage.save(list);
        } catch (IOException e) {
            ui.showError("Error saving tasks: " + e.getMessage());
        }
    }

}
