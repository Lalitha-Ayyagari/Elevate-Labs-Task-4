import java.io.*;
import java.util.*;

public class NotesManager {
    private static final String NOTES_FILE = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    createNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    updateNote();
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Notes Manager ===");
        System.out.println("1. Create a new note");
        System.out.println("2. Read all notes");
        System.out.println("3. Update a note");
        System.out.println("4. Delete a note");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void createNote() {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine();
        System.out.print("Enter note content: ");
        String content = scanner.nextLine();
        
        try (FileWriter writer = new FileWriter(NOTES_FILE, true)) {
            writer.write("=== " + title + " ===\n");
            writer.write(content + "\n");
            writer.write("---\n");
            System.out.println("Note created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void readNotes() {
        try (FileReader reader = new FileReader(NOTES_FILE)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int noteCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                noteCount++;
            }
            if (noteCount == 0) {
                System.out.println("No notes found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Create a note first.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void updateNote() {
        System.out.print("Enter the title of the note to update: ");
        String searchTitle = scanner.nextLine();
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            StringBuilder currentNote = new StringBuilder();
            String currentTitle = null;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("=== ") && line.endsWith(" ===")) {
                    if (currentTitle != null && currentTitle.equals(searchTitle)) {
                        found = true;
                        System.out.print("Enter new content for '" + searchTitle + "': ");
                        String newContent = scanner.nextLine();
                        lines.add("=== " + searchTitle + " ===");
                        lines.add(newContent);
                        lines.add("---");
                        // Skip the old content
                        while ((line = reader.readLine()) != null && !line.equals("---")) {
                            continue;
                        }
                        continue;
                    }
                    currentTitle = line.substring(4, line.length() - 4);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Note with title '" + searchTitle + "' not found.");
            return;
        }

        try (FileWriter writer = new FileWriter(NOTES_FILE)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("Note updated successfully!");
        } catch (IOException e) {
            System.out.println("Error updating note: " + e.getMessage());
        }
    }

    private static void deleteNote() {
        System.out.print("Enter the title of the note to delete: ");
        String searchTitle = scanner.nextLine();
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            String currentTitle = null;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("=== ") && line.endsWith(" ===")) {
                    currentTitle = line.substring(4, line.length() - 4);
                    if (currentTitle.equals(searchTitle)) {
                        found = true;
                        // Skip this note's content
                        while ((line = reader.readLine()) != null && !line.equals("---")) {
                            continue;
                        }
                        continue;
                    }
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Note with title '" + searchTitle + "' not found.");
            return;
        }

        try (FileWriter writer = new FileWriter(NOTES_FILE)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("Note deleted successfully!");
        } catch (IOException e) {
            System.out.println("Error deleting note: " + e.getMessage());
        }
    }
}