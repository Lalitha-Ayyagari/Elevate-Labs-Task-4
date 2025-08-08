# Elevate-Labs-Task-4
**Notes Manager**
A simple text-based notes management application built in Java, using FileReader and FileWriter for persistent storage. This program allows users to create, read, update, and delete notes through a command-line interface.

**Features**
Create new notes with a title and content
Read all stored notes
Update existing notes by title
Delete notes by title
Persistent storage in a notes.txt file
Error handling for file operations

Prerequisites

Java Development Kit (JDK) 8 or higher
Visual Studio Code (or any Java-compatible IDE)
Terminal or command-line interface

Setup Instructions

Clone or Download the Project

Save the NotesManager.java file in a project directory.


Open in VS Code

Open Visual Studio Code.
Navigate to File > Open Folder and select the project directory.


Compile the Program

Open the Terminal in VS Code (Terminal > New Terminal).
Run the following command to compile:javac NotesManager.java




Run the Program

In the same Terminal, execute:java NotesManager





Usage

Upon running, the program displays a menu with the following options:
Create a new note: Enter a title and content for a new note.
Read all notes: Display all notes stored in notes.txt.
Update a note: Enter the title of an existing note and provide new content.
Delete a note: Enter the title of the note to delete.
Exit: Close the program.


Follow the prompts to interact with the notes.
Notes are stored in a notes.txt file in the project directory, with the format:=== Note Title ===
Note content
---



File Structure

NotesManager.java: The main Java source file containing the application logic.
notes.txt: Auto-generated file where notes are stored (created after the first note is added).

Notes

The program uses FileReader and FileWriter for file operations.
Notes are appended to notes.txt when created.
Updating or deleting a note preserves other notes in the file.
Basic error handling is included for file operations (e.g., file not found, I/O errors).
The program runs in an infinite loop until the user selects the "Exit" option.

Troubleshooting

"File not found" error: If notes.txt doesn't exist, create a note first to generate the file.
Compilation errors: Ensure JDK is installed and configured correctly in your environment.
Input issues: Enter valid numeric choices (1–5) for the menu and follow prompts for text input.

