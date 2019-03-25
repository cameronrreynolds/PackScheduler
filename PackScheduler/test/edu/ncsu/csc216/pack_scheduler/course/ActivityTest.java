package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the conflict checking in the Activity class
 * @author Elias Robertson
 */
public class ActivityTest {

  /**
   * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.pack_scheduler.course.Activity)}.
   * Uses the test code given on the project instructions
   */
  @Test
  public void testCheckConflict() {
    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 200, "MW", 1330, 1445);
    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 200, "TH", 1330, 1445);
    
    try {
        a1.checkConflict(a2);
        a2.checkConflict(a1);
        assertEquals("Incorrect meeting string for this activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
        assertEquals("Incorrect meeting string for other activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
    } catch (ConflictException e) {
        fail("A ConflictException was thrown when two Activities at the same time on completely different days were compared.");
    }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1445, 1530);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
  } catch (ConflictException e) {
      assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
  }
    
    a1.setMeetingDays("TW");
    a1.setActivityTime(1445, 1530);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TW 2:45PM-3:30PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
  } catch (ConflictException e) {
      assertEquals("TW 2:45PM-3:30PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
  }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1445, 1530);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
  } catch (ConflictException e) {
      assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
  }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1330, 1630);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 1:30PM-4:30PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
  } catch (ConflictException e) {
      assertEquals("TH 1:30PM-4:30PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
  }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1245, 1330);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 12:45PM-1:30PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
  } catch (ConflictException e) {
      assertEquals("TH 12:45PM-1:30PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
  }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1245, 1445);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 12:45PM-2:45PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
    } catch (ConflictException e) {
      assertEquals("TH 12:45PM-2:45PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1345, 1445);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 1:45PM-2:45PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
    } catch (ConflictException e) {
      assertEquals("TH 1:45PM-2:45PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1345, 1645);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 1:45PM-4:45PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
    } catch (ConflictException e) {
      assertEquals("TH 1:45PM-4:45PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    a1.setMeetingDays("TH");
    a1.setActivityTime(1315, 1645);
    
    try {
        a1.checkConflict(a2);
        fail();
    } catch (ConflictException e) {
        assertEquals("TH 1:15PM-4:45PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    try {
      a2.checkConflict(a1);
      fail();
    } catch (ConflictException e) {
      assertEquals("TH 1:15PM-4:45PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    a1.setMeetingDays("A");
    a1.setActivityTime(0, 0);
    
    try {
        a1.checkConflict(a2);
        a2.checkConflict(a1);
    } catch (ConflictException e) {
        fail();
        assertEquals("A", a1.getMeetingString());
        assertEquals("A", a2.getMeetingString());
    }
  }
}
