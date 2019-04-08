package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * This class defines a faculty object.
 * 
 * @author Matthew Strickland
 *
 */
public class Faculty extends User {

	/** The maximum number of courses faculty can teach. */
	private int maxCourses;
	/** This represents the min courses value */
	private static final int MIN_COURSES = 1;
	/** This represents the max courses value */
	private static final int MAX_COURSES = 3;
<<<<<<< HEAD
	/** The faculty's schedule. */
=======
	/** The list of Course's this faculty teaches */ 
>>>>>>> branch 'master' of https://github.ncsu.edu/engr-csc216-spring2019/csc216-203-LLL-06.git
	private FacultySchedule schedule;
	
	/**
	 * Creates a new Faculty object.
	 * 
	 * @param firstName The faculty's first name.
	 * @param lastName The faculty's last name.
	 * @param id The faculty's Unity ID.
	 * @param email The faculty's email.
	 * @param password The faculty's password.
	 * @param maxCourses The maximum number of courses the faculty can teach.
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}
	
	/**
	 * Getter method for the faculty's schedule.
	 * 
	 * @return The faculty's schedule.
	 */
	public FacultySchedule getSchedule() {
		return this.schedule;
	}

    /**
     * Sets the maximum number of courses the faculty can teach.
     * 
     * @param maxCourses The number of courses.
     */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) throw new IllegalArgumentException("Invalid max courses");
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Getter for the maximum courses the faculty can teach.
	 * 
	 * @return The number of courses.
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Determines if the faculty has an overloaded schedule.
	 * 
	 * @return If the schedule is overloaded or not.
	 */
	public boolean isOverloaded() {
		if (schedule.getNumScheduledCourses() > maxCourses) {
			return true;
		}
		return false;
	}

	/**
	 * Generates hashcode for the faculty.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}


	/**
	 * Determines if a given object is equal to the current Faculty object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Faculty)) {
			return false;
		}
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a Faculty's fields as a comma-separated list.
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ Integer.toString(maxCourses);
	}
	
	/**
	 * Returns the schedule of Course's that the faculty teaches.
	 * 
	 * @return The schedule of Course's
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	public boolean isOverloaded() {
		if(schedule.getNumScheduledCourses() > maxCourses) {
			return true;
		}
		return false;
		
	}
	
}
