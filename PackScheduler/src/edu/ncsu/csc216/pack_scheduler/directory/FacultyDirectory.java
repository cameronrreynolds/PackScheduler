package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of faculty with all their information.
 * 
 * @author Matthew Strickland
 *
 */
public class FacultyDirectory {

	/** List of the faculty in the directory. */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty faculty directory.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates an empty faculty directory. All faculty in the previous list are
	 * lost unless saved by the user.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Constructs the faculty directory by reading in faculty information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of faculty
	 * @throws IllegalArgumentException if the file cannot be read
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a Faculty to the directory. Returns true if the faculty is added and
	 * false if the faculty is unable to be added because their id matches another
	 * faculty's id.
	 * 
	 * This method also hashes the faculty's password for internal storage.
	 * 
	 * @param firstName      faculty's first name
	 * @param lastName       faculty's last name
	 * @param id             faculty's id
	 * @param email          faculty's email
	 * @param password       faculty's password
	 * @param repeatPassword faculty's repeated password
	 * @param maxCredits     faculty's max credits.
	 * @return true if added
	 * @throws IllegalArgumentException if the passwords are null, empty strings,
	 *                                  cannot be hashed, or don't match
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
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
		// We will try to create a faculty and add it to the directory using this
		// information.
		// If an IllegalArgumentException is thrown while creating the faculty, it's
		// passed up from faculty to the GUI.
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);

		// Check that the faculty we just created is not already in the directory by
		// comparing the IDs of every faculty in the directory against ours.
		// If the IDs match, don't add the faculty and return false.
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}

		// Add the unique faculty and return true
		return facultyDirectory.add(faculty);
	}
	
	/**
	 * Removes the first faculty with a given id from the faculty directory.
	 * 
	 * @param facultyId faculty's id
	 * @return True if the faculty is removed and false if the faculty is not in the
	 *         directory.
	 */
	public boolean removeFaculty(String facultyId) {
		User s = null; // Placeholder variable for each faculty in the directory

		// Check each faculty in the directory
		for (int i = 0; i < facultyDirectory.size(); i++) {
			s = facultyDirectory.get(i);

			// If a faculty's ID matches facultyId, remove that faculty and return true.
			if (s.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}

		// A faculty with the specified ID wasn't found, so return false.
		return false;
	}

	/**
	 * Returns all faculty in the directory with a column for first name, last
	 * name, and id.
	 * 
	 * @return 2d String array containing faculty first name, last name, and id.
	 *         One faculty per row.
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		User s = null; // Placeholder variable for each faculty in the directory.

		// Go through each faculty in the directory and add their information to a row
		// in the array.
		for (int i = 0; i < facultyDirectory.size(); i++) {
			s = facultyDirectory.get(i);

			// Consider directory[i] = {s.getFirstName(), s.getLastName() ,s.getId()};
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}

		// Return the array of faculty information.
		return directory;
	}

	/**
	 * Saves all faculty in the directory to a file.
	 * 
	 * @param fileName File path to save faculty directory to as a String.
	 * @throws IllegalArgumentException if the file cannot be written to.
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
}
