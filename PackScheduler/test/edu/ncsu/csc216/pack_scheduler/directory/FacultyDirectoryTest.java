package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * This class test the FacultyDirectory class and all of its methods
 * @author Michael, Matthew, Cameron
 *
 */
public class FacultyDirectoryTest {

	
	/** Valid course records */
	private static final String VALID_TEST_FILE = "test-files/Faculty_records.txt";
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
	private static final int MAX_CREDITS = 3;
	
//	/**
//	 * Resets course_records.txt for use in other tests.
//	 * @throws Exception if something fails during setup.
//	 */
//	@Before
//	public void setUp() throws Exception {		
//		//Reset Faculty_records.txt so that it's fine for other needed tests
//		final Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_Faculty_records.txt");
//		final Path destinationPath = FileSystems.getDefault().getPath("test-files", "Faculty_records.txt");
//		try {
//			Files.deleteIfExists(destinationPath);
//			Files.copy(sourcePath, destinationPath);
//		} catch (IOException e) {
//			fail("Unable to reset files");
//		}
//	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are Facultys in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(VALID_TEST_FILE);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Test valid file
		sd.loadFacultyFromFile(VALID_TEST_FILE);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		//Test invalid file
		sd.newFacultyDirectory();
		try {
			sd.loadFacultyFromFile("not real");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file not real", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		//Test invalid password
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "password", MAX_CREDITS);
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
		sd.addFaculty("Joseph", "Dasilva", "200241018", "jvdasilv@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS);
		assertFalse(sd.addFaculty("Joseph", "Dasilva", ID, "jvdasilv@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS));
		assertFalse(sd.addFaculty("Joseph", "Dasilva", "200241018", "jvdasilv@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS));
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add Facultys and remove
		sd.loadFacultyFromFile(VALID_TEST_FILE);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("fmeadow"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Norman", facultyDirectory[5][0]);
		assertEquals("Brady", facultyDirectory[5][1]);
		assertEquals("nbrady", facultyDirectory[5][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
//	@Test
//	public void testSaveFacultyDirectory() {
//		FacultyDirectory sd = new FacultyDirectory();
//		
//		//Add a Faculty
//		sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 1);
//		assertEquals(1, sd.getFacultyDirectory().length);
//		sd.saveFacultyDirectory("test-files/actual_Faculty_records.txt");
//		checkFiles("test-files/expected_Faculty_records.txt", "test-files/actual_Faculty_records.txt");
//		
//		//Fail to add a Faculty
//		try {
//			sd.saveFacultyDirectory("/home/sesmith5/actual_Faculty_records.txt");
//		} catch (IllegalArgumentException e) {
//			assertEquals("Unable to write to file /home/sesmith5/actual_Faculty_records.txt", e.getMessage());
//		}
//	}
	
//	/**
//	 * Helper method to compare two files for the same contents
//	 * @param expFile expected output
//	 * @param actFile actual output
//	 */
//	private void checkFiles(String expFile, String actFile) {
//		try {
//			Scanner expScanner = new Scanner(new FileInputStream(expFile));
//			Scanner actScanner = new Scanner(new FileInputStream(actFile));
//			
//			while (expScanner.hasNextLine()) {
//				assertEquals(expScanner.nextLine(), actScanner.nextLine());
//			}
//			
//			expScanner.close();
//			actScanner.close();
//		} catch (IOException e) {
//			fail("Error reading files.");
//		}
//	}
	
	/**
	 * Tests the getFacultyById method for several different cases
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory sd = new FacultyDirectory();
		//add a few Facultys
		Faculty s1 = new Faculty("Evan", "Frost", "efrost", "efrost@ncsu.edu", "password", 1);
		Faculty s2 = new Faculty("Alan", "Rickman", "arick", "efrost@ncsu.edu", "password", 1);
		
		sd.addFaculty("Evan", "Frost", "efrost", "efrost@ncsu.edu", "password", "password", 1);
		sd.addFaculty("Alan", "Rickman", "arick", "efrost@ncsu.edu", "password", "password", 1);
		
		//tests finding a Faculty in the FacultyDirectory
		Faculty real = sd.getFacultyById("efrost");
		assertEquals(real.getFirstName(), s1.getFirstName());
		assertEquals(real.getLastName(), s1.getLastName());
		assertEquals(real.getId(), s1.getId());
		
		Faculty real2 = sd.getFacultyById("arick");
		assertEquals(real2.getFirstName(), s2.getFirstName());
		assertEquals(real2.getLastName(), s2.getLastName());
		assertEquals(real2.getId(), s2.getId());
		
		//tests finding a Faculty not in the FacultyDirectory
		Faculty fake = sd.getFacultyById("fakeid");
		assertNull(fake);
		
	}
}
