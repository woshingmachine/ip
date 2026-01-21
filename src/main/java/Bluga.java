import java.util.ArrayList;
import java.util.Scanner;

public class Bluga {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        String name = "______ _                   \n" +
                "| ___ \\ |                  \n" +
                "| |_/ / |_   _  __ _  __ _ \n" +
                "| ___ \\ | | | |/ _` |/ _` |\n" +
                "| |_/ / | |_| | (_| | (_| |\n" +
                "\\____/|_|\\__,_|\\__, |\\__,_|\n" +
                "                __/ |      \n" +
                "               |___/       \n";
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
            // Handle bye case
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(lines + "See you soon twin.\n" + lines);
                break;
            }
            // Handle list case
            else if (input.equalsIgnoreCase("list")){
                System.out.println(lines + "Here are your tasks, twin:\n");
                Bluga.printList(list);
                System.out.println(lines);
            }
            // Handle mark and unmark cases with error handling
            else if (input.toLowerCase().startsWith("mark") || input.toLowerCase().startsWith("unmark")) {
                boolean isMark = input.toLowerCase().startsWith("mark");
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1; // -1 because of 1 indexing
                    if (index >= 0 && index < list.size()) {
                        if (isMark) {
                            list.get(index).mark();
                        } else {
                            list.get(index).unmark();
                        }
                        String action = isMark ? "marked" : "unmarked";
                        printListWithHeader("Nice, twin! I've " + action + " the item.\n", list, lines);
                    } else {
                        System.out.println("Invalid Number.\n");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number after mark.\n");
                }
            }
            // Handle input case
            else {
                list.add(new Task(input));
                System.out.println(lines + "Added: " + input + "\n" + lines);
            }
        }
    }

    public static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1
                    + ". "
                    + list.get(i).getStatusIcon()
                    + list.get(i).getDescription()
                    + "\n");
        }
    }

    public static void printListWithHeader(String header, ArrayList<Task> list, String lines) {
        System.out.println(lines + header);
        Bluga.printList(list);
        System.out.println(lines);
    }
}
