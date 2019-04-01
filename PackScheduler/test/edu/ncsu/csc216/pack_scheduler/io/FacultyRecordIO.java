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

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * @author Cameron, Matthew, and Michael
 *
 */
public class FacultyRecordIO {

	/**
	 * This method takes a String parameter representing the name of the file that
	 * contains faculty information to read from. A scanner is used to read each
	 * line one at a time. A LinkedList is then made to store the Faculty in the
	 * file in a list. While the file continues to have valid lines, it gets the
	 * current line and passes it as a parameter to the processFaculty method to
	 * actually return the Faculty object represented by the line.
	 * 
	 * 
	 * @param fileName the name of the file that contains the faculty information in
	 *                 a comma separated list.
	 * @return A LinkedList of Faculty objects that were read in from the file.
	 * @throws FileNotFoundException if the file specified by the fileName does not
	 *                               exist.
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

	/**
	 * This method is called by the readFacultyRecords() method that passes in the
	 * line to be processed. A scanner with a delimiter set to a comma reads each
	 * value which corresponds to the Faculty's fields. Once all of the values are
	 * read in a Faculty object is created. If any of the values are invalid then an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param line the line from the file that contains information about the
	 *             Faculty
	 * @return a Faculty object from the information in the line.
	 */
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
		// Otherwise, throw an IllegalArgumentException.
		if (reader.hasNextInt()) {
			maxCourses = reader.nextInt();
			faculty = new Faculty(firstName, lastName, id, email, hashedPassword, maxCourses);
		} else {
			reader.close();
			throw new IllegalArgumentException();
		}

		reader.close(); // Always close what you open
		return faculty;
	}

	/**
	 * Writes the Faculty Objects in the LinkedList to a file specified by the fileName parameter by traversing the
	 * LinkedList, also passed in as a parameter, and using the toString() method on each Faculty object.
	 * 
	 * @param fileName The name of the file to be written to.
	 * @param list the List of Faculty Objects to write to the file.
	 * @throws IOException if the file is invalid or inaccessible.
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> list) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < list.size(); i++) {
			fileWriter.println(list.get(i).toString());
		}
		fileWriter.close();
	}
}
