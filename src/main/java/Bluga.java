import java.util.ArrayList;
import java.util.Scanner;

public class Bluga {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
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
                + "Hello, I'm\n"
                + name
                + lines
                + "What can I do for you?\n"
                + lines);

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(lines + "    Bye!\n" + lines);
                break;
            } else if (input.equalsIgnoreCase("list")){
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i) + "\n");
                }
            } else {
                list.add(input);
                System.out.println(lines + "    Added: " + input + "\n" + lines);
            }
        }
    }
}
