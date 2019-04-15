/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests each method of CourseCatalog.
 * 
 * @author Joseph Dasilva, Elias Robertson, William Boyles
 *
 */
public class CourseCatalogTest {

	/** Expected results for valid courses */
	private final String validCourse1 = "CSC116,Intro to Programming - Java,001,3,jdyoung2,10,MW,910,1100";
	private final String validCourse2 = "CSC116,Intro to Programming - Java,002,3,spbalik,10,MW,1120,1310";
	private final String validCourse3 = "CSC116,Intro to Programming - Java,003,3,tbdimitr,10,TH,1120,1310";
	// private final String validCourse4 = "CSC116,Intro to Programming -
	// Java,002,3,jtking,10,TH,910,1100";
	private final String validCourse4 = "CSC216,Programming Concepts - Java,001,4,sesmith5,10,TH,1330,1445";
	private final String validCourse5 = "CSC216,Programming Concepts - Java,002,4,jtking,10,MW,1330,1445";
	private final String validCourse6 = "CSC216,Programming Concepts - Java,601,4,jep,10,A";
	private final String validCourse7 = "CSC226,Discrete Mathematics for Computer Scientists,001,3,tmbarnes,10,MWF,935,1025";
	private final String validCourse8 = "CSC230,C and Software Tools,001,3,dbsturgi,10,MW,1145,1300";

	/** Array to hold expected results */
	// private final String[] validCourses = { validCourse1, validCourse2,
	// validCourse3, validCourse4, validCourse5, validCourse6, validCourse7,
	// validCourse8 };

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#loadCoursesFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		Course course1 = cc.getCourseFromCatalog("CSC116", "001");
		Course course2 = cc.getCourseFromCatalog("CSC116", "002");
		Course course3 = cc.getCourseFromCatalog("CSC116", "003");
		Course course4 = cc.getCourseFromCatalog("CSC216", "001");
		Course course5 = cc.getCourseFromCatalog("CSC216", "002");
		Course course6 = cc.getCourseFromCatalog("CSC216", "601");
		Course course7 = cc.getCourseFromCatalog("CSC226", "001");
		Course course8 = cc.getCourseFromCatalog("CSC230", "001");
		Course course9 = cc.getCourseFromCatalog("JOSEPH", "001"); // should be null b/c it isn't in the catalog
		System.out.println(course1);
		assertEquals(course1.toString(), validCourse1);
		assertEquals(course2.toString(), validCourse2);
		assertEquals(course3.toString(), validCourse3);
		assertEquals(course4.toString(), validCourse4);
		assertEquals(course5.toString(), validCourse5);
		assertEquals(course6.toString(), validCourse6);
		assertEquals(course7.toString(), validCourse7);
		assertEquals(course8.toString(), validCourse8);
		assertNull(course9);

		try {
			cc.loadCoursesFromFile("test-file/course_records.txt"); // Misspelled on purpose to
		} catch (IllegalArgumentException e) {
			assertEquals("File not found.", e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#addCourseToCatalog(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, java.lang.String, int, int)}.
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();

		assertTrue(cc.addCourseToCatalog("Eli123", "title", "001", 4, "jvdasilv", 150, "MWF", 900, 1300));

		cc.loadCoursesFromFile("test-files/course_records.txt");

		assertTrue(cc.addCourseToCatalog("Eli123", "title", "001", 4, "jvdasilv", 150, "MWF", 900, 1300));

		try {
			assertFalse(cc.addCourseToCatalog("Eli123", "title", "001", 4, "jvdasilv", 150, "MWF", 900, 1300));
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertTrue(cc.addCourseToCatalog("JOS123", "title", "001", 4, "jvdasilv", 150, "MWF", 1900, 2100));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#removeCourseFromCatalog(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");

		// name && !section
		// !name && section
		// name && section
		cc.removeCourseFromCatalog("CSC116", "003");
		assertEquals(1, cc.getCourseCatalog().length + 1);

		// !name && !section
		cc.removeCourseFromCatalog("HON299", "001");
		assertEquals(1, cc.getCourseCatalog().length + 1);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseCatalog()}.
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();

		try {
			cc.loadCoursesFromFile("test-files/abc.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("File not found.", e.getMessage());
		}

		String[][] courses = cc.getCourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		courses = cc.getCourseCatalog();
		assertEquals(courses[0][0], "CSC116");
		assertEquals(courses[0][1], "001");
		assertEquals(courses[0][2], "Intro to Programming - Java");
		assertEquals(courses[0][3], "MW 9:10AM-11:00AM");

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#saveCourseCatalog(java.lang.String)}.
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");

		cc.saveCourseCatalog("test-files/course_records_actual.txt");

		checkFiles("test-files/course_records.txt", "test-files/course_records.txt");

		try {
			cc.saveCourseCatalog("test-file/course_records_actual.txt"); // Misspelled to make non-existent path.
		} catch (IllegalArgumentException e) {
			assertEquals("The file cannot be saved.", e.getMessage());
		}
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File(expFile));
			Scanner actScanner = new Scanner(new File(actFile));

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
	 * Tests the newCourseCatalogMethod
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.newCourseCatalog();

		assertEquals(0, cc.getCourseCatalog().length);
	}

}
