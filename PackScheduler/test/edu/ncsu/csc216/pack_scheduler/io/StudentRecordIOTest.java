/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests StudentRecordIO
 * 
 * @author Elias Robertson, William Boyles, Joseph Dasilva
 */
public class StudentRecordIOTest {

	/**
	 * Valid student information provided from instruction pages in
	 * student_records.txt
	 */
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
			validStudent5, validStudent6, validStudent7, validStudent8, validStudent9 };

	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Hashes the passwords of all students using SHA-256
	 */
	@Before
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests ReadStudentRecords method
	 */
	@Test
	public void testReadStudentRecords() {
		SortedList<Student> readStudents = new SortedList<Student>();

		// Test student_records.txt
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			// Test the expected size of the list
			assertEquals(10, readStudents.size());

			// Test that each read studetn's toString() matches the valid students
			for (int i = 0; i < readStudents.size(); i++) {
				User s = readStudents.get(i);
				assertEquals(validStudents[i], s.toString());
			}

		} catch (FileNotFoundException e) {
			fail("Unable to find student_records.txt");
		}

		// Test invalid_student_records.txt
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");

			// make sure no invalid students were read in (list size of 0)
			assertEquals(0, readStudents.size());
		} catch (FileNotFoundException e) {
			fail("Unable to find invalid_student_records.txt");
		}

		// Test non-existent file
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/non_existent_file.txt");
			fail("Successfully read non-existent file");
		} catch (FileNotFoundException e) {
			// do nothing, as it should
		}

		// Test a file with duplicate records, empty lines, and a student without
		// maxHours listed
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/duplicate_records.txt");
			assertEquals(2, readStudents.size());
		} catch (FileNotFoundException e) {
			fail("Could not find duplicate_records.txt even though it exists.");
		}
	}

	/**
	 * Tests various aspects of writeStudetnRecords() to make sure that students
	 * written to a file are valid
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();

		// Add a valid student
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		// Assumption that you are using a hash of "pw" stored in hashPW

		// Try to write the SortedList of Students to a file.
		// If it can't, throw an IOException.
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);

			checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		} catch (IOException e) {
			fail("Was unable to write to a valid file with proper premissions");
		}

	}

	/**
	 * Test provided by Dr. Heckman that is supposed to fail in windows
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}

	/**
	 * Sees if files are equal in contents. This method was provided from the
	 * instruction pages.
	 * 
	 * @param expFile The file path of the expected file contents as a String.
	 * @param actFile The file path of the actual file contenst as a String.
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
