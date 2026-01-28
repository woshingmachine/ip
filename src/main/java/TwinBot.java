import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TwinBot is a task management chatbot application.
 * It allows users to create, manage, and track tasks including to-dos, deadlines, and events.
 */
public class TwinBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/twinbot.txt");
        ArrayList<Task> list;

        try {
            list = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks, starting with empty list.");
            list = new ArrayList<>();
        }

        String name = "TWINBOT\n";
        String lines = "------------------------------\n";

        System.out.println(
                lines
                + "What's up twin, I'm\n"
                + name
                + lines
                + "What can I do for you twin?\n"
                + lines);

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
            case "bye":
                System.out.println(lines + "See you soon twin.\n" + lines);
                return;
            case "list":
                System.out.println(lines + "Here are your tasks, twin:\n");
                printList(list);
                System.out.println(lines);
                break;
            case "mark":
            case "unmark":
                handleMarkUnmark(command, parts, list, lines, storage);
                break;
            case "todo":
                try {
                    String toDoDescription = parts[1].trim();
                    // Check if description is empty
                    if (toDoDescription.isEmpty()) throw new Exception();
                    ToDo toDo = new ToDo(toDoDescription);
                    list.add(toDo);
                    saveList(list, storage);
                    System.out.println(lines + "Added: " + toDo.getDescription() + "\n" + listCount(list.size()) + lines);
                } catch (Exception e) {
                    System.out.println("Twin, use 'todo task'\n");
                }
                break;
            case "deadline":
                try {
                    String[] deadlineParts = parts[1].split("/by", 2);
                    String deadlineDescription = deadlineParts[0].trim();
                    String deadlineDate = deadlineParts[1].trim();

                    // Check if any of the args are empty
                    if (deadlineDescription.isEmpty() || deadlineDate.isEmpty()) throw new Exception();

                    Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
                    list.add(deadline);
                    saveList(list, storage);

                    System.out.println(lines
                            + "Added: "
                            + deadlineDescription
                            + " by "
                            + deadlineDate
                            + "\n"
                            + listCount(list.size())
                            + lines
                    );
                } catch (Exception e) {
                    System.out.println("Twin, use 'deadline task /by date'\n");
                }
                break;
            case "event":
                try {
                    String[] eventParts = parts[1].split("/from|/to", 3);
                    String eventDescription = eventParts[0].trim();
                    String start = eventParts[1].trim();
                    String end = eventParts[2].trim();

                    // Check if any of the args are empty
                    if (eventDescription.isEmpty() || start.isEmpty() || end.isEmpty()) throw new Exception();

                    Event event = new Event(eventDescription, start, end);
                    list.add(event);
                    saveList(list, storage);

                    System.out.println(lines
                            + "Added: "
                            + eventDescription
                            + " from "
                            + start
                            + " to "
                            + end
                            + "\n"
                            + listCount(list.size())
                            + lines
                    );
                } catch (Exception e) {
                    System.out.println("Twin, use 'event task /from start /to end.'\n");
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(parts[1]);
                    Task task = list.get(index - 1);
                    list.remove(index - 1);
                    saveList(list, storage);
                    System.out.println(
                            lines + "Ok twin, I've removed this task: "
                            + task + "\n" + listCount(list.size()) + lines
                    );
                } catch (Exception e) {
                    System.out.println("Twin, use 'delete number'.\n");
                }
                break;
            // user inputs help
            case "help":
                System.out.println(
                        "- todo <task>\n" +
                        "- deadline <task> /by <date>\n" +
                        "- event <task> /from <start> to <end>\n" +
                        "- bye\n- list\n- mark <number>\n- unmark <number>\n" +
                        "- delete <number>\n"
                );
                break;
            // Any other input is considered invalid
            default:
                System.out.println("Twin, icl idk what that means. Type help for list of commands.");
            }
        }
    }

    /**
     * Handles marking and unmarking tasks as done or not done.
     *
     * @param command the command ("mark" or "unmark")
     * @param parts the parsed user input
     * @param list the list of tasks
     * @param lines the string separator for formatting output
     * @param storage the Storage object to save changes
     */
    public static void handleMarkUnmark(
            String command,
            String[] parts,
            ArrayList<Task> list,
            String lines,
            Storage storage
    ) {
        boolean isMark = parts[0].equalsIgnoreCase("mark");
            try {
                int index = Integer.parseInt(parts[1]) - 1; // -1 because of 1 indexing
                if (index >= 0 && index < list.size()) {
                    if (isMark) {
                        list.get(index).mark();
                    } else {
                        list.get(index).unmark();
                    }
                    String action = isMark ? "marked" : "unmarked";
                    printListWithHeader("Nice, twin! I've " + action + " the item.\n", list, lines);
                    saveList(list, storage);
                } else {
                    System.out.println("Invalid Number.\n");
                }
            } catch (Exception e) {
                    System.out.println("Please enter a valid number after mark.\n");
            }
    }

    /**
     * Prints all tasks in the list with their index numbers.
     *
     * @param list the list of tasks to print
     */
    public static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1
                    + ". "
                    + list.get(i).toString());
        }
    }

    /**
     * Prints all tasks in the list with a header message.
     *
     * @param header the header message to print
     * @param list the list of tasks to print
     * @param lines the string separator for formatting output
     */
    public static void printListWithHeader(String header, ArrayList<Task> list, String lines) {
        System.out.println(lines + header);
        printList(list);
        System.out.println(lines);
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
     */
    public static void saveList(ArrayList<Task> list, Storage storage) {
        try {
            storage.save(list);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
