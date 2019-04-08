package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * This class tests the Faculty class and all of its methods
 * @author Michael, Matthew, Cameron
 *
 */
public class FacultyTest {

	/**
	 * Test method for constructor
	 */
	@Test
	public void testConstructor() {
		Faculty f;
		try {
			f = new Faculty("Michael", "Adamik", "madamik", "madamik@ncsu.edu", "pw", 4);
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing, test passes
		}
		
		try {
			f = new Faculty("Michael", "Adamik", "madamik", "madamik@ncsu.edu", "pw", 1);
			assertEquals(1, f.getMaxCourses());
		} catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Test method for hasCode
	 */
	@Test
	public void testHashandEquals() {
		Faculty f = new Faculty("Michael", "Adamik", "madamik", "madamik@ncsu.edu", "pw", 1);
		Faculty f2 = new Faculty("Michael", "Adamik", "madamik", "madamik@ncsu.edu", "pw", 1);
		assertEquals(f.hashCode(), f2.hashCode());
		
		assertTrue(f.equals(f2));
		assertTrue(f.equals(f));
		assertFalse(f.equals(new String("test")));
	}
	
	/**
	 * This method tests the toString method
	 */
	@Test
	public void testToString() {
		Faculty f = new Faculty("Michael", "Adamik", "madamik", "madamik@ncsu.edu", "pw", 1);
		String exp = "Michael,Adamik,madamik,madamik@ncsu.edu,pw,1";
		assertEquals(exp, f.toString());
	}
	
	
}









