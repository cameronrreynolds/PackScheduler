package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the extended functionality of Course from 
 * Guided Project 2.
 * @author Sarah Heckman
 */
public class ExtendedCourseTest {
  
  /** Course name */
  private static final String NAME = "CSC216";
  /** Course title */
  private static final String TITLE = "Programming Concepts - Java";
  /** Course section */
  private static final String SECTION = "001";
  /** Course credits */
  private static final int CREDITS = 4;
  /** Course instructor id */
  private static final String INSTRUCTOR_ID = "sesmith5";
  /** Enrollment cap. */
  private static final int ENROLLMENT_CAP = 200;
  /** Course meeting days */
  private static final String MEETING_DAYS = "MW";
  /** Course start time */
  private static final int START_TIME = 1330;
  /** Course end time */
  private static final int END_TIME = 1445;

  /**
   * Tests Course.getShortDisplayArray().
   */
  @Test
  public void testGetShortDisplayArray() {
    Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
    assertEquals(NAME, c.getName());
    assertEquals(TITLE, c.getTitle());
    assertEquals(SECTION, c.getSection());
    assertEquals(CREDITS, c.getCredits());
    assertEquals(INSTRUCTOR_ID, c.getInstructorId());
    assertEquals(MEETING_DAYS, c.getMeetingDays());
    assertEquals(START_TIME, c.getStartTime());
    assertEquals(END_TIME, c.getEndTime());
    String [] actualShortDisplay = c.getShortDisplayArray();
    assertEquals(NAME, actualShortDisplay[0]);
    assertEquals(SECTION, actualShortDisplay[1]);
    assertEquals(TITLE, actualShortDisplay[2]);
    assertEquals("MW 1:30PM-2:45PM", actualShortDisplay[3]);
  }

  /**
   * Tests Course.getLongDisplayArray().
   */
  @Test
  public void testGetLongDisplayArray() {
    Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
    assertEquals(NAME, c.getName());
    assertEquals(TITLE, c.getTitle());
    assertEquals(SECTION, c.getSection());
    assertEquals(CREDITS, c.getCredits());
    assertEquals(INSTRUCTOR_ID, c.getInstructorId());
    assertEquals(MEETING_DAYS, c.getMeetingDays());
    assertEquals(START_TIME, c.getStartTime());
    assertEquals(END_TIME, c.getEndTime());
    String [] actualLongDisplay = c.getLongDisplayArray();
    assertEquals(NAME, actualLongDisplay[0]);
    assertEquals(SECTION, actualLongDisplay[1]);
    assertEquals(TITLE, actualLongDisplay[2]);
    assertEquals("" + CREDITS, actualLongDisplay[3]);
    assertEquals(INSTRUCTOR_ID, actualLongDisplay[4]);
    assertEquals("MW 1:30PM-2:45PM", actualLongDisplay[5]);
    assertEquals("", actualLongDisplay[6]);
  }

}