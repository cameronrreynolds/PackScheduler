package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Reads faculty information stored in a .txt file and writes information to new
 * .txt files.
 * 
 * @author Matthew Strickland
 *
 */
public class FacultyRecordIO {

	/**
	 * Reads faculty records from a file and creates and returns an LinkedList of
	 * faculty.
	 * 
	 * @param fileName name of the file being read as a String.
	 * @return faculty LinkedList of Faculty objects
	 * @throws FileNotFoundException If the file specified is invalid or doesn't
	 *                               exist.
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		Faculty faculty = null; // Placeholder variable for each Faculty in the file.
		User s = null; // Placeholder variable for each Faculty already added to the SortedList

		// Look through each line in the file and attempt to create a Faculty.
		while (fileReader.hasNextLine()) {
			try {

				faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;

				// Check that the Faculty from the current line has not already been added.
				for (int i = 0; i < faculty.size(); i++) {
					s = faculty.get(i);
					if (faculty.equals(s)) {
						duplicate = true;
						i = faculty.size(); // No need to continue checking if we already found a duplicate.
					}
				}

				// If the Faculty was unique, add it the the SortedList
				if (!duplicate) {
					faculty.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				// Skip the line if it represented an invalid Faculty
			}
		}

		fileReader.close(); // Always close what you open
		return faculty; // return the list of unique Faculties.
	}

	/**
	 * Writes Faculty records to a file
	 * 
	 * @param fileName         name of file being written to
	 * @param facultyDirectory SortedList of Faculties being used
	 * @throws IOException If the file is invalid or inaccessible.
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
	}

	/**
	 * Helper method for writeFacultyRecords that processes one line and returns a
	 * Faculty.
	 * 
	 * @param line A single line of a records file as a String.
	 * @return A Faculty represented in the line of the file.
	 * @throws IllegalArgumentException if the parameter is null or an empty string,
	 *                                  or the line doesn't have the proper
	 *                                  formatting.
	 */
	private static Faculty processFaculty(String line) {

		// If the line is empty or null throw an IllegalARgumentException
		if (line == null || line.equals("")) {
			throw new IllegalArgumentException();
		}

		Scanner reader = new Scanner(line); // Scanner to read file line
		reader.useDelimiter(","); // Values in file are separated by commas

		// Get the fields of Faculty
		String firstName = reader.next();
		String lastName = reader.next();
		String id = reader.next();
		String email = reader.next();

		// If there is an int next in the line, close the Scanner; throw an Exception.
		if (reader.hasNextInt()) {
			reader.close(); // Always close what you open
			throw new IllegalArgumentException();
		}

		String hashedPassword = reader.next();
		int maxCourses;
		Faculty faculty; // Placeholder variable for Faculty we are trying to create.

		maxCourses = reader.nextInt();
		faculty = new Faculty(firstName, lastName, id, email, hashedPassword, maxCourses);

		reader.close(); // Always close what you open
		return faculty;
	}
	
}
