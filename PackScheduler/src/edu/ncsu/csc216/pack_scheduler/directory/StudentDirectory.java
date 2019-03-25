package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Maintains a directory of all students enrolled at NC State. All students have
 * a unique id.
 * 
 * @author Sarah Heckman
 * 
 */
public class StudentDirectory {

	/** List of students in the directory */
	private SortedList<Student> studentDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Creates an empty student directory.
	 */
	public StudentDirectory() {
		newStudentDirectory();
	}

	/**
	 * Creates an empty student directory. All students in the previous list are
	 * list unless saved by the user.
	 */
	public void newStudentDirectory() {
		studentDirectory = new SortedList<Student>();
	}

	/**
	 * Constructs the student directory by reading in student information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of students
	 * @throws IllegalArgumentException if the file cannot be read
	 */
	public void loadStudentsFromFile(String fileName) {
		try {
			studentDirectory = StudentRecordIO.readStudentRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Student to the directory. Returns true if the student is added and
	 * false if the student is unable to be added because their id matches another
	 * student's id.
	 * 
	 * This method also hashes the student's password for internal storage.
	 * 
	 * @param firstName      student's first name
	 * @param lastName       student's last name
	 * @param id             student's id
	 * @param email          student's email
	 * @param password       student's password
	 * @param repeatPassword student's repeated password
	 * @param maxCredits     student's max credits.
	 * @return true if added
	 * @throws IllegalArgumentException if the passwords are null, empty strings,
	 *                                  cannot be hashed, or don't match
	 */
	public boolean addStudent(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCredits) {
		String hashPW = "";
		String repeatHashPW = "";

		// Password and repeat cannot be null or empty string
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}

		// Attempt to hash the password and its repeat using the specified algorithm
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());

			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}

		// Compare the hashes of the password and its repeat to see if they match.
		// If the hashes match, then the passwords must also match
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		// We have now ensured the password and its repeat are valid and hashed.
		// We will try to create a student and add it to the directory using this
		// information.
		// If an IllegalArgumentException is thrown while creating the student, it's
		// passed up from Student to the GUI.
		Student student = new Student(firstName, lastName, id, email, hashPW, maxCredits);

		// Check that the student we just created is not already in the directory by
		// comparing the IDs of every student in the directory against ours.
		// If the IDs match, don't add the student and return false.
		for (int i = 0; i < studentDirectory.size(); i++) {
			User s = studentDirectory.get(i);
			if (s.getId().equals(student.getId())) {
				return false;
			}
		}

		// Add the unique student and return true
		return studentDirectory.add(student);
	}

	/**
	 * Removes the first student with a given id from the student directory.
	 * 
	 * @param studentId student's id
	 * @return True if the student is removed and false if the student is not in the
	 *         directory.
	 */
	public boolean removeStudent(String studentId) {
		User s = null; // Placeholder variable for each student in the directory

		// Check each student in the directory
		for (int i = 0; i < studentDirectory.size(); i++) {
			s = studentDirectory.get(i);

			// If a student's ID matches studentId, remove that student and return true.
			if (s.getId().equals(studentId)) {
				studentDirectory.remove(i);
				return true;
			}
		}

		// A student with the specified ID wasn't found, so return false.
		return false;
	}

	/**
	 * Returns all students in the directory with a column for first name, last
	 * name, and id.
	 * 
	 * @return 2d String array containing students first name, last name, and id.
	 *         One student per row.
	 */
	public String[][] getStudentDirectory() {
		String[][] directory = new String[studentDirectory.size()][3];
		User s = null; // Placeholder variable for each student in the directory.

		// Go through each student in the directory and add their information to a row
		// in the array.
		for (int i = 0; i < studentDirectory.size(); i++) {
			s = studentDirectory.get(i);

			// Consider directory[i] = {s.getFirstName(), s.getLastName() ,s.getId()};
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}

		// Return the array of student information.
		return directory;
	}

	/**
	 * Saves all students in the directory to a file.
	 * 
	 * @param fileName File path to save student directory to as a String.
	 * @throws IllegalArgumentException if the file cannot be written to.
	 */
	public void saveStudentDirectory(String fileName) {
		try {
			StudentRecordIO.writeStudentRecords(fileName, studentDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	/**
	 * Searches for a Student by their id.  If no student is found then the method returns null
	 * @param id the Student id being searched for 
	 * @return Student the Student with the matching id or null if no Student is found
	 */
	public Student getStudentById(String id) {
		for(int i = 0; i < studentDirectory.size(); i++) {
			if(id.equals(studentDirectory.get(i).getId())) {
				return studentDirectory.get(i);
			}
		}
		return null;
	}

}
