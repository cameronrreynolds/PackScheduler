package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule class.
 * 
 * @author Matthew Strickland, William Boyles, Joey Davidson 
 *
 */
public class ScheduleTest {
	
	/** A schdule object that will be used in every test */
	public Schedule s;
	
	/**
	 * Makes the schdule field a new Schdule.
	 */
	@Before
	public void setUp() {
		s = new Schedule();
	}
	
	/**
	 * Tests the Schedule constructor.
	 */
	@Test
	public void testSchedule() {
		assertEquals(0, s.getScheduledCourses().length);
		assertEquals("My Schedule", s.getTitle());
	}
	
	/**
	 * Tests the addCourseToSchdule method.
	 */
	@Test
	public void testAddCourseToSchedule() {
		//null course
		Course c = null;
		try{
			s.addCourseToSchedule(c);
		} catch (NullPointerException e) {
			assertEquals(0, s.getScheduledCourses().length);
		}
		
		//duplicate
		c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 200, "MW", 1330, 1430);
		Course d = c;
		assertTrue(s.addCourseToSchedule(c));
		try {
			s.addCourseToSchedule(d);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
		
		//conflict
		d = new Course("CSC226", "Discrete Math", "001", 3, "ascarf", 200, "MW", 1300, 1400);
		try {
			s.addCourseToSchedule(d);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
	}
	
	/**
	 * Tests the removeCourseFromSchedule method.
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 200, "MW", 1330, 1430);
		s.addCourseToSchedule(c);
		assertEquals(1, s.getScheduledCourses().length);
		assertTrue(s.removeCourseFromSchedule(c));
		assertEquals(0, s.getScheduledCourses().length);
		assertFalse(s.removeCourseFromSchedule(c));
	}
	
	/**
	 * Tests the resetSchedule method.
	 */
	@Test
	public void testResetSchedule() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 200, "MW", 1330, 1430);
		s.addCourseToSchedule(c);
		assertEquals(1, s.getScheduledCourses().length);
		s.resetSchedule();
		assertEquals(0, s.getScheduledCourses().length);
	}
	
	/**
	 * Tests the getSchduledCoursesMethod.
	 */
	@Test
	public void testGetScheduledCourses() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 200, "MW", 1330, 1430);
		s.addCourseToSchedule(c);
		String[][] sch = s.getScheduledCourses();
		assertEquals(1, sch.length);
		assertEquals("CSC216", sch[0][0]);
		assertEquals("001", sch[0][1]);
		assertEquals("Programming Concepts - Java", sch[0][2]);
		assertEquals("MW 1:30PM-2:30PM", sch[0][3]);
	}
	
	/**
	 * Tests the setTitle method.
	 */
	@Test
	public void testSetTitle() {
		assertEquals("My Schedule", s.getTitle());
		s.setTitle("Not My Schedule");
		assertEquals("Not My Schedule", s.getTitle());
	}	
}
