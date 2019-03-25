package edu.ncsu.csc216.pack_scheduler.user;

/**
 * A User is one who interacts with the registration system.
 * They are either a student, a faculty,  or a registrar.
 * @author William Boyles, Matthew Strickland, Joey Davidson
 *
 */
public abstract class User {

	/** The first name of the User */
	private String firstName;
	/** The last name of the User */
	private String lastName;
	/** The User's id */
	private String id;
	/** The User's email address */
	private String email;
	/** The User's password */
	private String password;

	/**
	 * Creates a User with a iven name, id, email, and password
	 * @param firstName The User's first name as a String.
	 * @param lastName The User's last name as a String.
	 * @param id the User's id as a String.
	 * @param email The User's email as a String.
	 * @param password The User's password as a String.
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Returns the email of the student
	 * 
	 * @return the email of the student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the first name of the student
	 * 
	 * @return the first name of the student
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the email if valid
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if the email is invalid due to improper
	 *                                  formatting.
	 */
	public void setEmail(String email) {
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf('.') < email.lastIndexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the student's password
	 * 
	 * @return the student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password
	 * 
	 * @param password the student's password
	 * @throws IllegalArgumentException if the password is null or an empty string.
	 */
	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
	
		this.password = password;
	}

	/**
	 * Sets the student's first name
	 * 
	 * @param firstName The student's first name
	 * @throws IllegalArgumentException if the parameter is null or an empty string.
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
	
		this.firstName = firstName;
	}

	/**
	 * Returns the student's last name
	 * 
	 * @param lastName the student's last name
	 * @throws IllegalArgumentException if the parameter is null or an empty string.
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
	
		this.lastName = lastName;
	}

	/**
	 * Returns the last name of the student
	 * 
	 * @return the last name of the student
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the id of the student
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if the parameter is null or an empty string.
	 */
	protected void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the ID of the student
	 * 
	 * @return the ID of the student
	 */
	public String getId() {
		return id;
	}

	/**
	 * Generates a hash code for equals using this instance of User's fields.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Tells if an Objects is the same as this instance of User.
	 * @param obj An Object to compare against this instance of User.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}