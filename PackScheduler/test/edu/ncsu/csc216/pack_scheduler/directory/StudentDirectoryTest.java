package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 * @author Elias Robertson, William Boyles, Joseph Dasilva
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private static final String VALID_TEST_FILE = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		final Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		final Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(VALID_TEST_FILE);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(VALID_TEST_FILE);
		assertEquals(10, sd.getStudentDirectory().length);
		
		//Test invalid file
		sd.newStudentDirectory();
		try {
			sd.loadStudentsFromFile("not real");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file not real", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		//Test invalid password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "password", MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Passwords do not match", e.getMessage());
		}
		
		//Test hashing password
		//ENTER HERE
		//ENTER HERE
		//ENTER HERE
		//ENTER HERE
		//POR QUEEEE
		
		//Test non-unique id
		sd.addStudent("Joseph", "Dasilva", "200241018", "jvdasilv@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS);
		assertFalse(sd.addStudent("Joseph", "Dasilva", ID, "jvdasilv@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS));
		assertFalse(sd.addStudent("Joseph", "Dasilva", "200241018", "jvdasilv@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS));
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
				
		//Add students and remove
		sd.loadStudentsFromFile(VALID_TEST_FILE);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
		//Fail to add a student
		try {
			sd.saveStudentDirectory("/home/sesmith5/actual_student_records.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e.getMessage());
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests the getStudentById method for several different cases
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd = new StudentDirectory();
		//add a few Students
		Student s1 = new Student("Evan", "Frost", "efrost", "efrost@ncsu.edu", "password", 15);
		Student s2 = new Student("Alan", "Rickman", "arick", "efrost@ncsu.edu", "password", 15);
		
		sd.addStudent("Evan", "Frost", "efrost", "efrost@ncsu.edu", "password", "password", 15);
		sd.addStudent("Alan", "Rickman", "arick", "efrost@ncsu.edu", "password", "password", 15);
		
		//tests finding a Student in the StudentDirectory
		Student real = sd.getStudentById("efrost");
		assertEquals(real.getFirstName(), s1.getFirstName());
		assertEquals(real.getLastName(), s1.getLastName());
		assertEquals(real.getId(), s1.getId());
		
		Student real2 = sd.getStudentById("arick");
		assertEquals(real2.getFirstName(), s2.getFirstName());
		assertEquals(real2.getLastName(), s2.getLastName());
		assertEquals(real2.getId(), s2.getId());
		
		//tests finding a Student not in the StudentDirectory
		Student fake = sd.getStudentById("fakeid");
		assertNull(fake);
		
	}

}
