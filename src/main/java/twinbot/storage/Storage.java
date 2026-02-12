package twinbot.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import twinbot.task.Deadline;
import twinbot.task.Event;
import twinbot.task.Task;
import twinbot.task.ToDo;

/**
 * Handles loading and saving of tasks to the hard disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the data file
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the data file.
     * Creates the file and parent directories if they do not exist.
     *
     * @return an ArrayList of tasks loaded from storage
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<Task> load() throws IOException {

        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String typeCode = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;

            try {
                switch (typeCode) {
                case "T": // ToDo
                    task = new ToDo(description);
                    break;
                case "D": // Deadline
                    if (parts.length < 4) {
                        continue; // skip malformed line
                    }
                    String by = parts[3];
                    task = new Deadline(description, by);
                    break;
                case "E": // Event
                    if (parts.length < 5) {
                        continue; // skip malformed line
                    }
                    String start = parts[3];
                    String end = parts[4];
                    task = new Event(description, start, end);
                    break;
                default:
                    continue;
                }
            } catch (Exception e) {
                // Skip tasks with invalid data
                System.out.println("Skipping corrupted task: " + line);
                continue;
            }

            if (task != null && isDone) {
                task.mark();
            }

            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the data file.
     *
     * @param tasks the lists of tasks to be saved
     * @throws IOException if an I/O error occurs
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        assert tasks != null : "Task list cannot be null";
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toFileString()).append("\n");
        }

        Files.write(filePath, sb.toString().getBytes());
    }

}
