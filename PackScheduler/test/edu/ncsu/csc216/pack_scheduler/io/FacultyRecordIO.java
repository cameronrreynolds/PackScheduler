/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * @author Cameron, Matthew, and Michael
 *
 */
public class FacultyRecordIO {

	/**
	 * This method takes a String parameter representing the name of the file that
	 * contains faculty information to read from. A scanner is created and it's
	 * delimiter set to a comma to read each unique field of the Faculty in the
	 * comma separated list. A LinkedList is then made to store the Faculty in the
	 * file in a list. While the file continues to have valid lines, it gets the
	 * current line and passes it as a parameter to the processFaculty method to
	 * actually return the Faculty object represented by the line.
	 * 
	 * 
	 * @param fileName the name of the file that contains the faculty information in
	 *                 a comma separated list.
	 * @return A LinkedList of Faculty objects that were read in from the file.
	 * @throws FileNotFoundException if the file specified by the fileName does not exist.
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		Faculty faculty = null; // Placeholder variable for each Faculty in the file.
		User s = null; // Placeholder variable for each Faculty already added to the LinkedList

		// Look through each line in the file and attempt to create a Faculty.
		while (fileReader.hasNextLine()) {
			try {

				faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;

				// Check that the Faculty from the current line has not already been added.
				for (int i = 0; i < facultyList.size(); i++) {
					s = facultyList.get(i);
					if (faculty.equals(s)) {
						duplicate = true;
						i = facultyList.size(); // No need to continue checking if we already found a duplicate.
					}
				}

				// If the Faculty was unique, add it the the LinkedList
				if (!duplicate) {
					facultyList.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				// Skip the line if it represented an invalid Faculty
			}
		}

		fileReader.close(); // Always close what you open
		return facultyList; // return the list of unique Faculty.
	}

	private static Faculty processFaculty(String line) {
		// If the line is empty or null throw an IllegalArgumentException
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

		// If there is a maxCourses number, create a Faculty with it.
		// Otherwise, create a Faculty without maxCourses.
		if (reader.hasNextInt()) {
			maxCourses = reader.nextInt();
			faculty = new Faculty(firstName, lastName, id, email, hashedPassword, maxCourses);
		} else {
			faculty = new Faculty(firstName, lastName, id, email, hashedPassword);
		}

		reader.close(); // Always close what you open
		return faculty;
	}

	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> list) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < list.size(); i++) {
			fileWriter.println(list.get(i).toString());
		}
		fileWriter.close();
	}
}
