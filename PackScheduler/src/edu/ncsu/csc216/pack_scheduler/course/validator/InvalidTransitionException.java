package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Creates new exception InvalidTransitionException.
 * 
 * @author Matthew Strickland, Joey Davidson, and William Boyles
 */
public class InvalidTransitionException extends Exception {
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new InvalidTransitionException with a custom message.
	 * 
	 * @param message The custom exception
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Creates a new ConflictException with the default message.
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
