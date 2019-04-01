package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Testing the CourseRoll class and its various methods
 * @author jhdav
 *
 */
public class CourseRollTest {

	private static final int CAP = 50;
	private static final int MAX_CAP = 260;
	private static final int MIN_CAP = 5;
	private static final String FIRST_NAME = "Matt";
	private static final String LAST_NAME = "Murdock";
	private static final String ID = "mmurd";
	private static final String EMAIL = "mmurd@ncsu.edu";
	private static final String PASS = "password";
	private static final Course C = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	
	/**
	 * Testing the constructor method of the CourseRoll class
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll cr = new CourseRoll(CAP, C);
		assertNotNull(cr);
		
	}

	/**
	 * Testing the setEnrollmentCap method of the CourseRoll class
	 */
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll cr = new CourseRoll(CAP, C);
		assertEquals(cr.getEnrollmentCap(), CAP);
		
		CourseRoll cr1 = null;
		try {
			cr1 = new CourseRoll(MIN_CAP, C);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(cr1);
		}
		
		CourseRoll cr2 = null;
		try {
			cr2 = new CourseRoll(MAX_CAP, C);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(cr2);
		}
		
	}

	/**
	 * Testing the getEnrollmentCap method of the CourseRoll class
	 */
	@Test
	public void testGetEnrollmentCap() {
		CourseRoll cr = new CourseRoll(CAP, C);
		assertEquals(cr.getEnrollmentCap(), CAP);
	}
	
	/**
	 * This further tests the enroll method
	 */
	@Test
	public void testEnroll2() {
		CourseRoll cr = new CourseRoll(10, C);
		
		cr.enroll(new Student("Jack", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jackie", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jacks", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jackal", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jac", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jak", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jackson", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jacklyn", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Jacklyne", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Mike", LAST_NAME, ID, EMAIL, PASS));
		
		cr.enroll(new Student("Bob1", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob2", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob3", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob4", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob5", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob6", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob7", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob8", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob9", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob10", LAST_NAME, ID, EMAIL, PASS));
		
		try {
			cr.enroll(new Student("Bob11", LAST_NAME, ID, EMAIL, PASS));
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing, test passes
		}
	
		
	}
	
	
	/**
	 * This further tests the drop method
	 */
	@Test
	public void testDrop2() {
		CourseRoll cr = new CourseRoll(10, C);
		Student s1 = new Student("Jack", LAST_NAME, ID, EMAIL, PASS);
		Student s2 = new Student("Jackie", LAST_NAME, ID, EMAIL, PASS);
		Student s3 = new Student("Jacks", LAST_NAME, ID, EMAIL, PASS);
		Student s4 = new Student("Jackal", LAST_NAME, ID, EMAIL, PASS);
		Student s5 = new Student("Jac", LAST_NAME, ID, EMAIL, PASS);
		Student s6 = new Student("Jak", LAST_NAME, ID, EMAIL, PASS);
		Student s7 = new Student("Jackson", LAST_NAME, ID, EMAIL, PASS);
		Student s8 = new Student("Jacklyn", LAST_NAME, ID, EMAIL, PASS);
		Student s9 = new Student("Jacklyne", LAST_NAME, ID, EMAIL, PASS);
		Student s10 = new Student("Mike", LAST_NAME, ID, EMAIL, PASS);
		
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		
		cr.enroll(new Student("Bob1", LAST_NAME, ID, EMAIL, PASS));
		cr.enroll(new Student("Bob2", LAST_NAME, ID, EMAIL, PASS));
		
		cr.drop(s1);
		assertEquals(0, cr.getOpenSeats());
	}

	/**
	 * Testing the enroll method of the CourseRoll class
	 */
	@Test
	public void testEnroll() {
		CourseRoll cr = new CourseRoll(CAP, C);
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASS);
		cr.enroll(s);
		assertEquals(49, cr.getOpenSeats());
		
		Student bad = null;
		try {
			cr.enroll(bad);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(bad);
		}
		
		try {
			cr.enroll(s);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(cr.getOpenSeats(), 49);
		}
		
		CourseRoll maxedOut = new CourseRoll(10, C);
		maxedOut.enroll(s);
		maxedOut.enroll(new Student("Jack", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackie", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacks", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackal", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jac", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jak", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackson", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacklyn", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacklyne", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(0, maxedOut.getOpenSeats());
		try {
			maxedOut.enroll(new Student("Jack", LAST_NAME, ID, EMAIL, PASS));
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(0, maxedOut.getOpenSeats());
		}
	}

	/**
	 * Testing the drop method of the CourseRoll class
	 */
	@Test
	public void testDrop() {
		CourseRoll maxedOut = new CourseRoll(10, C);
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASS);
		maxedOut.enroll(s);
		maxedOut.enroll(new Student("Jack", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackie", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacks", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackal", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jac", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jak", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackson", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacklyn", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacklyne", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 0);
		maxedOut.drop(s);
		assertEquals(maxedOut.getOpenSeats(), 1);
		
		try {
			maxedOut.drop(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(maxedOut.getOpenSeats(), 1);
		}
	}

	/**
	 * Testing the getOpenSeats method of the CourseRoll class
	 */
	@Test
	public void testGetOpenSeats() {
		CourseRoll maxedOut = new CourseRoll(10, C);
		assertEquals(maxedOut.getOpenSeats(), 10);
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASS);
		maxedOut.enroll(s);
		assertEquals(maxedOut.getOpenSeats(), 9);
		maxedOut.enroll(new Student("Jack", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 8);
		maxedOut.enroll(new Student("Jackie", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 7);
		maxedOut.enroll(new Student("Jacks", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 6);
		maxedOut.enroll(new Student("Jackal", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 5);
		maxedOut.enroll(new Student("Jac", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 4);
		maxedOut.enroll(new Student("Jak", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 3);
		maxedOut.enroll(new Student("Jackson", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 2);
		maxedOut.enroll(new Student("Jacklyn", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 1);
		maxedOut.enroll(new Student("Jacklyne", LAST_NAME, ID, EMAIL, PASS));
		assertEquals(maxedOut.getOpenSeats(), 0);
	}

	/**
	 * Testing the canEnroll method of the CourseRoll class
	 */
	@Test
	public void testCanEnroll() {
		CourseRoll maxedOut = new CourseRoll(10, C);
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASS);
		maxedOut.enroll(s);
		maxedOut.enroll(new Student("Jack", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackie", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacks", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackal", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jac", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jak", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jackson", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacklyn", LAST_NAME, ID, EMAIL, PASS));
		maxedOut.enroll(new Student("Jacklyne", LAST_NAME, ID, EMAIL, PASS));
		assertFalse(maxedOut.canEnroll(s));
//		assertFalse(maxedOut.canEnroll(new Student("Alex", LAST_NAME, ID, EMAIL, PASS)));
		maxedOut.drop(s);
		assertTrue(maxedOut.canEnroll(new Student("Alex", LAST_NAME, ID, EMAIL, PASS)));
		
	}

}
