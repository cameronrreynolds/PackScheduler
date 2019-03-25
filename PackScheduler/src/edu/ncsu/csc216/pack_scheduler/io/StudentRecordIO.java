package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Reads and writes a student record from/to a file
 * 
 * @author William Boyles, Joseph Dasilva, Elias Robertson
 */
public class StudentRecordIO {

	/**
	 * Reads student records from a file and creates adn returns an SortedList of
	 * students.
	 * 
	 * @param fileName name of the file being read as a String.
	 * @return students SortedList of Student objects
	 * @throws FileNotFoundException If the file specified is invalid or doesn't
	 *                               exist.
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		Student student = null; // Placeholder variable for each Student in the file.
		User s = null; // Placeholder variable for each Student already added to the SortedList

		// Look through each line in the file and attempt to create a Student.
		while (fileReader.hasNextLine()) {
			try {

				student = processStudent(fileReader.nextLine());
				boolean duplicate = false;

				// Check that the student from the current line has not already been added.
				for (int i = 0; i < students.size(); i++) {
					s = students.get(i);
					if (student.equals(s)) {
						duplicate = true;
						i = students.size(); // No need to continue checking if we already found a duplicate.
					}
				}

				// If the student was unique, add it the the SortedList
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				// Skip the line if it represented an invalid student
			}
		}

		fileReader.close(); // Always close what you open
		return students; // return the list of unique students.
	}

	/**
	 * Writes student records to a file
	 * 
	 * @param fileName         name of file being written to
	 * @param studentDirectory SortedList of Students being used
	 * @throws IOException If the file is invalid or inaccessible.
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
	}

	/**
	 * Helper method for writeStudentRecords that processes one line and returns a
	 * Student.
	 * 
	 * @param line A single line of a records file as a String.
	 * @return A student represented in the line of the file.
	 * @throws IllegalArgumentException if the parameter is null or an empty string,
	 *                                  or the line doesn't have the proper
	 *                                  formatting.
	 */
	private static Student processStudent(String line) {

		// If the line is empty or null throw an IllegalARgumentException
		if (line == null || line.equals("")) {
			throw new IllegalArgumentException();
		}

		Scanner reader = new Scanner(line); // Scanner to read file line
		reader.useDelimiter(","); // Values in file are separated by commas

		// Get the fields of student
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
		int maxCredits;
		Student student; // Placeholder variable for Student we are trying to create.

		// If there is a maxCredits number, create a student with it.
		// Otherwise, create a student without maxCredits.
		if (reader.hasNextInt()) {
			maxCredits = reader.nextInt();
			student = new Student(firstName, lastName, id, email, hashedPassword, maxCredits);
		} else {
			student = new Student(firstName, lastName, id, email, hashedPassword);
		}

		reader.close(); // Always close what you open
		return student;
	}

}