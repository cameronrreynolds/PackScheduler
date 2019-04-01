package edu.ncsu.csc216.pack_scheduler.user;

/**
 * This class defines a faculty object.
 * 
 * @author Matthew Strickland
 *
 */
public class Faculty extends User {

	/** The maximum number of courses faculty can teach. */
	private int maxCourses;
	
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
	}

    /**
     * Sets the maximum number of courses the faculty can teach.
     * 
     * @param maxCourses The number of courses.
     */
	public void setMaxCourses(int maxCourses) {
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Getter for the maimum courses the faculty can teach.
	 * 
	 * @return The number of courses.
	 */
	public int getMaxCourses() {
		return maxCourses;
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
	
}
