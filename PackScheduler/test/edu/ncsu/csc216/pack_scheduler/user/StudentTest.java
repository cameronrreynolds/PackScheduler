package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;

/**
 * Tests the student class
 * 
 * @author Elias Robertson, William Boyles, Joseph Dasilva
 *
 */
public class StudentTest {
	private String firstName = "Zahir";
	private String firstName2 = "a";
	private String lastName = "King";
	private String lastName2 = "a";
	private String id = "zking";
	private String id2 = "a";
	private String email = "orci.Donec@ametmassaQuisque.com";
	private String email2 = "a@a.com";
	private String invalidEmail1 = "orchi.donec@com";
	private String invalidEmail2 = "orchi@com";
	private String invalidEmail3 = "orchi.com";
	private String password = "pw";
	private String password2 = "a";
	private int maxCredits = 15;
	private int invalidMaxCredits1 = 2;
	private int invalidMaxCredits2 = 20;
	private String invalidString1 = null;
	private String invalidString2 = "";
	private String toString = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private Student validStudent = new Student(firstName, lastName, id, email, password, maxCredits);
	private Student validStudent2 = new Student(firstName, lastName, id, email, password);
	private final int maxCredits2 = 18;
	private Object obj = null;
	private Object obj1 = new Object();
	private StudentRecordIO studentRecord = new StudentRecordIO();

	/**
	 * Test method for hashcode
	 */
	@Test
	public void testHashCode() {
		assertEquals(validStudent.hashCode(), validStudent.hashCode());
		assertFalse(validStudent.hashCode() == validStudent2.hashCode());
		User student1 = new Student(firstName2, lastName, id, email, password, maxCredits);
		User student2 = new Student(firstName, lastName2, id, email, password, maxCredits);
		User student3 = new Student(firstName, lastName, id2, email, password, maxCredits);
		User student4 = new Student(firstName, lastName, id, email2, password, maxCredits);
		User student5 = new Student(firstName, lastName, id, email, password2, maxCredits);
		assertTrue(validStudent.hashCode() != student1.hashCode());
		assertFalse(validStudent.hashCode() == student2.hashCode());
		assertFalse(validStudent.hashCode() == student3.hashCode());
		assertFalse(validStudent.hashCode() == student4.hashCode());
		assertFalse(validStudent.hashCode() == student5.hashCode());
	}

	/**
	 * Test method for Student constructor
	 */
	@Test
	public void testStudentConstructor1() {
		// Check values of all parts of the valid student
		assertEquals(firstName, validStudent.getFirstName());
		assertEquals(lastName, validStudent.getLastName());
		assertEquals(id, validStudent.getId());
		assertEquals(email, validStudent.getEmail());
		assertEquals(password, validStudent.getPassword());
		assertEquals(maxCredits, validStudent.getMaxCredits());
		// Try creating a student with one of each invalid values for the constructor,
		// try catch for the error on all
		try {
			validStudent = new Student(invalidString1, lastName, id, email, password, maxCredits); // invalid first name
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid first name", e.getMessage());
			assertEquals(firstName, validStudent.getFirstName());
		}
		try {
			validStudent = new Student(firstName, invalidString1, id, email, password, maxCredits); // invalid last name
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid last name", e.getMessage());
			assertEquals(lastName, validStudent.getLastName());
		}
		try {
			validStudent = new Student(firstName, lastName, invalidString1, email, password, maxCredits); // invalid id
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid id", e.getMessage());
			assertEquals(id, validStudent.getId());
		}
		try {
			validStudent = new Student(firstName, lastName, id, invalidString1, password, maxCredits); // invalid email
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals(email, validStudent.getEmail());
		}
		try {
			validStudent = new Student(firstName, lastName, id, email, invalidString1, maxCredits); // invalid password
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
			assertEquals(password, validStudent.getPassword());
		}
		try {
			validStudent = new Student(firstName, lastName, id, email, password, invalidMaxCredits1); // invalid max
																										// credits
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid max credits", e.getMessage());
			assertEquals(maxCredits, validStudent.getMaxCredits());
		}
		try {
			validStudent = new Student(invalidString2, lastName, id, email, password, maxCredits);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid first name", e.getMessage());
			assertEquals(firstName, validStudent.getFirstName());
		}
		try {
			validStudent = new Student(firstName, invalidString2, id, email, password, maxCredits);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid last name", e.getMessage());
			assertEquals(lastName, validStudent.getLastName());
		}
		try {
			validStudent = new Student(firstName, lastName, invalidString2, email, password, maxCredits);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid id", e.getMessage());
			assertEquals(id, validStudent.getId());
		}
		try {
			validStudent = new Student(firstName, lastName, id, invalidString2, password, maxCredits);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals(email, validStudent.getEmail());
		}
		try {
			validStudent = new Student(firstName, lastName, id, email, invalidString2, maxCredits);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
			assertEquals(password, validStudent.getPassword());
		}
		try {
			validStudent = new Student(firstName, lastName, id, email, password, invalidMaxCredits2);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid max credits", e.getMessage());
			assertEquals(maxCredits, validStudent.getMaxCredits());
		}
	}

	/**
	 * Test method for Student constructor number 2
	 */
	@Test
	public void testStudentConstructor2() {
		assertEquals(validStudent2.getFirstName(), firstName);
		assertEquals(validStudent2.getLastName(), lastName);
		assertEquals(validStudent2.getId(), id);
		assertEquals(validStudent2.getEmail(), email);
		assertEquals(validStudent2.getPassword(), password);
		assertEquals(validStudent2.getMaxCredits(), maxCredits2);
	}

	/**
	 * Test method for setEmail
	 */
	@Test
	public void testSetEmail() {
		try {
			validStudent.setEmail(invalidEmail1);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
		}
		assertEquals(validStudent.getEmail(), email);
		try {
			validStudent.setEmail(invalidEmail2);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
		}
		assertEquals(validStudent.getEmail(), email);
		try {
			validStudent.setEmail(invalidEmail3);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
		}
		assertEquals(validStudent.getEmail(), email);
	}

	/**
	 * Test method for toString
	 */
	@Test
	public void testToString() {
		assertEquals(toString, validStudent.toString());
	}

	/**
	 * Test method for equals
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		assertFalse(validStudent.equals(obj));
		assertFalse(validStudent.equals(obj1));
		assertFalse(validStudent.equals(studentRecord));
		assertFalse(validStudent.equals(validStudent2));
		User student1 = new Student(firstName2, lastName, id, email, password, maxCredits);
		User student2 = new Student(firstName, lastName2, id, email, password, maxCredits);
		User student3 = new Student(firstName, lastName, id2, email, password, maxCredits);
		User student4 = new Student(firstName, lastName, id, email2, password, maxCredits);
		User student5 = new Student(firstName, lastName, id, email, password2, maxCredits);
		User student6 = new Student(firstName, lastName, id, email, "passwordthatsverylong", maxCredits);
		User student7 = new Student(firstName, lastName, id, email, password, maxCredits);
		assertFalse(validStudent.equals(student1));
		assertFalse(validStudent.equals(student2));
		assertFalse(validStudent.equals(student3));
		assertFalse(validStudent.equals(student4));
		assertFalse(validStudent.equals(student5));
		assertFalse(validStudent.equals(student6));
		assertTrue(validStudent.equals(student7));
		assertTrue(validStudent.equals(validStudent));

	}

	/**
	 * Tests compareTo. Students should be ordered by last, first, and finally
	 * unity ID
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("A", "A", "AAAA", "AAAA@ncsu.edu", "AAAAAA");
		Student s2 = null;
		Student s3 = new Student("A", "A", "AAAA", "AAAA@ncsu.edu", "AAAAAA");
		Student s4 = new Student("Z", "Z", "ZZZZ", "ZZZZ@ncsu.edu", "ZZZZZZ");
		Student s5 = new Student("A", "Z", "ZZZZ", "ZZZZ@ncsu.edu", "ZZZZZZ");
		Student s7 = new Student("Z", "A", "ZZZZ", "ZZZZ@ncsu.edu", "ZZZZZZ");
		Student s6 = new Student("A", "A", "ZZZZ", "ZZZZ@ncsu.edu", "ZZZZZZ");
		
		//NullPointer case
		try { 
			s1.compareTo(s2);
			fail();
		} catch (NullPointerException e) {
			assertEquals("A", s1.getFirstName());
		}
		
		//Actually equal students
		assertEquals(0, s1.compareTo(s3));
		
		//Not equal students by first name
		assertEquals(-25, s1.compareTo(s7));
		assertEquals(-25, s1.compareTo(s4));
		assertEquals(25, s4.compareTo(s1));
		
		//Not equal students by last name
		assertEquals(-25, s1.compareTo(s5));
		assertEquals(25, s5.compareTo(s1));
		
		//Not equal students by id 
		assertEquals(-25, s1.compareTo(s6));
		assertEquals(25, s6.compareTo(s1));
	}

}
