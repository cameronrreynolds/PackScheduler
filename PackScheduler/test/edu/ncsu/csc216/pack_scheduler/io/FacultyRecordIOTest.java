package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * This class tests the FacultyRecordIO class and its methods
 * @author Michael, Matthew, Cameron
 *
 */
public class FacultyRecordIOTest {

	/**
	 * Test provided by Dr. Heckman that is supposed to fail in windows
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    LinkedList<Faculty> faculty = new LinkedList<Faculty>();
	    faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 1));
	    
	   
	    
	   
	    try {
			FacultyRecordIO.writeFacultyRecords("test-files/faculty_records_io.txt", faculty);
		} catch (IOException e) {
			fail();
		}
	   
	}

}
