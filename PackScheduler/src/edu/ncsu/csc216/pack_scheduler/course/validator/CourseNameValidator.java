package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Runs the State Pattern FSM to tell if a given course name is valid.
 * 
 * @author William Boyles, Matthew Strickland
 *
 */
public class CourseNameValidator {
	/** Current state of the FSM */
	State state;
	/** The letter state object of the FSM */
	final State letterState = new LetterState();
	/** The number state object of the FSM */
	final State numberState = new NumberState();
	/** The suffix state object of the FSM */
	final State suffixState = new SuffixState();
	/** Digit count */
	private int dc;
	/** Letter count */
	private int lc;

	/**
	 * The FSM to tell if a given course name is valid.
	 * 
	 * @param name The course name to test.
	 * @return True if the name is valid, and false if the name is invalid but went
	 *         through valid FSM transitions.
	 * @throws InvalidTransitionException If a name would create an invalid
	 *                                    transition in the FSM.
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		state = new InitialState();
		dc = 0;
		lc = 0;

		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);

			if (Character.isDigit(c)) {
				try {
					state.onDigit();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			} else if (Character.isLetter(c)) {
				try {
					state.onLetter();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			} else {
				state.onOther();
			}
		}

		return dc == 3 || state == suffixState;
	}

	/**
	 * Represents an abstract state in the FSM. ensures every concrete state has
	 * code to handle the next character being a letter and a digit.
	 * 
	 * @author William Boyles, Matthew Strickland
	 */
	private abstract class State {
		public abstract void onLetter() throws InvalidTransitionException;

		public abstract void onDigit() throws InvalidTransitionException;

		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * Handles the FSM for the letter state.
	 * 
	 * @author William Boyles
	 */
	private class LetterState extends State {
		static final int MAX_PREFIX_LETTERS = 4;

		private LetterState() {
		}

		/**
		 * A course name is in the letter state, and the next character is a letter.
		 * 
		 * @throws InvalidTransitionException if the letter count is not less than 4.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (lc < MAX_PREFIX_LETTERS) {
				lc++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}

		}

		/**
		 * A course name is in the letter state, and the next character is a digit.
		 */
		@Override
		public void onDigit() {
			dc++;
			state = numberState;
		}

	}

	/**
	 * Handles the FSM for the suffix state.
	 * 
	 * @author William Boyles
	 */
	private class SuffixState extends State {

		private SuffixState() {
		}

		/**
		 * A course name is in the suffix state and the next character is a letter.
		 * 
		 * @throws InvalidTransitionException always because the suffix cannot be more
		 *                                    than 1 letter.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * A course name is in the suffix state and the next character is a digit.
		 * 
		 * @throws InvalidTransitionException always because the suffix cannot be more
		 *                                    than 1 letter.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}

	}

	/**
	 * Creates a class representing the initial state of the FSM.
	 * 
	 * @author Matthew Strickland
	 *
	 */
	private class InitialState extends State {

		/**
		 * Constructor for the InititalState.
		 */
		private InitialState() {
			dc = 0;
			lc = 0;
		}

		/**
		 * Outlines behavior if while in the initial state the FSM receives a letter.
		 */
		@Override
		public void onLetter() {
			state = letterState;
			lc++;
		}

		/**
		 * Outlines behavior if while in the initial state the FSM receives a digit.
		 * 
		 * @throws InvalidTransitionException on receiving any digit.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * Creates a class representing the number state of the FSM.
	 * 
	 * @author Matthew Strickland
	 *
	 */
	private class NumberState extends State {

		/**
		 * Constructor for the NumberState.
		 */
		private NumberState() {

		}

		/**
		 * Outlines behavior if while in the digit state the FSM receives a digit.
		 * 
		 * @throws InvalidTransitionException on receiving any digit if the digit count
		 *                                    is over three..
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (dc < 3) {
				state = numberState;
				dc++;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}

		/**
		 * Outlines behavior if while in the digit state the FSM receives a letter.
		 * 
		 * @throws InvalidTransitionException on receiving any letter.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (dc == 3) {
				state = suffixState;
				lc++;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
	}
}
