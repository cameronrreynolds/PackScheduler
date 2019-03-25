package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Tests the ConflictException constructors to
 * ensure they function as required
 * @author Elias Robertson
 */
public class ConflictExceptionTest {

  /**
   * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
   * Uses test code from the project instructions
   */
  @Test
  public void testConflictExceptionString() {
    ConflictException ce = new ConflictException("Custom exception message");
    assertEquals("Custom exception message", ce.getMessage());
  }

  /**
   * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.ConflictException#ConflictException()}.
   */
  @Test
  public void testConflictException() {
    ConflictException ce = new ConflictException();
    assertEquals("Schedule conflict.", ce.getMessage());
  }

}