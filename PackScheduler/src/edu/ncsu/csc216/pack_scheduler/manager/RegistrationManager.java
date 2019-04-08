package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This class allows users to manage class registration.
 * 
 * @author Matthew Strickland, William Boyles, Joey Davidson
 *
 */
public class RegistrationManager {

	/** Singleton instance of manager */
	private static RegistrationManager instance;
	/** List of courses available to be added to schedule */
	private CourseCatalog courseCatalog;
	/** List of students who can log into the system and create their schedules. */
	private StudentDirectory studentDirectory;
	/** List of faculty who can log into the system and manage their schedules. */
	private FacultyDirectory facultyDirectory;

	/** The registrar can add and remove students and courses from directories */
	private User registrar;
	/** The current user is either a student or a registrar. */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Stores the user information for the registrar */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Creates a registrar and empty course and student directories.
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory(); 
		facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Creates a registrar user from the registrar properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes the password into a more obfuscated String/
	 * @param pw The password to hash as a String.
	 * @return A String representing the hash of the password.
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Gets this instance of the registration manager.
	 * 
	 * @return This instance of the registration manager to be used within the
	 *         class.
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Getter method for the course catalog.
	 * 
	 * @return The created course catalog.
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Getter method for student directory.
	 * 
	 * @return The created student directory.
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Getter method for faculty directory.
	 * 
	 * @return The created faculty directory.
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Allows a user to login to the registration manager with their credentials.
	 * 
	 * @param id       The user ID of the user.
	 * @param password The password of the user.
	 * @return A boolean representing if the user logged in or not.
	 */
	public boolean login(String id, String password) {

		Student student = studentDirectory.getStudentById(id);
		Faculty faculty = facultyDirectory.getFacultyById(id);
		
		if (currentUser == null) {
			if (student != null) {
				try {
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (student.getPassword().equals(localHashPW)) {
						currentUser = student;
						return true;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			} else if (registrar.getId().equals(id)) {
					MessageDigest digest;
					try { 
						digest = MessageDigest.getInstance(HASH_ALGORITHM);
						digest.update(password.getBytes());
						String localHashPW = new String(digest.digest());
						if (registrar.getPassword().equals(localHashPW)) {
							currentUser = registrar;
							return true;
						}
					} catch (NoSuchAlgorithmException e) {
						throw new IllegalArgumentException();
					}
			} else if(faculty != null) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String hasedPw = new String(digest.digest());
					if(faculty.getPassword().equals(hasedPw)) {
						currentUser = faculty;
						return true;
					}
				} catch(NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}


		return false;
	}

	/**
	 * Logs the current user out of the system.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Getter method for the current user.
	 * 
	 * @return The current user, with their permissions.
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears the data in the registration manager.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Adds a faculty to a course.
	 * 
	 * @param course The course with new faculty.
	 * @param faculty The faculty to be added to the course.
	 * @return If the operation was successful.
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			course.setInstructorId(faculty.getId());
			faculty.getSchedule().addCourseToSchedule(course);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * Removes faculty from a course.
	 * 
	 * @param course The course with the faculty to be removed.
	 * @param faculty The faculty to be removed.
	 * @return If the operation was successful.
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			course.setInstructorId(null);
			faculty.getSchedule().removeCourseFromSchedule(course);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * Resets the faculty's schedule.
	 * 
	 * @param faculty The faculty who's schedule is to be reset.
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		faculty.getSchedule().resetSchedule();
	}

	/**
	 * This class outlines a registrar as a child class of user.
	 * 
	 * @author matthewstrickland
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password in the
		 * registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}