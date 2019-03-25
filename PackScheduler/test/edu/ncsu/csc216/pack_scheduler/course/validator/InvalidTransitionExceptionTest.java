package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests InvalidTransitionException to ensure that the exceptions
 * are created as expected.
 * 
 * @author Matthew Strickland, Joey Davidson, and William Boyles
 *
 */
public class InvalidTransitionExceptionTest {
	
	/**
	 * Tests the InvalidTransitionException class when using a custom message.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Testing custom exception message.");
	    assertEquals("Testing custom exception message.", ce.getMessage());
	}

	/**
	 * Tests the InvalidTransitionException class when using the default message.
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ce.getMessage());
   	}
}
