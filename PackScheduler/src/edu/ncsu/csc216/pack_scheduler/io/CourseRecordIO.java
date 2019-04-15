package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Reads and writes lists of courses from and to files
 * 
 * @author Elias Robertson, William Boyles, and Joseph Dasilva
 */
public class CourseRecordIO {
//	/** The max length of a course string split up */
//	private static int courseStringLength = 9;

	/**
	 * Reads course records from a file and returns a list of courses from the file
	 * Uses code from course website
	 * 
	 * @param nameOfFile file to read Course records from
	 * @return a list of courses from the file
	 * @throws FileNotFoundException if the file cannot be found/read
	 */
	public static SortedList<Course> readCourseRecords(String nameOfFile) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(nameOfFile));
		SortedList<Course> courses = new SortedList<>();
		
		boolean duplicate = false;
		
		while (scan.hasNextLine()) {
			try {
				//read the course
				Course c = readCourse(scan.nextLine());
				
				//check that it isn't a duplicate
				for (int i = 0; i < courses.size(); i++) {
					Course compare = courses.get(i);
					
					if (c.getName().equals(compare.getName()) && c.getSection().equals(compare.getSection())) {
						duplicate = true;
					}
				}
				
				//if not a duplicate, add it to the list
				if (!duplicate && c != null) {
					courses.add(c);
				}
				
				//reset duplicate if it changed
				duplicate = false;
				
			} catch (IllegalArgumentException e) {
				// If it isn't valid, skip it.
			}
		}
		
		//always close what you open
		scan.close();
		
		//return the course list.
		return courses;
	}

	/**
	 * Helper method that reads one line from a text file and returns a Course object from this information.
	 * @param nextLine The line of the file to read as a String.
	 * @return A Course constructed from the information of the line.
	 */
	private static Course readCourse(String nextLine) {
		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter(",");
		
		try {
			String name = scan.next();
			String title = scan.next();
			String section = scan.next();
			int courses = scan.nextInt();
			String id = scan.next();
			int enroll = scan.nextInt();
			String meetingDays = scan.next();
			Course course = new Course(name, title, section, courses, null, enroll, meetingDays);
			if(scan.hasNextInt() && scan.hasNextInt()) {
				course.setActivityTime(scan.nextInt(), scan.nextInt());
			}
			scan.close();
			RegistrationManager r = RegistrationManager.getInstance();
			if(r.getFacultyDirectory().getFacultyById(id) != null) {
				course.setInstructorId(id);
				r.getFacultyDirectory().getFacultyById(id).getSchedule().addCourseToSchedule(course);
			}
			
			return course;
		} catch(NoSuchElementException e) {
			scan.close();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Writes the list of courses to the file Uses code from course website
	 * 
	 * @param fileName The name of the file
	 * @param courses  The courses
	 * @throws IOException Throws an IO exception if file cannot be written to
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream write = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			write.println(courses.get(i).toString());
		}

		write.close();
	}

}